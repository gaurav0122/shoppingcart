import { HttpClient } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { Category } from '../model/CategoryModel';
import { Product } from '../model/productModel';

import { DashboardserviceService } from './dashboardservice.service';


describe('DashboardserviceService', () => {
  let dashboardservice: DashboardserviceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
  let categories:Category[];
  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','put','delete']);
    TestBed.configureTestingModule({
      providers:[DashboardserviceService,
      {
        provide:HttpClient,
        useValue:httpClientSpyObj
      }]
    });
    dashboardservice = TestBed.inject(DashboardserviceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
    categories=[
      {
        id:"cid1",
        categoryName:"cName1",
        categoryImg:"cImg1"
      },
      {
        id:"cid2",
        categoryName:"cName2",
        categoryImg:"cImg2"
      }
    ]
  });

  it('should be created', () => {
    expect(dashboardservice).toBeTruthy();
  });

  it('get All categories method check', (done:DoneFn) => {

    httpClientSpy.get.and.returnValue(of(categories));

    dashboardservice.getAllCategories().subscribe({
      next:(data)=>{
        expect(data).toEqual(categories);
        expect(data.length).toEqual(categories.length);
        done();
      },
      error:(error)=>{
        done.fail;
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

  it('get All products method check', (done:DoneFn) => {

    const products:Product[]=[
      { productid:"pid1",
        productType:"ptype1",
        productName:"pNmae1",
        category:{
          id:"cid1",
          categoryName:"cName1",
          categoryImg:"cImg1"
           },
        image:["PImage1"],
        price:1200,
        merchantName:"User1"
      }
    ]
    httpClientSpy.get.and.returnValue(of(products));

    dashboardservice.getAllProducts().subscribe({
      next:(data)=>{
        expect(data).toEqual(products);
        expect(data.length).toEqual(products.length);
        done();
      },
      error:(error)=>{
        done.fail;
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

  it('get All getAllProductTypes method check', (done:DoneFn) => {

    const productTypes:string[]=[
    "pType1","pType2"
    ]
    const categoryid:string="catId1";

    httpClientSpy.get.and.returnValue(of(productTypes));

    dashboardservice.getAllProductTypes(categoryid).subscribe({
      next:(data)=>{
        expect(data).toEqual(productTypes);
        expect(data.length).toEqual(productTypes.length);
        done();
      },
      error:(error)=>{
        done.fail;
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

});
