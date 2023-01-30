import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Orders } from '../../myorders/Model/order.model'
import { OrderplacedService } from './orderplaced.service';

describe('OrderplacedService', () => {
  let service: OrderplacedService;
  let httpClientSpy: jasmine.SpyObj<HttpClient>;
  const orderId = "orderId"

  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get'])
    TestBed.configureTestingModule({
      providers:[OrderplacedService,
      {
          provide:HttpClient,
          useValue:httpClientSpyObj
      }]});
    service = TestBed.inject(OrderplacedService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('get the order details', (done:DoneFn) => {
    const order:Orders={
      orderId: "oid1",
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
      }};

      httpClientSpy.get.and.returnValue(of(order));

      service.getOrderDetails(orderId).subscribe({
        next:(data)=>{
          expect(data).toEqual(order);
          expect(data.orderId).toEqual(order.orderId)
          done();
        },
        error:(err) =>{
          done.fail;
        },
      });

      expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });


});
