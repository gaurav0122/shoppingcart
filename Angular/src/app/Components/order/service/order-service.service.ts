import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CartserviceService } from '../../cart/service/cartservice.service';
import { Orders } from '../../myorders/Model/order.model';
import { postOrder } from '../model/postOrder.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({
  providedIn: 'root'
})
export class OrderServiceService {

  placeOrderApi:string;
  constructor(private http:HttpClient,private cartService:CartserviceService) {

    this.placeOrderApi="http://localhost:8111/order-service/order/order/";


  }


  placeOrder(order:postOrder,cartId:string):Observable<Orders>{
    return this.http.post<Orders>(this.placeOrderApi+cartId,order,
              {headers:{authorization:localStorage.getItem("auth")!}});
  }

  createOrder(order:any): Observable<any> {
		return this.http.post("http://localhost:8080/pg/createOrder", {
		customerName: order.name,
		email: order.email,
		phoneNumber: order.phone,
		amount: order.amount
		}, httpOptions);
	}



}
