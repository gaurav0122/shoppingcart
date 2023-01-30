import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { AppService } from 'src/app/app.service';

import { ProductdetailsComponent } from './productdetails.component';
import { ProductdetailsserviceService } from './service/productdetailsservice.service';

describe('ProductdetailsComponent', () => {
  let component: ProductdetailsComponent;
  let fixture: ComponentFixture<ProductdetailsComponent>;
  let mockActRoute:any;
  let mockappService :any;
  let mockproductDetailsService :any;
  beforeEach(async () => {
    mockActRoute = jasmine.createSpyObj(ActivatedRoute,['']);
    mockappService = jasmine.createSpyObj(AppService,['']);
    mockproductDetailsService = jasmine.createSpyObj(ProductdetailsserviceService,['']);

    await TestBed.configureTestingModule({
      declarations: [ ProductdetailsComponent ],
      providers:[
        {
          provide:ProductdetailsserviceService,
          useValue:mockproductDetailsService
        },
        {
          provide:ActivatedRoute,
          useValue:mockActRoute
        },
        {
          provide:AppService,
          useValue:mockappService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductdetailsComponent);
    component = fixture.componentInstance;
  });

  // private router:Router
  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
