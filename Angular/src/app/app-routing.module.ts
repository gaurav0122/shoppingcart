import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartComponent } from './Components/cart/cart.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { LoginComponent } from './Components/login/login.component';
import { ProductdetailsComponent } from './Components/productdetails/productdetails.component';
import { ProductsComponent } from './Components/products/products.component';
import { RegisterComponent } from './Components/register/register.component';
import { AuthGuard } from './Components/login/service/auth.guard.service'
import { OrderComponent } from './Components/order/order.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { MyordersComponent } from './Components/myorders/myorders.component';
import { WalletComponent } from './Components/wallet/wallet.component';
import { PostproductComponent } from './Components/postproduct/postproduct.component';
import { OrderplacedComponent } from './Components/orderplaced/orderplaced.component';
import { SearchproductsComponent } from './Components/searchproducts/searchproducts.component';

const routes: Routes = [
  {path:"", component:DashboardComponent},
  {path:"login", component:LoginComponent},
  {path:"products/:cid",component:ProductsComponent},
  {path:"products/details/:pid",component:ProductdetailsComponent},
  {path:"register",component:RegisterComponent},
  {path:"cart",component:CartComponent,canActivate:[AuthGuard]},
  {path:"profile",component:ProfileComponent,canActivate:[AuthGuard]},
  {path:"order",component:OrderComponent,canActivate:[AuthGuard]},
  {path:"myorders",component:MyordersComponent,canActivate:[AuthGuard]},
  {path:"wallet",component:WalletComponent,canActivate:[AuthGuard]},
  {path:"postproduct",component:PostproductComponent,canActivate:[AuthGuard]},
  {path:"orderplaced/:orderId",component:OrderplacedComponent,canActivate:[AuthGuard]},
  {path:"searchproducts/:searchtitle",component:SearchproductsComponent}
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
