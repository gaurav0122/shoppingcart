import { ThisReceiver } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Orders } from '../myorders/Model/order.model';
import { OrderplacedService } from './service/orderplaced.service';

@Component({
  selector: 'app-orderplaced',
  templateUrl: './orderplaced.component.html',
  styleUrls: ['./orderplaced.component.css']
})
export class OrderplacedComponent implements OnInit {

  order:Orders;
  orderId:string;


  constructor(private orderplacedservice:OrderplacedService,private actRoute:ActivatedRoute) {
    this.orderId="";
    this.order={
        orderId:"",
        address:{ city:"",
                  colonyName:"",
                  houseNumber:10,
                  pinCode:0,
                  state:"",
                  streetName:""},
        items:[{price:0,
                productId:"",
                productImg:"",
                productName:"",
                quantity:0}],
    }

   }

  ngOnInit(): void {
    this.actRoute.params.subscribe(params=>{
      this.orderId=params["orderId"];
      console.log(this.orderId)
      this.orderplacedservice.getOrderDetails(this.orderId).subscribe(data=>{
        this.order=data;
      })

      this.orderplacedservice.sendEmail(this.orderId).subscribe(data=>{
      })

    })

  }



}
