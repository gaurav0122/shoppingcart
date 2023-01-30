import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { map } from 'rxjs';
import { Category } from '../dashboard/model/CategoryModel';
import { DashboardserviceService } from '../dashboard/service/dashboardservice.service';
import { ProductDetails } from '../productdetails/model/productdetails.model';
import { PostProductServiceService } from './service/post-product-service.service';

@Component({
  selector: 'app-postproduct',
  templateUrl: './postproduct.component.html',
  styleUrls: ['./postproduct.component.css']
})
export class PostproductComponent implements OnInit {

  categories:Category[];
  productForm:FormGroup;
  product:ProductDetails;
  specMap:Map<string,string>;

  constructor(private dashboardService:DashboardserviceService, private postProductService:PostProductServiceService) {
    this.categories=[];
    this.specMap= new Map();
    this.productForm = new FormGroup({
      "productname":new FormControl("",Validators.required),
      "productType":new FormControl("",Validators.required),
      "productPrice":new FormControl(Validators.required),
      "productCategory":new FormControl("",Validators.required),
      "productImage1":new FormControl("",Validators.required),
      "productImage2":new FormControl(),
      "productImage3":new FormControl(),
      "productImage4":new FormControl(),
      "productDescription":new FormControl("",Validators.required),
      "SpecTitle1":new FormControl(),
      "SpecTitle2":new FormControl(),
      "SpecTitle3":new FormControl(),
      "SpecTitle4":new FormControl(),
      "SpecTitle5":new FormControl(),
      "SpecDesc1":new FormControl(),
      "SpecDesc2":new FormControl(),
      "SpecDesc3":new FormControl(),
      "SpecDesc4":new FormControl(),
      "SpecDesc5":new FormControl()
    }
    );

    this.product={
      category:{}
    }
  }

  ngOnInit(): void {
    this.dashboardService.getAllCategories().subscribe(data=>{
      this.categories=data;
      console.log(data);
    })
  }

  onSubmit(){

    this.product.productName = this.productForm.value.productname;
    this.product.productType = this.productForm.value.productType;
    this.product.price = this.productForm.value.productPrice;
    this.product.image=[];
    if(this.productForm.value.productImage1)
      this.product.image.push(this.productForm.value.productImage1);
    if(this.productForm.value.productImage2)
     this.product.image.push(this.productForm.value.productImage2);
    if(this.productForm.value.productImage3)
      this.product.image.push(this.productForm.value.productImage3);
    if(this.productForm.value.productImage4)
     this.product.image.push(this.productForm.value.productImage4);

    // this.product.image =[this.productForm.value.productImage1,
    //                       this.productForm.value.productImage2,
    //                       this.productForm.value.productImage3,
    //                       this.productForm.value.productImage3] ;
    this.product.description = this.productForm.value.productDescription;

    this.product.category.categoryName=this.productForm.value.productCategory;
    if(this.productForm.value.SpecTitle1 && this.productForm.value.SpecDesc1)
      this.specMap.set(this.productForm.value.SpecTitle1,this.productForm.value.SpecDesc1)

    if(this.productForm.value.SpecTitle2 && this.productForm.value.SpecDesc2)
      this.specMap.set(this.productForm.value.SpecTitle2,this.productForm.value.SpecDesc2)

    if(this.productForm.value.SpecTitle3 && this.productForm.value.SpecDesc3)
      this.specMap.set(this.productForm.value.SpecTitle3,this.productForm.value.SpecDesc3)

    if(this.productForm.value.SpecTitle4 && this.productForm.value.SpecDesc4)
      this.specMap.set(this.productForm.value.SpecTitle4,this.productForm.value.SpecDesc4)

    if(this.productForm.value.SpecTitle5 && this.productForm.value.SpecDesc5)
     this.specMap.set(this.productForm.value.SpecTitle5,this.productForm.value.SpecDesc5)

     this.product.specification = this.specMap;

    let convMap:any = {};
     this.specMap.forEach((val: string, key: string) => {
        convMap[key] = val;
      });

    this.product.specification=convMap;
    console.log(convMap);
    console.log(this.product.specification);
    this.postProductService.postProduct(this.product).subscribe(data=>{
      alert("Product posted succesfully");
      console.log(data);
    },
    error=>{
      console.log("postProduct Error");
    }
    );

  }

}
