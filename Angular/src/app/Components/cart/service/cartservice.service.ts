import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cart } from '../model/cart.model';

@Injectable({
  providedIn: 'root'
})
export class CartserviceService {

  getCartApi:string;
  addcartApi:string;
  deleteItemApi:string;
  addQuantityApi:string;
  subQuantityApi:string;
  removeAllItemsApi:string;
  httpHeaders:HttpHeaders;

  constructor(private http:HttpClient) {
    this.getCartApi="http://localhost:8111/cart-service/cart/cart/user/";
    this.addcartApi="http://localhost:8111/cart-service/cart/addcart/";
    this.deleteItemApi="http://localhost:8111/cart-service/cart/cart/remove/";
    this.addQuantityApi="http://localhost:8111/cart-service/cart/cart/addquantity/";
    this.subQuantityApi="http://localhost:8111/cart-service/cart/cart/subquantity/";
    this.removeAllItemsApi="http://localhost:8111/cart-service/cart/cart/removeall/"
    this.httpHeaders = new HttpHeaders({authorization: localStorage.getItem('auth')!})
   }

   getCart():Observable<Cart>{
      let userId = localStorage.getItem('userId');
      let authToken = localStorage.getItem("auth");
      return this.http.get<Cart>(this.getCartApi+userId,{headers: {authorization: localStorage.getItem('auth')!}});
       // {headers: {authorization: authToken || "tokenNotAvailable"}});

        //{headers: this.httpHeaders});
   }

   addCart() {
    let userId = localStorage.getItem('userId');
    return this.http.post<Cart>(this.addcartApi+userId,{},
                  {headers: {Authorization: localStorage.getItem('auth')! }});
  }

  deleteItemInCart(cartId:string,productId:string):Observable<Cart>{
    return this.http.put<Cart>(this.deleteItemApi+cartId+"/"+productId,{}
                  ,{headers: this.httpHeaders});
  }

  addQuantity(cartId:string,productId:string):Observable<Cart>{
    return this.http.put<Cart>(this.addQuantityApi+cartId+"/"+productId,{}
                  ,{headers: this.httpHeaders});
  }

  subQuantity(cartId:string,productId:string):Observable<Cart>{
    return this.http.put<Cart>(this.subQuantityApi+cartId+"/"+productId,{}
                  ,{headers: this.httpHeaders});
  }

  removeAllItems(cartId:string):Observable<Cart>{
    return this.http.put<Cart>(this.removeAllItemsApi+cartId,{}
                  ,{headers: this.httpHeaders});
  }
}
