import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppService } from 'src/app/app.service';
import { CartserviceService } from '../cart/service/cartservice.service';
import { of } from 'rxjs';
import { LoginComponent } from './login.component';
import { LoginserviceService } from './service/loginservice.service';
import { User } from './Model/user.model';

describe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let mockloginService:any;
  let mockappService:any;
  let mockcartService:any;

  beforeEach(async () => {
    mockloginService = jasmine.createSpyObj(LoginserviceService,['authenticationService','getUserByEmail']);
    mockappService = jasmine.createSpyObj(AppService,['loggedIn']);
    mockcartService = jasmine.createSpyObj(CartserviceService,[''])
    await TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      providers:[
        {
          provide:LoginserviceService,
          useValue:mockloginService
        },
        {
          provide:AppService,
          useValue:mockappService
        },
        {
          provide:CartserviceService,
          useValue:mockcartService
        }

      ]
    })
    .compileComponents();
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
   fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should test onsubmit method', () => {

    mockloginService.authenticationService.and.returnValue(of(undefined));
    mockloginService.getUserByEmail.and.returnValue(of());
    component.onSubmit();
    expect(component.invalidLogin).toBe(false);
    expect(component.loginSuccess).toBe(true);
    expect(component.setData).toHaveBeenCalled;
  });


  it('should test setData method', () => {

    const user:User={
      address:{},
      email:"email",
      encodedPassword:"vjdvsa",
      fullName:"abc",
      id:"aaaa",
      mobileNo:9090909090,
      password:"abc",
      role:"Merchant"
    };
    const email:string="email";
    const password:string="password";
    component.loginSuccess=false;
    mockloginService.getUserByEmail.and.returnValue(of(user));

    component.setData(email,password);
    expect(mockloginService.getUserByEmail).toHaveBeenCalledTimes(0);
    expect(component.errorMsg).toEqual("Invalid creditionls");

  });


});
