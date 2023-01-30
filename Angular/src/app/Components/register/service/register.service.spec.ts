import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { UserReg } from '../model/user.model';

import { RegisterService } from './register.service';

describe('RegisterService', () => {
  let service: RegisterService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','put','delete'])
    TestBed.configureTestingModule({
      providers:[RegisterService,
          {
            provide:HttpClient,
            useValue:httpClientSpyObj
          }]
    });
    service = TestBed.inject(RegisterService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('register User method check', (done:DoneFn) => {
    let date:Date = new Date("22-10-2022");
    const user:UserReg={
      fullName: "uName",
      email: "uEmail",
      mobileNo: 9999999999,
      role: "Merchant",
      dateOfBirth: date,
      gender: "male",
      password: "password",
      address: {
          houseNumber: 12,
          streetName: "aStreet",
          colonyName: "aColony",
          city: "aCity",
          state: "Astate",
          pinCode: 414141
      }
    };

    httpClientSpy.post.and.returnValue(of(user));

    service.registerUser(user).subscribe({
      next:(data)=>{
        expect(data).toEqual(user);
        expect(data.email).toEqual(user.email);
        done();
      },
      error:(err)=>{
        done.fail;
      }
    });

    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });


});
