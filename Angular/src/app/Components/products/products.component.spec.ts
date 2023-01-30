import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { ProductsComponent } from './products.component';
import { ProductserviceService } from './service/productservice.service';

describe('ProductsComponent', () => {
  let component: ProductsComponent;
  let fixture: ComponentFixture<ProductsComponent>;
  let mockActRoute:any;
  let mockproductservice:any;

  beforeEach(async () => {
    mockActRoute = jasmine.createSpyObj(ActivatedRoute,['']);
    mockproductservice = jasmine.createSpyObj(ProductserviceService,['']);

    await TestBed.configureTestingModule({
      declarations: [ ProductsComponent ],
      providers:[
        {
          provide:ActivatedRoute,
          useValue:mockActRoute
        },
        {
          provide:ProductserviceService,
          useValue:mockproductservice
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductsComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });


});
