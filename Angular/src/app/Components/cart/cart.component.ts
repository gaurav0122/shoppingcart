import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import swal from 'sweetalert';
import { Product } from '../dashboard/model/productModel';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';
import { Cart } from './model/cart.model';
import { CartserviceService } from './service/cartservice.service';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  products:Product[];
  cart:Cart;
  isCartEmpty:boolean;
  getCart:any=null;

  constructor(private cartService:CartserviceService,
                private router:Router, private appService:AppService) {
    this.isCartEmpty=true;
    this.products=[];
    this.cart={"cartId": "",
            "totalPrice": 0.0,
            "userId": "",
            "totalItems": 0,
            "items": [
                  {
                      "productId": "",
                      "productName": "",
                      "productImg": "",
                      "price": 0.0,
                      "quantity": 0
                  }
    ]};
  }

  ngOnInit(): void {

    this.getCartStep();



}

   private getCartStep() {

      this.cartService.getCart().subscribe(data=>{
        this.cart=data;
        this.getCart=data;
        console.log(this.getCart);
        this.checkCartItems();
    });

    }

  private  checkCartItems(){
      if(this.cart.totalItems==0){
        this.isCartEmpty=true;
        console.log(this.isCartEmpty);
      }
      else{
        this.isCartEmpty=false;
      }
    }


  deleteItemCheck(productId:string){

    swal({
      title: "Are you sure? Delete the item?",
      //text: "Once deleted, you will not be able to recover this imaginary file!",
      icon: "warning",
      buttons: ["Cancel",true],
      dangerMode: true
    })
    .then((willDelete) => {
      if (willDelete) {
        this.deleteItem(productId);
      }
    });

  }

  deleteItem(productId:string){
    console.log(productId);
    this.cartService.deleteItemInCart(this.cart.cartId,productId).subscribe(
      data=>{
       this.cart=data;
        this.appService.cartItems.next(data.totalItems);
       if(this.cart.totalItems==0){
        this.isCartEmpty=true;
      }
      }
    );
    swal("Item deleted successfully","","success");
  }

  addQuantity(productId:string,quantity:number){
    this.cartService.addQuantity(this.cart.cartId,productId).subscribe(
      data=>{
        this.cart=data;
      }
    );
    if(quantity===10){
      console.log("abc");
      swal("","You cant increase quantity more than 10","warning");
    }
  }

  subQuantity(productId:string,quantity:number){
    if(quantity===1){
      console.log("any");
      swal({
        icon:"warning",
        title:"Can't reduce quantity",
        text:"Do you want to delete the item",
        buttons:["Cancel",true]
      })
      .then(value=>{
        if(value){
          this.deleteItem(productId);
        }
      });
    }
    else{
      this.cartService.subQuantity(this.cart.cartId,productId).subscribe(
        data=>{
          this.cart=data;
        });
    }

  }

  checkOut(){
    this.router.navigateByUrl("order");
  }

}
