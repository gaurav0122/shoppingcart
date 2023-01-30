import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { User } from '../Model/user.model';

import { LoginserviceService } from './loginservice.service';

describe('LoginserviceService', () => {
  let service: LoginserviceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;

  const username:string = "user1";
  const password:string = "password";

  const email:string = "email@email.com";
  let user:User;

  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','delete','put']);
    TestBed.configureTestingModule({
      providers:[LoginserviceService,
                    {
                      provide:HttpClient,
                      useValue:httpClientSpyObj
                    }]
    });
    service = TestBed.inject(LoginserviceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;

    user = {
      id: "id1",
      fullName: "email@email.com",
      email: "uEmail",
      mobileNo: 9999999999,
      role: "Merchant",
      gender: "male",
      username: "username",
      password: "password",
      encodedPassword:"encodedpass",
      address: {
          houseNumber: 12,
          streetName: "aStreet",
          colonyName: "aColony",
          city: "aCity",
          state: "Astate",
          pinCode: 414141
      }
    }
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });


  it('create auth token method check', (done:DoneFn) => {
    let tokenCheck:string = "Basic "+window.btoa(username+":"+password)
    let token:string = service.createBasicAuthToken(username,password);
    expect(token).toEqual(tokenCheck);
    done();
  });

  it('register Successful Login method check', (done:DoneFn) => {
    service.registerSuccessfulLogin(username,password);
    expect().nothing;
    done();
  });

  it('get User By Email Id method check', (done:DoneFn) => {

    httpClientSpy.get.and.returnValue(of(user));
    service.getUserByEmail(email).subscribe({
      next:(data)=>{
        expect(data).toEqual(user);
        expect(data.email).toEqual(user.email);
        done();
      },
      error:(err)=>{
        done.fail;
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

});
