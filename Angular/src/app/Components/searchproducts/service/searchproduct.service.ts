import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Product } from '../../products/model/Product.model';

@Injectable({
  providedIn: 'root'
})
export class SearchproductService {

  getProductsApi:string;
  constructor(private http:HttpClient) {
    this.getProductsApi="http://localhost:8111/product-service/product/products/bySearch/";
   }

  getProductsBySearch(searchTitle:string):Observable<Product[]>{
    return this.http.get<Product[]>(this.getProductsApi+searchTitle);
  }
}
