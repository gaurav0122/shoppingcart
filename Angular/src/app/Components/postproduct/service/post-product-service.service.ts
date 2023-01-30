import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../../dashboard/model/productModel';
import { ProductDetails } from '../../productdetails/model/productdetails.model';

@Injectable({
  providedIn: 'root'
})
export class PostProductServiceService {

  postProductApi:string;

  constructor(private http:HttpClient) {
    this.postProductApi ="http://localhost:8111/product-service/product/allproduct/";
  }

  postProduct(product:ProductDetails):Observable<ProductDetails>{
    let userId = localStorage.getItem('userId');
    return this.http.post<ProductDetails>(this.postProductApi+userId,product,
                {headers:{authorization:localStorage.getItem('auth')||"cantAccess"}});
  }
}
