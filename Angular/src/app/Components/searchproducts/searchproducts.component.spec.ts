import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { ProductserviceService } from '../products/service/productservice.service';
import { of } from 'rxjs';
import { SearchproductsComponent } from './searchproducts.component';
import { SearchproductService } from './service/searchproduct.service';

describe('SearchproductsComponent', () => {
  let component: SearchproductsComponent;
  let fixture: ComponentFixture<SearchproductsComponent>;
  let mockActRoute:any;
  let mocksearchproductService :any;


  beforeEach(async () => {
  mockActRoute = jasmine.createSpyObj(ActivatedRoute,['params']);
  mocksearchproductService = jasmine.createSpyObj(SearchproductService,['']);
    await TestBed.configureTestingModule({
      declarations: [ SearchproductsComponent ],
      providers:[
        {
          provide:SearchproductService,
          useValue:mocksearchproductService
        },
        {
          provide:ActivatedRoute,
          useValue:mockActRoute
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SearchproductsComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    mockActRoute.params.and.returnValue(of("abc"));
    expect(component).toBeTruthy();
  });
});
