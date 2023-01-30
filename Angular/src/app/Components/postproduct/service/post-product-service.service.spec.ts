import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { ProductDetails} from '../../productdetails/model/productdetails.model'
import { PostProductServiceService } from './post-product-service.service';

describe('PostProductServiceService', () => {
  let service: PostProductServiceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
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
      providers:[PostProductServiceService,
                {
                  provide:HttpClient,
                  useValue:httpClientSpyObj
                }]
    });
    service = TestBed.inject(PostProductServiceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('post product method should be called', (done:DoneFn) => {

    httpClientSpy.post.and.returnValue(of(product));
    service.postProduct(product).subscribe({
      next:(data)=>{
        expect(data).toEqual(product);
        expect(data.productid).toEqual(product.productid);
        done();
      },
      error:(err)=>{
        done.fail;
      }
    });
    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);

  });

});
