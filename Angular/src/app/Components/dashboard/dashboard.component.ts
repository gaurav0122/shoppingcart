import { Component, OnInit } from '@angular/core';
import { Category } from './model/CategoryModel';
import { Product } from './model/productModel';
import { DashboardserviceService } from './service/dashboardservice.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  categories:Category[];
  products:Product[];
  dropdown:string;


  constructor(private categoryService:DashboardserviceService) {
    this.categories =[];
    this.products=[];
    this.dropdown="";
  }

  ngOnInit(): void {
    this.categoryService.getAllCategories().subscribe(data=>{
      this.categories=data;
    },
    error=>{
      console.log(error);
    });

    this.categoryService.getAllProducts().subscribe(data=>{
      this.products=data;

    });
  }

}
