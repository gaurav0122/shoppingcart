import { ComponentFixture, TestBed } from '@angular/core/testing';
import { WalletServiceService } from './service/wallet-service.service';
import { of } from 'rxjs';
import { WalletComponent } from './wallet.component';
import { Statements } from './model/statements.model';

describe('WalletComponent', () => {
  let component: WalletComponent;
  let fixture: ComponentFixture<WalletComponent>;
  let mockwalletService:any;

  beforeEach(async () => {
    mockwalletService = jasmine.createSpyObj(WalletServiceService,['getWallet','createWallet','getStatements']);

    await TestBed.configureTestingModule({
      declarations: [ WalletComponent ],
      providers:[
        {
          provide:WalletServiceService,
          useValue:mockwalletService
        }
      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(WalletComponent);
    component = fixture.componentInstance;

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should check ngOninit create', () => {
    const wallet={
      "walletId": "",
      "currentBalance": 0.0,
      "userId": ""
    }

    mockwalletService.getWallet.and.returnValue(of(wallet))
    mockwalletService.createWallet.and.returnValue(of(wallet))

   fixture.detectChanges();
  });


  it('should check showStatements method', () => {
    const statements:Statements[] =[];

    mockwalletService.getStatements.and.returnValue(of(statements))

    component.showStatements();
    expect(mockwalletService.getStatements).toHaveBeenCalledTimes(1);

  });


});
