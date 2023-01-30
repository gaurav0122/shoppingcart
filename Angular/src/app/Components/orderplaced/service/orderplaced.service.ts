import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Orders } from '../../myorders/Model/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderplacedService {

  getOrderDetailsApi:string;
  sendEmailApi:string;

  constructor(private http:HttpClient) {
    this.getOrderDetailsApi="http://localhost:8111/order-service/order/allorder/";
    this.sendEmailApi ="http://localhost:8111/order-service/email/";
   }

   getOrderDetails(orderId:string){
   return this.http.get<Orders>(this.getOrderDetailsApi+orderId,
            {headers:{authorization:localStorage.getItem('auth')!}});
   }

   sendEmail(orderId:string){
    return this.http.get<boolean>(this.sendEmailApi+orderId,
             {headers:{authorization:localStorage.getItem('auth')!}});
    }
}
