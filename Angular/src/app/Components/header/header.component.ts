import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import { Category } from '../dashboard/model/CategoryModel';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponentApp implements OnInit {

  loggedIn:boolean;
  isMerchant:boolean;
  userName:string|null;
  userName1:any;
  cartItem:number;
  categories:Category[];
  productTypes:String[];
  searchForm:FormGroup;

  constructor(private appService:AppService,private router:Router
                    ,private dashboardService:DashboardserviceService) {
    this.loggedIn=false;
    this.isMerchant=false;
    this.cartItem=0;
    this.userName="";
    this.categories =[];
    this.productTypes=[];

    this.searchForm = new FormGroup({
      searchTitle:new FormControl("",Validators.required)
    });
   }

  ngOnInit(): void {

    this.dashboardService.getAllCategories().subscribe(data=>{
      this.categories=data;
    });

    this.setHeaderItems();

  }

  setHeaderItems(){

      this.appService.loggedIn.subscribe(data=>{
        this.loggedIn=data;
       });

      this.appService.userName.subscribe(data=>{
        this.userName=data;
        this.userName1= this.userName?.split(" ").at(0);
      });

      this.appService.isMerchant.subscribe(data=>{
        this.isMerchant=data;
        console.log(data);
      });

      this.appService.cartItems.subscribe(data=>{
        this.cartItem=data;
        console.log(data);
      });
  }

  logOut(){
    this.appService.loggedIn.next(false);
    this.appService.isMerchant.next(false);
    this.appService.cartItems.next(0);
    localStorage.removeItem('token');
    localStorage.removeItem('userId');
    localStorage.removeItem('userName');
    localStorage.removeItem('isLoggedIn');
    localStorage.removeItem("auth");
    localStorage.removeItem("userRole");
    alert("user logged out..");
    this.loggedIn=false;
    this.router.navigateByUrl("/");
  }

  toggle(){
    this.router.navigateByUrl("cart");
  }

  categoryClick(categoryName:string){
      this.router.navigateByUrl("/products/"+categoryName);
  }

  getProductTypes(categoryid:string|undefined){
    this.dashboardService.getAllProductTypes(categoryid).subscribe(data=>{
      this.productTypes=data;
      console.log(data);
    })
  }
  clear(){
    this.productTypes=[];
  }

  searchItems(){
    this.router.navigateByUrl("searchproducts/"+this.searchForm.value.searchTitle);
  }
}
