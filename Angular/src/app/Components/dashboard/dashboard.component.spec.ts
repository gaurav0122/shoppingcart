import { HttpErrorResponse } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of, throwError } from 'rxjs';


import { DashboardComponent } from './dashboard.component';
import { Category } from './model/CategoryModel';
import { Product } from './model/productModel';
import { DashboardserviceService } from './service/dashboardservice.service';

describe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  let mockcategoryService:any;

  beforeEach(async () => {
    mockcategoryService = jasmine.createSpyObj(DashboardserviceService,['getAllCategories','getAllProducts'])
    await TestBed.configureTestingModule({
      declarations: [ DashboardComponent ],
      providers:[
        {
          provide:DashboardserviceService,
          useValue:mockcategoryService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
   // fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should test ngOnInit', () => {
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
    ];

    const categories:Category[]=[
      { id:"id1",
      categoryImg:"img1",
      categoryName:"cName"
      }
    ];

    mockcategoryService.getAllProducts.and.returnValue(of(products));
    mockcategoryService.getAllCategories.and.returnValue(of(categories));
    fixture.detectChanges();

    expect(mockcategoryService.getAllCategories).toHaveBeenCalledTimes(1);
    expect(mockcategoryService.getAllProducts).toHaveBeenCalledTimes(1);

  });

  it('should test ngOnInit on error ', (done:DoneFn) => {
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
    ];

    const categories:Category[]=[
      { id:"id1",
      categoryImg:"img1",
      categoryName:"cName"
      }
    ];

    mockcategoryService.getAllProducts.and.returnValue(of(products));
    mockcategoryService.getAllCategories.and.returnValue(throwError(()=> new Error("get Error")));


    fixture.detectChanges();

    expect(mockcategoryService.getAllCategories).toThrowError;
    expect(mockcategoryService.getAllProducts).toHaveBeenCalledTimes(1);
    done();
  });



});
