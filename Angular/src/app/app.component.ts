import { Component } from '@angular/core';
import { AppService } from './app.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'shoppingcart1';

  constructor(private appService:AppService){

  }
  ngOnInit(): void {
    let token=localStorage.getItem('token');

    if(token){
      this.appService.loggedIn.next(true);
    }

}
}
