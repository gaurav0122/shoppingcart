import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AppService } from 'src/app/app.service';
import swal from 'sweetalert';
import { UpdateUser, User } from '../login/Model/user.model';
import { ProfileServiceService } from './service/profile-service.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  user:User;
  updateProfileModal:boolean;
  updateForm:FormGroup;
  updateUser:UpdateUser;


  constructor(private profileService:ProfileServiceService,private appService:AppService) {

    this.updateProfileModal = false;

    this.updateUser={address:{
    }};

    this.user={id:"",
              email:"",
              fullName:"",
              encodedPassword:"",
              mobileNo:0,
              password:"",
              role:"",
              address:{}
            };

    this.updateForm = new FormGroup(
      {
        "fullName":new FormControl(),
         "email": new FormControl(),
          "mobileNo":new FormControl(),
          "gender":new FormControl(),
          "dateOfBirth":new FormControl(),
          "houseNumber":new FormControl(),
          "streetName":new FormControl(),
          "colonyName":new FormControl(),
          "city":new FormControl(),
          "state":new FormControl(),
          "pinCode":new FormControl()
      }
    );

   }

  ngOnInit(): void {
   let userId = localStorage.getItem('userId');
   console.log(userId);
  this.profileService.getUserDetails(userId).subscribe(
    data=>{
      console.log(data);
      this.user=data;
    },
    error=>{
      alert("error")
    }
  );
  }

  updateProfile(){
    this.updateProfileModal = !this.updateProfileModal;
  }

  onSubmit(){
    let userId = localStorage.getItem('userId');
    console.log(this.updateForm.value);
    this.updateUser.fullName=this.updateForm.value.fullName;
    this.updateUser.email=this.updateForm.value.email;
    this.updateUser.dateOfBirth=this.updateForm.value.dateOfBirth;
    this.updateUser.mobileNo=this.updateForm.value.mobileNo;
    this.updateUser.gender=this.updateForm.value.gender;
    this.updateUser.address.houseNumber=this.updateForm.value.houseNumber;
    this.updateUser.address.streetName=this.updateForm.value.streetName;
    this.updateUser.address.colonyName=this.updateForm.value.colonyName
    this.updateUser.address.city=this.updateForm.value.city;
    this.updateUser.address.state=this.updateForm.value.state;
    this.updateUser.address.pinCode=this.updateForm.value.pinCode;
    console.log(this.updateUser);

    this.profileService.updateUser(userId,this.updateUser).subscribe(data=>{
      this.user=data;
      console.log(data);
      //alert("profile updated successfully");
      swal({
        icon: "success",
        text:"Profile updated successfully"
      });
    },
    error=>{
      alert(error.error.message)
    });

    if(this.updateUser.fullName){
      localStorage.setItem('userName',this.updateUser.fullName);
      this.appService.userName.next(this.updateUser.fullName);
    }

  }
}
