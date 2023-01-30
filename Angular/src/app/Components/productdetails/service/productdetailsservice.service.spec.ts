 import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Cart } from '../../cart/model/cart.model';
import { ProductDetails } from '../model/productdetails.model';

import { ProductdetailsserviceService } from './productdetailsservice.service';

describe('ProductdetailsserviceService', () => {
  let service: ProductdetailsserviceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
  const productId:string="pid1";
  let myMap = new Map<string, string>([
    ["key1", "value1"],
    ["key2", "value2"]
  ]);
  const product:ProductDetails ={

    productid:"pid1",
    productType:"pType1",
    productName:"pName1",
    category:{
      id:"cId1",
      categoryName:"cName1"
    },
    image:["pImg1","pImg2"],
    price:1200,
    description:"pDescription1",
    specification:myMap,
    merchantName:"mUser1"
  }
  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','get']);
    TestBed.configureTestingModule({
      providers:[ProductdetailsserviceService,
        {
          provide:HttpClient,
          useValue:httpClientSpyObj
        }]
    });
    service = TestBed.inject(ProductdetailsserviceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("get product details method should called",(done:DoneFn)=>{

    httpClientSpy.get.and.returnValue(of(product));
    service.getProductDetails(productId).subscribe({
      next:(data)=>{
        expect(data).toEqual(product);
        expect(data.productid).toEqual(productId);
        done();
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

  // it("add item to cart method should called",(done:DoneFn)=>{


  //   const cart:Cart={
  //     cartId: "cart1",
  //     totalPrice: 1200,
  //     userId: "user1",
  //     totalItems: 1,
  //     items: [
  //         {
  //             productId: "pid1",
  //             productName: "pName1",
  //             productImg: "PImg1",
  //             price: 1200,
  //             quantity: 1
  //         }
  //       ]
  //   }
  //   httpClientSpy.post.and.returnValue(of(cart));

  //   const userId:string = "user1";

  //   if(userId){
  //     service.addItemToCart(productId).subscribe({
  //             next:(data)=>{
  //               expect(data).toEqual(cart);
  //               expect(data.cartId).toEqual(cart.cartId);
  //               expect(data.items.length).toEqual(cart.items.length);
  //               done();
  //             }
  //        });
  //   }
  //   else{
  //     expect(Error);
  //   }
  //   expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  // });

});
