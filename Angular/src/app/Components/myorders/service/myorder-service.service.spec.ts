import { HttpClient, HttpEvent, HttpEventType } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Orders } from "../Model/order.model"

import { MyorderServiceService } from './myorder-service.service';

describe('MyorderServiceService', () => {
  let service: MyorderServiceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
  const orderId="order1";

  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','put']);
    TestBed.configureTestingModule({
      providers:[MyorderServiceService,{
          provide:HttpClient,
          useValue:httpClientSpyObj
      }]
    });
    service = TestBed.inject(MyorderServiceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("get all Orders",(done:DoneFn)=>{

    let orders:Orders[]=[
      {orderId: "oid1",
      orderDate: "22-01-2022",
      customerId: "cid1",
      amountPaid: 1200,
      paymentStatus: true,
      modeOfPayment: "Cash-On-Delivery",
      orderStatus: "placed",
      quantity: 1,
      items: [
          {
              productId: "pid1",
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
      }},
      {orderId: "oid2",
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
    ];

    httpClientSpy.get.and.returnValue(of(orders));

    service.getOrders().subscribe({
      next:(data)=>{
        expect(data).toEqual(orders);
        expect(data.length).toEqual(orders.length);
        done();
      },
      error:(err) =>{
        done.fail;
      }
    })

    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

  // it("get Downloaded File method check",(done:DoneFn)=>{
  //   let expectedData: HttpEvent<Blob> ;
  //   httpClientSpy.post.and.returnValue(of(expectedData));

  //   service.getDownloadedFile(orderId).subscribe({
  //     next:(data)=>{
  //       expect(data).toEqual(expectedData);

  //     },
  //     error:(err)=>{

  //     }
  //   })
  // })


});
