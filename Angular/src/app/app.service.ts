import { Token } from "@angular/compiler";
import { Injectable } from "@angular/core";
import { BehaviorSubject, Observable} from "rxjs";
import { Cart } from "./Components/cart/model/cart.model";
import { CartserviceService } from "./Components/cart/service/cartservice.service";


@Injectable({
    providedIn: 'root'
  })
export class AppService {

  loggedIn = new BehaviorSubject<boolean>(false);
  isMerchant = new BehaviorSubject<boolean>(false);
  userName = new BehaviorSubject<string|null>("");

  cartItems = new BehaviorSubject<number>(0);

    constructor(private cartService:CartserviceService) {
      let user = localStorage.getItem('auth');
      let userName = localStorage.getItem('userName');
      let role = localStorage.getItem('userRole');
      if(user){
        this.loggedIn.next(true);
        this.userName.next(userName);
        this.cartService.getCart().subscribe(data=>{
          this.cartItems.next(data.totalItems);
        })
        if(role === "Merchant"){
          this.isMerchant.next(true);
        }
      }

    }

    ngOnInit(): void {


    }

}
