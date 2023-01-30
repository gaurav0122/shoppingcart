import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../../login/Model/user.model';
import { UserReg } from '../model/user.model';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  registerUserApi:string;
  constructor(private http:HttpClient) {
    this.registerUserApi="http://localhost:8111/profile-service/user/user"
  }

  registerUser(user:UserReg):Observable<UserReg>{
    return this.http.post<UserReg>(this.registerUserApi,user);
  }
}
