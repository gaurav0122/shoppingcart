import { Component, OnInit } from '@angular/core';
import { FormControl, FormControlName, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { from, max } from 'rxjs';
import swal from 'sweetalert';
import { UserReg } from './model/user.model';
import { RegisterService } from './service/register.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {


  registrationForm:FormGroup;
  user:UserReg;

  constructor(private registerService:RegisterService, private router:Router) {

    this.user={"fullName":"",
                "email":"",
                "gender":"",
                "mobileNo":0,
                "password":"",
                "role":"",
                "address":{}
                };

    this.registrationForm = new FormGroup(
      {"name" : new FormControl("",[Validators.required,Validators.minLength(3)]),
      "email":new FormControl("",[Validators.required,Validators.email]),
      "mobileNo":new FormControl(null,[Validators.required,Validators.min(5555555555),Validators.max(9999999999)]),
      "dateOfBirth":new FormControl(),
      "password":new FormControl("",[Validators.required,Validators.minLength(6),Validators.maxLength(15)]),
      "gender":new FormControl(Validators.required),
      "role":new FormControl(),
      "houseNumber":new FormControl("",Validators.required),
      "streetName":new FormControl("",Validators.required),
      "colonyName":new FormControl("",Validators.required),
      "city":new FormControl("",Validators.required),
      "state":new FormControl("",Validators.required),
      "pinCode":new FormControl("",Validators.required)
    }
    );
  }

  ngOnInit(): void {
  }

  onRegistrationFormSubmit(){



    console.log("form submitted");
    console.log(this.registrationForm);

    this.user.fullName = this.registrationForm.value.name;
    this.user.email = this.registrationForm.value.email;
    this.user.mobileNo = this.registrationForm.value.mobileNo;
    this.user.role = this.registrationForm.value.role;
    if(! this.user.role){
      alert("please select the role");

      return;
    }
    this.user.gender = this.registrationForm.value.gender;
    this.user.password = this.registrationForm.value.password;
    this.user.dateOfBirth=this.registrationForm.value.dateOfBirth;
    this.user.address.houseNumber=this.registrationForm.value.houseNumber;
    this.user.address.streetName=this.registrationForm.value.streetName;
    this.user.address.colonyName=this.registrationForm.value.colonyName
    this.user.address.city=this.registrationForm.value.city;
    this.user.address.state=this.registrationForm.value.state;
    this.user.address.pinCode=this.registrationForm.value.pinCode;
    console.log(this.user);

    this.registerService.registerUser(this.user).subscribe(data=>{
      swal("User Registered successfully","","success");
      console.log("user Registerd successfully");
      this.router.navigateByUrl("/login");
    },
    error=>{
      console.log(error);
      alert(error.error.message);
    });

  }
}
