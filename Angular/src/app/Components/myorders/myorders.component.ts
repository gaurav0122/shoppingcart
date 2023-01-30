import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Orders } from './Model/order.model';
import { saveAs } from 'file-saver';
import { MyorderServiceService } from './service/myorder-service.service';
import { FormControl, FormGroup } from '@angular/forms';
import { MatFormFieldControl } from '@angular/material/form-field';
import { Reviews } from '../productdetails/model/review.model';
import swal from 'sweetalert';

@Component({
  selector: 'app-myorders',
  templateUrl: './myorders.component.html',
  styleUrls: ['./myorders.component.css']
})
export class MyordersComponent implements OnInit {

  orders:Orders[];
  reviewForm:FormGroup;
  productId:string;
  loading:boolean = true;
  rat:number;

  constructor(private myOrderSerive:MyorderServiceService) {
    this.orders=[];
    this.productId="";
    this.rat=3;
    this.reviewForm = new FormGroup(
      {
        "rating":new FormControl(),
        "reviewTitle":new FormControl(),
        "reviewText":new FormControl(),
      }
    );
  }

  ngOnInit(): void {
    this.myOrderSerive.getOrders().subscribe(data=>{
      this.orders= data;
      console.log(data);
      this.loading=false;
    },
    error=>{
      console.log(error);
      console.log("orders error");
    }

    );
  }


  downloadInvoice(orderId:string){
    console.log("downloadInvoice");

    this.myOrderSerive.getDownloadedFile(orderId).subscribe(
      event=>{
        console.log(event);
        this.reportProgress(event);
      },
      error=>{
        console.log(error);
      }
    )

  }


  reportProgress(httpEvent:HttpEvent<Blob>){
    switch(httpEvent.type){
      case HttpEventType.ResponseHeader:
        console.log('Header Returned', httpEvent);
        break;

      case HttpEventType.Response:
        console.log(httpEvent.headers)
        console.log(httpEvent.headers.get('File-Name'));
        saveAs(new File([httpEvent.body!], "Invoice",
                  {type: "application/pdf"}));
        break;

      default:
        console.log(httpEvent.type)
    }
  }

  setProductId(productId:string){
    this.productId = productId;
    console.log(this.productId);
  }
  onsubmit(){


    let review:Reviews={
      ratings : this.reviewForm.value.rating,
      reviewTitle:this.reviewForm.value.reviewTitle,
      reviewText:this.reviewForm.value.reviewText
    }
    console.log(this.reviewForm);
    this.myOrderSerive.postReview(this.productId,review).subscribe(data=>{
      console.log(data);
      swal("Review Posted","","success")
    },
    error=>{
      console.log(error);
    });

  }

  value(){
      this.rat = this.reviewForm.value.rating;
     // console.log(this.rat);
  }
}
