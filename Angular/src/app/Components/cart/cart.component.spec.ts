import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AppService } from 'src/app/app.service';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';
import { ignoreElements, of } from 'rxjs';
import { CartComponent } from './cart.component';
import { CartserviceService } from './service/cartservice.service';
import { Cart } from './model/cart.model';
import { ProductDetails } from '../productdetails/model/productdetails.model';

describe('CartComponent', () => {
  let component: CartComponent;
  let fixture: ComponentFixture<CartComponent>;
  let mockcartService:any;
  let mockappService:any;
  let cart:Cart;
  const productId:string="pid1";
  const cartId:string="cart1";
  const quantity:number=4;

  beforeEach(async () => {

    mockcartService = jasmine.createSpyObj(CartserviceService,['getCart','addCart','deleteItemInCart','addQuantity','subQuantity']);
    mockappService = jasmine.createSpyObj(AppService,[''])

    await TestBed.configureTestingModule({
      declarations: [ CartComponent ],
      providers:[
        {
          provide:CartserviceService,
          useValue:mockcartService
        },
        {
          provide:AppService,
          useValue:mockappService
        }

      ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CartComponent);
    component = fixture.componentInstance;

    cart={
      cartId: "cart1",
      totalPrice: 1200,
      userId: "user1",
      totalItems: 1,
      items: [
          {
              productId: "pid1",
              productName: "pName",
              productImg: "PImg",
              price: 300,
              quantity: 4
          }
        ]
      };

  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should test ngOinit method', () => {
    let myMap = new Map<string, string>([
      ["key1", "value1"],
      ["key2", "value2"]
    ]);
    const products:ProductDetails[] =[{
      productid:"pid1",
      productType:"pType1",
      productName:"pName1",
      category:{
        id:"cId1",
        categoryName:"cName1"
      },
      image:["pImg1","pImg2"],
      price:1200,
      description:"pDescription1",
      specification:myMap,
      merchantName:"mUser1"
    },{
      productid:"pid1",
      productType:"pType1",
      productName:"pName1",
      category:{
        id:"cId1",
        categoryName:"cName1"
      },
      image:["pImg1","pImg2"],
      price:1200,
      description:"pDescription1",
      specification:myMap,
      merchantName:"mUser1"
    }
    ];

    mockcartService.getCart.and.returnValue(of(cart));

   // mockcartService.addCart.and.returnValue(of(cart));



    // mockcartService.getCart.and.getValue(of(cart)).subscribe((data: any)=>{
    //   if(data===null){
    //     mockcartService.addCart.and.returnValue(of(cart));
    //   }
    // });
    fixture.detectChanges();

    expect(mockcartService.getCart).toHaveBeenCalledTimes(1);
    //expect(mockcartService.addCart).toHaveBeenCalledTimes(1);

  });

  // it("Should check the deleteItem method ",(done:DoneFn)=>{
  //   mockcartService.deleteItemInCart.and.returnValue(of(cart));
  //   component.deleteItem(productId);

  //  // mockappService.cartItems.and.setValue(2);
  //   expect(mockcartService.deleteItemInCart).toHaveBeenCalledTimes(1);
  //   done();
  // });

   it("Should check the addquantity method ",(done:DoneFn)=>{

    mockcartService.addQuantity.and.returnValue(of(cart));
    component.addQuantity(cartId,quantity);
    expect(mockcartService.addQuantity).toHaveBeenCalledTimes(1);
    done();
  });

  it("Should check the subquantity method ",(done:DoneFn)=>{

    mockcartService.subQuantity.and.returnValue(of(cart));
    component.subQuantity(cartId,quantity);
    if(quantity==0){
      expect(mockcartService.subQuantity).toHaveBeenCalledTimes(0);
    }
    expect(mockcartService.subQuantity).toHaveBeenCalledTimes(1);

    done();
  });
});
