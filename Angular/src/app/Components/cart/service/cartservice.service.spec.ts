import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import {Cart} from "../../cart/model/cart.model"
import { CartserviceService } from './cartservice.service';



const cartTd:string="cart1";

describe('CartserviceService', () => {
  let service: CartserviceService;
  let htttpClientSpy:jasmine.SpyObj<HttpClient>;
  let cart:Cart;

  beforeEach(() => {
    let htttpClientSpyObj = jasmine.createSpyObj('HttpClient',['get','post','put','delete']);
    TestBed.configureTestingModule({
      providers:[CartserviceService,
                {
                  provide : HttpClient,
                  useValue : htttpClientSpyObj
                }]
    });
    service = TestBed .inject(CartserviceService);
    htttpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;

    cart={
      cartId: "cart1",
      totalPrice: 1200,
      userId: "user1",
      totalItems: 1,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 4
          }
        ]
      };

  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it("get the cart method check",(done:DoneFn)=>{

      htttpClientSpy.get.and.returnValue(of(cart));
      service.getCart().subscribe({
        next:(data)=>{
          expect(data).toEqual(cart);
          done();
        },
        error:(err)=> {
          done.fail;
        }
      })

      expect(htttpClientSpy.get).toHaveBeenCalledTimes(1);

  });


  it("post the cart method check",(done:DoneFn)=>{

      htttpClientSpy.post.and.returnValue(of(cart));
      service.addCart().subscribe({
        next:(data)=>{
          expect(data).toEqual(cart);
          done();
        },
        error:(err)=> {
          done.fail;
        }
      })

      expect(htttpClientSpy.post).toHaveBeenCalledTimes(1);

  });

  it("delete item the cart method check",(done:DoneFn)=>{
    const cartRest:Cart={
      cartId: "cart1",
      totalPrice: 1600,
      userId: "user1",
      totalItems: 2,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 4
          }
        ]
      };
    cartRest.items.push({
      productId: "pid2",
      productName: "pName2",
      productImg: "PImg2",
      price: 400,
      quantity: 1
  });

    cart.items.push({
        productId: "pid2",
        productName: "pName2",
        productImg: "PImg2",
        price: 400,
        quantity: 1
    });
    cart.totalItems=2;

    htttpClientSpy.put.and.returnValue(of(cart));
    cart.items.slice(1);
    cart.totalItems=1;
    service.deleteItemInCart(cartTd,"pid1").subscribe({
      next:(data)=>{
        expect(data).toEqual(cart);
        expect(data.totalItems).toEqual(cart.totalItems);
        expect(data.items.length).toEqual(cart.items.length);
        done();
      },
      error:(err)=> {
        done.fail;
      }
    })

    expect(htttpClientSpy.put).toHaveBeenCalledTimes(1);

  });

  it("Add quantity cart method check",(done:DoneFn)=>{
    const cartRest:Cart={
      cartId: "cart1",
      totalPrice: 1500,
      userId: "user1",
      totalItems: 1,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 5
          }
        ]
      };

    cart.items.at(0)!.quantity=cart.items.at(0)!.quantity+1;
    cart.totalPrice = cart.items.at(0)!.quantity * cart.items.at(0)!.price;
    htttpClientSpy.put.and.returnValue(of(cartRest));

    service.addQuantity(cartTd,"pid1").subscribe({
      next:(data)=>{
        expect(data).toEqual(cart);
        expect(data.items.at(0)!.quantity).toEqual(cart.items.at(0)!.quantity);
        expect(data.totalPrice).toEqual( cart.totalPrice);
        done();
      },
      error:(err)=> {
        done.fail;
      }
    })

    expect(htttpClientSpy.put).toHaveBeenCalledTimes(1);

  });

  it("Sub quantity cart method check",(done:DoneFn)=>{
    const cartRest:Cart={
      cartId: "cart1",
      totalPrice: 900,
      userId: "user1",
      totalItems: 1,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 3
          }
        ]
      };

    cart.items.at(0)!.quantity=cart.items.at(0)!.quantity-1;
    cart.totalPrice = cart.items.at(0)!.quantity * cart.items.at(0)!.price;
    htttpClientSpy.put.and.returnValue(of(cartRest));

    service.subQuantity(cartTd,"pid1").subscribe({
      next:(data)=>{
        expect(data).toEqual(cart);
        expect(data.items.at(0)!.quantity).toEqual(cart.items.at(0)!.quantity);
        expect(data.totalPrice).toEqual( cart.totalPrice);
        done();
      },
      error:(err)=> {
        done.fail;
      }
    })

    expect(htttpClientSpy.put).toHaveBeenCalledTimes(1);
  });

  it("Remove all Items from cart method check",(done:DoneFn)=>{
    const cartRest:Cart={
      cartId: "cart1",
      totalPrice: 900,
      userId: "user1",
      totalItems: 1,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 3
          }
        ]
      };
      cartRest.items.splice(0,cartRest.items.length);
      cartRest.totalItems=0;
      cartRest.totalPrice=0;

      cart.items.splice(0,cart.items.length);
      cart.totalItems=0;
      cart.totalPrice=0;
    htttpClientSpy.put.and.returnValue(of(cartRest));

    service.removeAllItems(cartTd).subscribe({
      next:(data)=>{
        expect(data).toEqual(cart);
        expect(data.items.length).toEqual(cart.items.length);
        expect(data.totalPrice).toEqual( cart.totalPrice);
        expect(data.totalItems).toEqual( cart.totalItems);
        done();
      },
      error:(err)=> {
        done.fail;
      }
    })

    expect(htttpClientSpy.put).toHaveBeenCalledTimes(1);
  });

});
