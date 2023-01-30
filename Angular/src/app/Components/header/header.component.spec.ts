import { HttpClient } from '@angular/common/http';
import { ComponentFixture, TestBed, tick } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { of } from 'rxjs';
import { AppService } from 'src/app/app.service';
import { CartserviceService } from '../cart/service/cartservice.service';
import { Category } from '../dashboard/model/CategoryModel';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';

import { HeaderComponentApp } from './header.component';

describe('HeaderComponent', () => {
  let component: HeaderComponentApp;
  let fixture: ComponentFixture<HeaderComponentApp>;
  let mockAppService:any;
  let mockDashboardService:any;
  let routerSpy:any;
  let router:any;

  const categories:Category[]=[
    {
      id:"catid1",
      categoryName:"catName1",
      categoryImg:"catImg"
    }
  ]

  beforeEach(async () => {
    mockAppService = jasmine.createSpyObj(AppService,['cartItems']);
    routerSpy = jasmine.createSpyObj(Router, ['navigateByUrl']);
    mockDashboardService = jasmine.createSpyObj(DashboardserviceService,['getAllCategories','getAllProductTypes'])
    await TestBed.configureTestingModule({
      declarations: [ HeaderComponentApp ],
      providers:[
        {
          provide:AppService ,
          useValue:mockAppService
        },
        {
          provide:DashboardserviceService,
          useValue:mockDashboardService
        },
        { provide: Router,
          useValue: routerSpy
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderComponentApp);
    component = fixture.componentInstance;
    router = fixture.debugElement.injector.get(Router);
   // fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  // it('should create', () => {
  //   mockDashboardService.getAllCategories.and.returnValue(of(categories));

  //   fixture.detectChanges();
  //   expect(mockDashboardService.getAllCategories).toHaveBeenCalledTimes(1);

  // });

    it('should test getProductTypes method', () => {
      const categoryid:string = "cat1";
      const productTypes:string[]=["abc","pqr"];
    mockDashboardService.getAllProductTypes.and.returnValue(of(productTypes));
    component.getProductTypes(categoryid);
    expect(mockDashboardService.getAllProductTypes).toHaveBeenCalledTimes(1);
    expect(component.productTypes.length).toEqual(2);



  });

  it('should test getProductTypes method', () => {
    const productTypes:string[]=["abc","pqr"];
    component.productTypes=productTypes;
    expect(component.productTypes.length).toEqual(2);
     component.clear();

    expect(component.productTypes.length).toEqual(0);
    });

    it('should test searchItems method', () => {
      const spy = router.navigateByUrl as jasmine.Spy;
      component.searchItems();
      const navArgs = spy.calls.first().args[0];
      expect(navArgs).toBe("searchproducts/"+component.searchForm.value.searchTitle);
    });


  it('should test categoryClick method', () => {
    const categoryName="catName";

    const spy = router.navigateByUrl as jasmine.Spy;
    component.categoryClick(categoryName);
    const navArgs = spy.calls.first().args[0]
    expect(navArgs).toBe("/products/"+categoryName);
  });

  it('should test toggle method', () => {

    const spy = router.navigateByUrl as jasmine.Spy;
    component.toggle();
    const navArgs = spy.calls.first().args[0]
    expect(navArgs).toBe("cart");
  });

});
