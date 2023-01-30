import { HttpEvent, HttpEventType, HttpResponse } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of, throwError } from 'rxjs';
import { Reviews } from '../productdetails/model/review.model';
import { Orders } from './Model/order.model';

import { MyordersComponent } from './myorders.component';
import { MyorderServiceService } from './service/myorder-service.service';

describe('MyordersComponent', () => {
  let component: MyordersComponent;
  let fixture: ComponentFixture<MyordersComponent>;
  let MockMyorderService:any;
  let orders:Orders[];
  beforeEach(async () => {
    MockMyorderService = jasmine.createSpyObj(MyorderServiceService,['getOrders','getDownloadedFile','postReview']);
    await TestBed.configureTestingModule({
      declarations: [ MyordersComponent ],
      providers:[
        {
          provide:MyorderServiceService,
          useValue:MockMyorderService
        }

      ]

    })
    .compileComponents();

    fixture = TestBed.createComponent(MyordersComponent);
    component = fixture.componentInstance;
    orders=[
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
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('test ngOninit', () => {
    MockMyorderService.getOrders.and.returnValue(of(orders));
    fixture.detectChanges();

    expect(MockMyorderService.getOrders).toHaveBeenCalledTimes(1);
  });

  it('test ngOninit getorder method error', () => {
    MockMyorderService.getOrders.and.returnValue(throwError(()=>new Error("method error")));
    fixture.detectChanges();

    expect(MockMyorderService.getOrders).toThrowError;
  });

  it('test downloadInvoice getDownloadedFile method error', () => {
    const orderId:string="order1";
    MockMyorderService.getDownloadedFile.and.returnValue(throwError(()=>new Error("method error")));
    component.downloadInvoice(orderId);

    expect(MockMyorderService.getDownloadedFile).toThrowError;
  });

  it('test downloadInvoice getDownloadedFile method', () => {
    const orderId:string="order1";
    MockMyorderService.getDownloadedFile.and.returnValue(of(HttpEventType));
    component.downloadInvoice(orderId);

    expect(MockMyorderService.getDownloadedFile).toHaveBeenCalledTimes(1);
  });



  it('test setProductId method', () => {
    const productId:string="product1";
    component.setProductId(productId);
    expect(component.productId).toBe("product1");
  });

  // onsubmit(){


  //   let review:Reviews={
  //     ratings : this.reviewForm.value.rating,
  //     reviewTitle:this.reviewForm.value.reviewTitle,
  //     reviewText:this.reviewForm.value.reviewText
  //   }

  //   this.myOrderSerive.postReview(this.productId,review).subscribe(data=>{
  //     console.log(data);
  //     swal("Review Posted","","success")
  //   },
  //   error=>{
  //     console.log(error);
  //   });

  // }

  it('test onsubmit method', () => {
    let review:Reviews={
      ratings : 2,
      reviewTitle:"Good Product",
      reviewText:"Very good Product"
    }
    MockMyorderService.postReview.and.returnValue(of(review));
    component.onsubmit();

    expect(MockMyorderService.postReview).toHaveBeenCalledTimes(1);

  });

  it('test onsubmit method error handle', () => {
    let review:Reviews={
      ratings : 2,
      reviewTitle:"Good Product",
      reviewText:"Very good Product"
    }
    MockMyorderService.postReview.and.returnValue(throwError(()=>{"post review error"}));
    component.onsubmit();

    expect(MockMyorderService.postReview).toThrowError;

  });

});
