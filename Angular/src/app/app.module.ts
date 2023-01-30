import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http'
import { AppRoutingModule } from './app-routing.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { FooterComponentApp } from './Components/footer/footer.component';
import { HeaderComponentApp } from './Components/header/header.component';
import { DashboardComponent } from './Components/dashboard/dashboard.component';
import { LoginComponent } from './Components/login/login.component';
import { ProductdetailsComponent } from './Components/productdetails/productdetails.component';
import { ProductsComponent } from './Components/products/products.component';
import { RegisterComponent } from './Components/register/register.component';
import { CartComponent } from './Components/cart/cart.component';
import { ProfileComponent } from './Components/profile/profile.component';
import { OrderComponent } from './Components/order/order.component';
import { MyordersComponent } from './Components/myorders/myorders.component';
import { WalletComponent } from './Components/wallet/wallet.component';
import { PostproductComponent } from './Components/postproduct/postproduct.component';
import { OrderplacedComponent } from './Components/orderplaced/orderplaced.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatSliderModule } from '@angular/material/slider';
import { MatInputModule } from '@angular/material/input';
import { MatBadgeModule } from '@angular/material/badge';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { SearchproductsComponent } from './Components/searchproducts/searchproducts.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponentApp,
    FooterComponentApp,
    DashboardComponent,
    LoginComponent,
    ProductdetailsComponent,
    ProductsComponent,
    RegisterComponent,
    CartComponent,
    ProfileComponent,
    OrderComponent,
    MyordersComponent,
    WalletComponent,
    PostproductComponent,
    OrderplacedComponent,
    SearchproductsComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatInputModule,
    MatBadgeModule,
    MatButtonModule,
    MatIconModule,
    NgxPaginationModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
