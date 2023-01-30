import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../products/model/Product.model';
import { SearchproductService } from './service/searchproduct.service';

@Component({
  selector: 'app-searchproducts',
  templateUrl: './searchproducts.component.html',
  styleUrls: ['./searchproducts.component.css']
})
export class SearchproductsComponent implements OnInit {

  products:Product[];
  searchtitle:string;
  loading:boolean = true;
  isEmpty:boolean = false;

  constructor(private actRoute:ActivatedRoute,private searchProductService:SearchproductService) {
    this.products=[];
    this.searchtitle="";
  }

  ngOnInit(): void {

    this.actRoute.params.subscribe(
      params=>{
        console.log(params['searchtitle']);
        this.searchtitle=params['searchtitle'];
        this.searchProductService.getProductsBySearch( this.searchtitle).subscribe(
          data=>{
            this.products=data;
            console.log(this.products);
            this.loading=false;
            if(data.length===0){
              this.isEmpty=true;
            }
          }
        )
      }
    );


  }

}
