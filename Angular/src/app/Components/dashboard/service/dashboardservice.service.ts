import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category } from '../model/CategoryModel';
import { Product } from '../model/productModel';

@Injectable({
  providedIn: 'root'
})
export class DashboardserviceService {



  categoryApi:string;
  productApi:string;
  productTypeApi:string;
  constructor(private http:HttpClient) {
    this.categoryApi = "http://localhost:8111/product-service/category/category";
    this.productApi="http://localhost:8111/product-service/product/allproduct";
  this.productTypeApi ="http://localhost:8111/product-service/product//productType/byCategory/";
  }

  getAllCategories():Observable<Category[]>{
    return this.http.get<Category[]>(this.categoryApi);
  }

  getAllProducts():Observable<Product[]>{
    return this.http.get<Product[]>(this.productApi);
  }

  getAllProductTypes(categoryid:string|undefined):Observable<String[]>{
    return this.http.get<String[]>(this.productTypeApi+categoryid);
  }
}
