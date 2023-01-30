import { Component, HostListener, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { CartserviceService } from '../cart/service/cartservice.service';
import { UpdateUser, User } from '../login/Model/user.model';
import { Orders } from '../myorders/Model/order.model';
import { ProfileServiceService } from '../profile/service/profile-service.service';
import { wallet } from '../wallet/model/wallet.model';
import { WalletServiceService } from '../wallet/service/wallet-service.service';
import { postOrder } from './model/postOrder.model';
import { OrderServiceService } from './service/order-service.service';

declare var Razorpay: any;

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css'],
})
export class OrderComponent implements OnInit {
  user: User;
  orderForm:FormGroup;
  postOrder:postOrder;
  cartId:string;
  paymentId: string;
  error: string;
  amount:number;
  form: any ;
  wallet:wallet;
  updateAddressForm:FormGroup;
  updateUser:UpdateUser;

  constructor(private profileService: ProfileServiceService,private orderService:OrderServiceService,
                private router:Router,private cartService:CartserviceService,private walletService:WalletServiceService,
                  private appService:AppService) {
  this.paymentId="";
      this.error="";
    this.cartId="";
    this.postOrder={
      modeOfPayment:"",
      paymentStatus:true
    }
    this.updateUser={
      address:{}
    }
    this.updateAddressForm = new FormGroup(
      {"houseNumber":new FormControl(),
      "streetName": new FormControl(),
      "colonyName": new FormControl,
      "city" : new FormControl(),
      "state" : new FormControl(),
      "pinCode": new FormControl() }
    );

    this.wallet={
      "walletId": "",
      "currentBalance": 0.0,
      "userId": ""
    }

    this.amount=0;
    this.user = {
      id: '',
      email: '',
      fullName: '',
      encodedPassword: '',
      mobileNo: 0,
      password: '',
      role: '',
      address: {},
    };

    this.orderForm = new  FormGroup(
      {"paymentMethod":new FormControl(Validators.required)}
     );
    this.form={};
  }

  ngOnInit(): void {
    let userId = localStorage.getItem('userId');
    console.log(userId);
    this.profileService.getUserDetails(userId).subscribe(
      (data) => {
        this.user = data;
      },
      (error) => {
        alert('error');
      }
    );
    this.cartService.getCart().subscribe(data=>{
      this.cartId=data.cartId;
      this.amount=data.totalPrice;
    });


  this.walletService.getWallet().subscribe(data=>{
    if(!data){
      this.walletService.createWallet().subscribe(data1=>{
        this.wallet=data1;
      })
    }
    else{
      console.log(data);
    this.wallet=data;
    }
  })

  }

  onSubmit(){
    this.postOrder.modeOfPayment=this.orderForm.value.paymentMethod;

    console.log(this.postOrder.modeOfPayment);

    if(this.postOrder.modeOfPayment === "Payment-Gateway"){
      this.onPaymentByGateway();
    }
    else if(this.postOrder.modeOfPayment === 'E-wallet'){
      this.payByWallet();
    }
    else if(this.postOrder.modeOfPayment === 'Cash-On-Delivery'){

      this.orderService.placeOrder(this.postOrder,this.cartId).subscribe(data=>{
        this.cartService.removeAllItems(this.cartId).subscribe(data1=>{

        });
        this.navigate(data.orderId);
    },
    error=>{
      console.log(error);
    });
     // alert("order placed succesfully");
      this.appService.cartItems.next(0);
    }
    else{
      alert("please select the mode of payment");
    }

  }

  options = {
    "key": "",
    "amount": "",
    "name": "Shopitto",
    "description": "Shopping cart",
    "image": "https://rzp-1415-prod-dashboard-activation.s3.amazonaws.com/org_100000razorpay/main_logo/phpAJgHea",
    "order_id":"",
    "handler": function (response:any){
      var event = new CustomEvent("payment.success",
        {
          detail: response,
          bubbles: true,
          cancelable: true
        }
      );
      window.dispatchEvent(event);
    }
    ,
    "prefill": {
    "name": "",
    "email": "",
    "contact": ""
    },
    "notes": {
    "address": ""
    },
    "theme": {
    "color": "#3399cc"
    }
    };


    onPaymentByGateway(){
      this.form={
        "customerName": this.user.fullName,
        "email": this.user.email,
        "phoneNumber": this.user.mobileNo,
        "amount": this.amount
      };

      this.paymentId = '';
      this.error = '';
      this.orderService.createOrder(this.form).subscribe(
      data => {
        console.log(data);
        this.options.key = data.secretId;
        this.options.order_id = data.razorpayOrderId;
        this.options.amount = data.applicationFee; //paise
        this.options.prefill.name = "Shopitto";
        this.options.prefill.email = "abc@gmail.com";
        this.options.prefill.contact = "";

        if(data.pgName ==='razor2') {
          this.options.image="";
          var rzp1 = new Razorpay(this.options);
          rzp1.open();
        } else {
          var rzp2 = new Razorpay(this.options);
          rzp2.open();
        }


        rzp1.on('payment.failed', function (response:any){
          // Todo - store this information in the server
          console.log(response);
          console.log(response.error.code);
          console.log(response.error.description);
          console.log(response.error.source);
          console.log(response.error.step);
          console.log(response.error.reason);
          console.log(response.error.metadata.order_id);
          console.log(response.error.metadata.payment_id);

        }
        );
      },
      err => {
        this.error = err.error.message;
      }
      );
    }

    @HostListener('window:payment.success', ['$event'])
    onPaymentSuccess(event:any): void {
      if (event) {
        console.log(event.detail.razorpay_order_id);
      }
      this.placeOrder();
       console.log(event.detail);
    }

    placeOrder(){

      this.orderService.placeOrder(this.postOrder,this.cartId).subscribe(data=>{
        this.cartService.removeAllItems(this.cartId).subscribe(data1=>{
         // alert("order placed successfully");
          this.appService.cartItems.next(0);
          this.navigate(data.orderId);
        });
      });


  }

  navigate(orderId:string){
    this.router.navigateByUrl("/orderplaced/"+orderId);
  }

  payByWallet(){

    if(this.wallet.currentBalance >= this.amount){
      console.log(this.amount);
      console.log("you can pay By E-wallet");
      this.walletService.payBywallet(this.amount,this.wallet.walletId).subscribe(data=>{
        this.placeOrder();

      }
      );
    }
    else{
      console.log(this.amount);
      alert("Your balance is low please top-up your account");
    }
    console.log("payByWallet");

  }

  onAddressFormSubmit(){
    console.log("submitted");
    this.updateUser.address.houseNumber=this.updateAddressForm.value.houseNumber;
    this.updateUser.address.streetName=this.updateAddressForm.value.streetName;
    this.updateUser.address.colonyName=this.updateAddressForm.value.colonyName
    this.updateUser.address.city=this.updateAddressForm.value.city;
    this.updateUser.address.state=this.updateAddressForm.value.state;
    this.updateUser.address.pinCode=this.updateAddressForm.value.pinCode;
    console.log(this.updateUser);
    this.profileService.updateUser(this.user.id,this.updateUser).subscribe(data=>{
      this.user=data;
    });
  }
}
