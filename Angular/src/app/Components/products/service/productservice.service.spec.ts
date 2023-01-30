import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ProductDetails } from '../../productdetails/model/productdetails.model';

import { ProductserviceService } from './productservice.service';

describe('ProductserviceService', () => {
  let service: ProductserviceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient> ;
  const categoryName = "cName1";

  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','put']);

    TestBed.configureTestingModule({
      providers:[ProductserviceService,
                  {
                    provide:HttpClient,
                    useValue:httpClientSpyObj
                  }]
    });

    service = TestBed.inject(ProductserviceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('get all product by category should be called', (done:DoneFn) => {
    let myMap = new Map<string, string>([
      ["key1", "value1"],
      ["key2", "value2"]
    ]);
    const products:ProductDetails[] =[{
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
    },{
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
    ];

    httpClientSpy.get.and.returnValue(of(products));
    service.getAllProductByCategory(categoryName).subscribe({
      next:(data)=>{
        expect(data).toEqual(products);
        expect(data.length).toEqual(products.length)
        done();
      },
      error:(err)=> {
        done.fail;
      }
    });

    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });



});
