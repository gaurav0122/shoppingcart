import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppService } from 'src/app/app.service';
import { Cart } from '../../cart/model/cart.model';
import { CartserviceService } from '../../cart/service/cartservice.service';
import { ProductDetails } from '../model/productdetails.model';
import { Reviews } from '../model/review.model';

@Injectable({
  providedIn: 'root'
})
export class ProductdetailsserviceService {

  getProductDetailsApi:string;
  addItemToCartApi:string;
  getReviews:string;


  constructor(private http:HttpClient,private cartService:CartserviceService) {
    this.getProductDetailsApi="http://localhost:8111/product-service/product/allproduct/";
    this.addItemToCartApi="http://localhost:8111/cart-service/cart/additem/";
    this.getReviews ="http://localhost:8111/product-service/getreview/"
  }

  getProductDetails(productid:string):Observable<ProductDetails>{
    return this.http.get<ProductDetails>(this.getProductDetailsApi + productid);
  }


  addItemToCart(productId:string):Observable<Cart>{
    let userId = localStorage.getItem("userId");
    let header = localStorage.getItem("auth");
    if(userId){
      return this.http.post<Cart>(this.addItemToCartApi+userId+"/"+productId,{},
      {headers:{authorization:header||"Token not available"}});
    }
    else{
      throw new Error("user not logged in...");
    }
 }

    getReviewByProductId(productId:string):Observable<Reviews[]>{
      return this.http.get<Reviews[]>(this.getReviews+productId);
    }

}
