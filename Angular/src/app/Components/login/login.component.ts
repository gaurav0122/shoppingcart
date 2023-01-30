import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router, UrlTree } from '@angular/router';
import { AppService } from 'src/app/app.service';
import swal from 'sweetalert';
import { CartserviceService } from '../cart/service/cartservice.service';
import { User } from './Model/user.model';
import { LoginserviceService } from './service/loginservice.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm:FormGroup;
  user:User;
  errorMsg:string;
  isLoggedIn:boolean;
  userName:string;


  password : string;
  errorMessage = 'Invalid Credentials';
  successMessage: string;
  invalidLogin:boolean = false;
  loginSuccess:boolean = false;

  constructor(private loginService:LoginserviceService,private router:Router, private appService:AppService,private cartService:CartserviceService) {
    this.isLoggedIn=false;
    this.user={
      address:{},
      email:"",
      encodedPassword:"",
      fullName:"",
      id:"",
      mobileNo:0,
      password:"",
      role:""
    };

    this.errorMsg="";
    this.userName="";
    this.password="";
    this.successMessage="";

    this.loginForm = new FormGroup({
      "email": new FormControl("",[Validators.required,Validators.email]),
      "password": new FormControl("", [Validators.required]),
      });


  }

  ngOnInit(): void {

  }


  onSubmit() {
    console.log("reactive form submitted");
    console.log(this.loginForm);

    let email = this.loginForm.value.email;
    let password = this.loginForm.value.password;

    this.loginService.authenticationService(email,password).subscribe(result=> {
      this.invalidLogin = false;
      this.loginSuccess = true;
      this.successMessage = 'Login Successful.';
      console.log(result);
      this.setData(email,password);
    },
    error => {
      swal({
        title: "Invalid Creditionls..",
        icon: "error"

      });
      console.log("error");
      this.invalidLogin = true;
      this.loginSuccess = false;
    });



}

  setData(email:string,password:string){
    if(this.loginSuccess){
      this.loginService.getUserByEmail(email).subscribe(data=>{
        this.user=data;
        this.localSet(email,password);
      });

    }
    else{
      alert("invalid")
        this.errorMsg="Invalid creditionls"
    }
  }

  localSet(email:string,password:string){

    this.isLoggedIn=true;
    this.appService.loggedIn.next(true);
    this.appService.userName.next(this.user.fullName);

    if(this.user.role=="Merchant"){
      this.appService.isMerchant.next(true);
    }
    console.log(this.appService.userName.value);
    let token = btoa(email + ":" + password);
    localStorage.setItem("token",token);
    localStorage.setItem("isLoggedIn","true");
    localStorage.setItem("userId",this.user.id);
    localStorage.setItem("userName",this.user.fullName);
    localStorage.setItem("userRole",this.user.role);
   // console.log(this.user.fullName.split("\s").at(0));
    this.cartService.getCart().subscribe(data=>{
      if(data===null){
        this.cartService.addCart().subscribe(data1=>{
          console.log(data1);
          this.appService.cartItems.next(data1.totalItems);
        },
        error=>{
          console.log(error.message)
        });
      }
      else{
        this.appService.cartItems.next(data.totalItems);
        console.log(data);
      }
    },
    error=>{
      console.log(error.message)
    });
    alert("user Logged In");
    this.router.navigateByUrl("/");

  }
}

