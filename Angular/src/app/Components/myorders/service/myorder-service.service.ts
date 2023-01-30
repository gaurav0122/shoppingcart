import { HttpClient, HttpEvent } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Reviews } from '../../productdetails/model/review.model';
import { Orders } from '../Model/order.model';

@Injectable({
  providedIn: 'root'
})
export class MyorderServiceService {

  getOrdersApi:string;
  getInvoice:string;
  postReviewApi:string;

  constructor(private http:HttpClient) {
    this.getOrdersApi="http://localhost:8111/order-service/order/allorder/byuser/";
    this.getInvoice="http://localhost:8111/order-service/pdf/getpdf/";
    this.postReviewApi="http://localhost:8111/product-service/postreview/";
   }

   getOrders():Observable<Orders[]>{
    let userId = localStorage.getItem('userId');
    return this.http.get<Orders[]>(this.getOrdersApi+userId,
      { headers:{ authorization:localStorage.getItem("auth") || "Not Available" }});
   }

   getDownloadedFile(orderId:string):Observable<HttpEvent<Blob>>{
    return this.http.get(this.getInvoice+orderId,
     {
      headers:{ authorization:localStorage.getItem("auth") || "Not Available" },
       reportProgress:true,
       observe:'events',
       responseType:'blob'
     });
    }

    postReview(productId:string,review:Reviews):Observable<Reviews>{
      return this.http.post<Reviews>(this.postReviewApi+productId+"/"+localStorage.getItem('userId')!
                  ,review)
    }
}
