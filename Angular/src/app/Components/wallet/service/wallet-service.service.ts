import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Statements } from '../model/statements.model';
import { wallet } from '../model/wallet.model';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

@Injectable({
  providedIn: 'root'
})
export class WalletServiceService {

  getWalletApi:string;
  createWalletApi:string;
  payByWalletApi:string;
  getStatementsApi:string;
  paymentApi:string;
  addMoneyApi:string;
  httpHeader:HttpHeaders;

  constructor(private http:HttpClient) {
    this.getWalletApi="http://localhost:8111/wallet-service/api/wallets/byuser/";
    this.createWalletApi="http://localhost:8111/wallet-service/api/wallet/";
    this.payByWalletApi="http://localhost:8111/wallet-service/api/wallet/pay/";
    this.getStatementsApi="http://localhost:8111/wallet-service/api/wallet/statements/";
    this.paymentApi="http://localhost:8080/pg/createOrder";
    this.addMoneyApi="http://localhost:8111/wallet-service/api/wallet/";
    this.httpHeader = new HttpHeaders(
                        {authorization : localStorage.getItem('auth')||""});
   }

  getWallet():Observable<wallet>{
    let userId = localStorage.getItem('userId');
    return this.http.get<wallet>(this.getWalletApi+userId,{headers:this.httpHeader});
  }

  createWallet():Observable<wallet>{
    let userId = localStorage.getItem('userId');
    return this.http.post<wallet>(this.createWalletApi+userId,{},{headers:this.httpHeader});
  }

  payBywallet(amount:number, walletId:string){
    return this.http.post<wallet>(this.payByWalletApi+amount+"/"+walletId,{},{headers:this.httpHeader});
  }

  getStatements(walletId:string):Observable<Statements[]>{
    return this.http.get<Statements[]>(this.getStatementsApi+walletId,{headers:this.httpHeader});
  }

  createOrder(payment:any):Observable<any>{
    return this.http.post(this.paymentApi, {
		customerName: payment.name,
		email: payment.email,
		phoneNumber: payment.phone,
		amount: payment.amount
		}, httpOptions);
  }

  addMoney(walletId:string,amount:number):Observable<wallet>{
    return this.http.post<wallet>(this.addMoneyApi+walletId+"/"+amount,{},{headers:this.httpHeader});

  }
}
