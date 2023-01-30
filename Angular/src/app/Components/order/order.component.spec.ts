import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppService } from 'src/app/app.service';
import { CartserviceService } from '../cart/service/cartservice.service';
import { ProfileServiceService } from '../profile/service/profile-service.service';
import { WalletServiceService } from '../wallet/service/wallet-service.service';

import { OrderComponent } from './order.component';
import { OrderServiceService } from './service/order-service.service';

describe('OrderComponent', () => {
  let component: OrderComponent;
  let fixture: ComponentFixture<OrderComponent>;
  let mockwalletService:any;
  let mockcartService:any;
  let mockappService:any;
  let mockprofileService:any;
  let mockorderService:any;


  beforeEach(async () => {
    mockwalletService = jasmine.createSpyObj(WalletServiceService,['']);
    mockcartService = jasmine.createSpyObj(CartserviceService,['']);
    mockappService = jasmine.createSpyObj(AppService,['']);
    mockprofileService = jasmine.createSpyObj(ProfileServiceService,['']);
    mockorderService = jasmine.createSpyObj(OrderServiceService,['']);


    await TestBed.configureTestingModule({
      declarations: [ OrderComponent ],
      providers:[
        {
          provide:WalletServiceService,
          useValue:mockwalletService
        },
        {
          provide:CartserviceService,
          useValue:mockcartService
        },
        {
          provide:AppService,
          useValue:mockappService
        },
        {
          provide:ProfileServiceService,
          useValue:mockprofileService
        },
        {
          provide:OrderServiceService,
          useValue:mockorderService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderComponent);
    component = fixture.componentInstance;
   // fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
