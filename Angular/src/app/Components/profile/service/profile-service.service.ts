import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UpdateUser, User } from '../../login/Model/user.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileServiceService {

  getUserDetailsApi:string;
  updateUserApi:string;

  constructor(private http:HttpClient) {

    this.getUserDetailsApi="http://localhost:8111/profile-service/user/user/";
    this.updateUserApi="http://localhost:8111/profile-service/user/user/";
  }

  getUserDetails(userId: string |null ) :Observable<User>{
    let authToken = localStorage.getItem("auth");
    return this.http.get<User>(this.getUserDetailsApi+userId,
      {headers:{authorization: authToken!}});
  }

  updateUser(userId: string |null,userDetails:UpdateUser):Observable<User>{
    let authToken = localStorage.getItem("auth");
    return this.http.put<User>(this.updateUserApi+userId,userDetails,
      {headers:{authorization: authToken!}});
  }
}
