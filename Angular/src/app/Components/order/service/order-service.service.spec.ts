import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { CartserviceService } from '../../cart/service/cartservice.service';
import { Orders } from '../../myorders/Model/order.model';
import { postOrder } from '../model/postOrder.model';

import { OrderServiceService } from './order-service.service';

describe('OrderServiceService', () => {
  let orderService: OrderServiceService;
  let httpClientSpy : jasmine.SpyObj<HttpClient>;
  let order:Orders;
  const cartId:string="cart1";
  beforeEach(() => {

    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','put']);
    TestBed.configureTestingModule({
      providers:[OrderServiceService,
                {
                    provide:HttpClient,
                    useValue:httpClientSpyObj
                }]
    });

    orderService = TestBed.inject(OrderServiceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;

     order ={
        orderId: "oid2",
        orderDate: "22-01-2022",
        customerId: "cid2",
        amountPaid: 1200,
        paymentStatus: true,
        modeOfPayment: "Cash-On-Delivery",
        orderStatus: "placed",
        quantity: 1,
        items: [
            {
                productId: "pid2",
                productName: "pName",
                productImg: "PImg",
                price: 300,
                quantity: 4
            }
        ],
        address: {
            houseNumber: 42,
            streetName: "A Street",
            colonyName: "A Colony",
            city: "City",
            state: "State",
            pinCode: 414141
        }}
  });

  it('should be created', () => {
    expect(orderService).toBeTruthy();
  });

  it('place order method check', (done:DoneFn) => {
    const postOrder:postOrder={
      modeOfPayment:"Cash-On-Delivery",
      paymentStatus:true
    };

    httpClientSpy.post.and.returnValue(of(order));

    orderService.placeOrder(postOrder,cartId).subscribe({
      next:(data)=>{
        expect(data).toEqual(order);
        expect(data.paymentStatus).toEqual(order.paymentStatus);
        done();
      },
      error:(err)=>{
        done.fail;
      }
    });

    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });

  it('create order method check',(done:DoneFn)=>{
    let form:any={
      customerName: "cName",
      email: "email@email.com",
      phoneNumber: 9898981212,
      amount: 1200
    };
    let createOrder1:any={
      secretId:"asdfjhdf",
      razorpayOrderId:"rId1",
      applicationFee:1200,
      pgName:"Razor1"
    }

    httpClientSpy.post.and.returnValue(of(createOrder1));

    orderService.createOrder(form).subscribe({
      next:(data)=>{
        expect(data).toEqual(createOrder1);
        expect(data.applicationFee).toEqual(form.amount);
        done();
      },
      error:(err)=>{
        done.fail;
      }
    });

  })
});
