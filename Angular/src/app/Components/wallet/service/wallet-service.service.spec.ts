import { fakeAsync, TestBed } from '@angular/core/testing';

import { WalletServiceService } from './wallet-service.service';
import { HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing'
import { HttpClient } from '@angular/common/http';
import { Observable, of, Subscriber } from 'rxjs';
import { Statements } from '../model/statements.model';

describe('WalletServiceService', () => {
  let walletservice: WalletServiceService;
  let httpClientSpy:jasmine.SpyObj<HttpClient>;

  beforeEach(() => {
    httpClientSpy = jasmine.createSpyObj('HttpClient', ['get','post']);

    walletservice = new WalletServiceService(httpClientSpy);

  });

  it('should be created', () => {
    expect(walletservice).toBeDefined();
  });

  it('call get Wallet method()',(done:DoneFn) => {
    const wallet={
      walletId: "string",
      currentBalance: 1000,
      userId: "string",
      userName: "string"
    }

    httpClientSpy.get.and.returnValue(of(wallet));
    walletservice.getWallet().subscribe({
      next:(data)=>{
        expect(data).toEqual(wallet);
        done();
      },
      error:()=>{
        done.fail;
      }
    });

    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);

  });

  it('call create Wallet method()',(done:DoneFn) => {
    const wallet={
      walletId: "string",
      currentBalance: 1000,
      userId: "string",
      userName: "string"
    }

    httpClientSpy.post.and.returnValue(of(wallet));
    walletservice.createWallet().subscribe({
      next:(data)=>{
        expect(data).toEqual(wallet);
        done();
      },
      error:()=>{
        done.fail;
      }
    });
    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });

  it('call payBywallet method()',(done:DoneFn) => {
    const wallet={
      walletId: "string",
      currentBalance: 1000,
      userId: "string",
      userName: "string"
    }

    let amount=100;
    let walletId="string";

    const wallet1={
      walletId: "string",
      currentBalance: wallet.currentBalance-amount,
      userId: "string",
      userName: "string"
    }

    httpClientSpy.post.and.returnValue(of(wallet1));
    walletservice.payBywallet(amount,walletId).subscribe({
      next:(data)=>{
        expect(data).toEqual(wallet1);
        expect(data.currentBalance).toEqual(wallet.currentBalance-amount);
        done();
      },
      error:()=>{
        done.fail;
      }
    });
    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });


  it('call addMoney method()',(done:DoneFn) => {
    const wallet={
      walletId: "string",
      currentBalance: 1000,
      userId: "string",
      userName: "string"
    }

    let amount=100;
    let walletId="string";

    const wallet1={
      walletId: "string",
      currentBalance: wallet.currentBalance+amount,
      userId: "string",
      userName: "string"
    }

    httpClientSpy.post.and.returnValue(of(wallet1));
    walletservice.addMoney(walletId,amount).subscribe({
      next:(data)=>{
        expect(data).toEqual(wallet1);
        expect(data.currentBalance).toEqual(wallet.currentBalance+amount);
        expect(data.userId).toEqual(wallet.userId);
        done();
      },
      error:()=>{
        done.fail;
      }
    });
    expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });

  it('call get statements method',(done:DoneFn)=>{

    let statements:Statements[]=[];
    statements=[{
      statementId:"i1",
      transactionType: "deposit",
      amount: 1000,
      date: new Date("2022-01-16") ,
      transactionRemarks: "done",
      ewalletId: "walletid"
      },
      {
        statementId:"id2",
        transactionType: "deposit",
        amount: 2000,
        date:new Date("2022-01-18"),
        transactionRemarks: "done",
        ewalletId: "walletid"
      }
    ];

    let walletId="walletid";
    httpClientSpy.get.and.returnValue(of(statements));

    walletservice.getStatements(walletId).subscribe({
      next:(data)=>{
        expect(data).toEqual(statements);
        expect(data.length).toEqual(statements.length);
        done();
      },
      error:()=>{
        done.fail;
      }
    });
    expect(httpClientSpy.get).toHaveBeenCalledTimes(1);
  });

  it("call crate order method", (done:DoneFn)=>{
    const payment={
     name:"name",
     email:"email@email.com",
     phoneNumber:"9090909090",
     amount:1000
    }

    const returnValue={
      secretKey : "key",
	    razorpayOrderId : "orderId",
	    applicationFee : "fees",
	    secretId : "secretId",
	    pgName : "pagename"
    };

    httpClientSpy.post.and.returnValue(of(returnValue));

    walletservice.createOrder(payment).subscribe({
      next:(data)=>{
        expect(data).toEqual(returnValue);
        expect(data.secretId).toEqual(returnValue.secretId);
        done();
      },
      error:()=>{
        done.fail;
      }
    })
      expect(httpClientSpy.post).toHaveBeenCalledTimes(1);
  });


});






