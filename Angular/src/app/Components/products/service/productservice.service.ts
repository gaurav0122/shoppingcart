import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../model/Product.model';

@Injectable({
  providedIn: 'root'
})
export class ProductserviceService {

  getProductsByCategory:string;


  constructor(private http:HttpClient) {
      this.getProductsByCategory ="http://localhost:8111/product-service/product/allproduct/category/"
   }

   getAllProductByCategory(catName:string):Observable<Product[]>{
      return this.http.get<Product[]>(this.getProductsByCategory+catName);
   }
}
