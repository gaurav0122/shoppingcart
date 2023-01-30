import { HttpClient } from '@angular/common/http';
import { TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { UpdateUser, User } from '../../login/Model/user.model';

import { ProfileServiceService } from './profile-service.service';

describe('ProfileServiceService', () => {
  let service: ProfileServiceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;
  beforeEach(() => {
    let httpClientSpyObj = jasmine.createSpyObj(HttpClient,['get','post','put','delete'])
    TestBed.configureTestingModule({
      providers:[ProfileServiceService,
          {
            provide:HttpClient,
            useValue:httpClientSpyObj
          }]
    });
    service = TestBed.inject(ProfileServiceService);
    httpClientSpy = TestBed.inject(HttpClient) as jasmine.SpyObj<HttpClient>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('get User Details method shold be called', (done:DoneFn) => {
    const userId:string="user1";
    let date:Date = new Date("22-10-2022");
    const user:User={
      id: userId,
      fullName: "uName",
      email: "uEmail",
      mobileNo: 9999999999,
      role: "Merchant",
      dateOfBirth: date,
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

    httpClientSpy.get.and.returnValue(of(user));
    service.getUserDetails(userId).subscribe({
      next:(data)=>{
        expect(data).toEqual(user);
        expect(data.id).toEqual(userId);
        done();
      },
      error:()=>{
        done.fail;
      }
    });

    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });


  it('update user method shold be called', (done:DoneFn) => {
    const userId:string="user1";
    let date:Date = new Date("22-10-2022");
    const user:UpdateUser={
      fullName: "uName",
      dateOfBirth: date,
      gender: "male",
      address: {
          pinCode: 414141
      }
    }

    const userRest:User={
      id: userId,
      fullName: "uName",
      email: "uEmail",
      mobileNo: 9999999999,
      role: "Merchant",
      dateOfBirth: date,
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
    };

    httpClientSpy.put.and.returnValue(of(userRest));

    service.updateUser(userId,user).subscribe({
      next:(data)=>{
        expect(data).toEqual(userRest);
        expect(data.id).toEqual(userId);
        expect(data.fullName).toEqual(user.fullName!);
        done();
      },
      error:()=>{
        done.fail;
      }
    });

    expect(httpClientSpy.put).toHaveBeenCalledTimes(1);
  });


});
