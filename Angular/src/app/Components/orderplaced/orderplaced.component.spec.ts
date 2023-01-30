import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';

import { OrderplacedComponent } from './orderplaced.component';
import { OrderplacedService } from './service/orderplaced.service';

describe('OrderplacedComponent', () => {
  let component: OrderplacedComponent;
  let fixture: ComponentFixture<OrderplacedComponent>;
  let mockorderplacedService:any;
  let mockActRoute:any;

  beforeEach(async () => {

    mockorderplacedService = jasmine.createSpyObj(OrderplacedService,['']);
    mockActRoute = jasmine.createSpyObj(ActivatedRoute,['']);

    await TestBed.configureTestingModule({
      declarations: [ OrderplacedComponent ],
      providers:[
        {
          provide:OrderplacedService,
          useValue:mockorderplacedService
        },
        {
          provide:ActivatedRoute,
          useValue:mockActRoute
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderplacedComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
