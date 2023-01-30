import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AppService } from 'src/app/app.service';
import swal from 'sweetalert';
import { ProductDetails } from './model/productdetails.model';
import { Reviews } from './model/review.model';
import { ProductdetailsserviceService } from './service/productdetailsservice.service';

@Component({
  selector: 'app-productdetails',
  templateUrl: './productdetails.component.html',
  styleUrls: ['./productdetails.component.css']
})
export class ProductdetailsComponent implements OnInit {

  loading:boolean = true;
  productId:string;
  product:ProductDetails;
  imageurl:string;
  reviews:Reviews[];
  totalRatings:number;
  star5:number;
  star4:number;
  star3:number;
  star2:number;
  star1:number;
  ratingStat:number;

  constructor(private actroute:ActivatedRoute,
              private productdetailsservice:ProductdetailsserviceService,
              private appService:AppService,
              private router:Router) {
    this.productId="";
    this.product={productid:"",
                  category:{}};
    this.imageurl="";
    this.reviews=[];
    this.totalRatings=0.0;
    this.star1=0;
    this.star3=0;
    this.star2=0;
    this.star4=0;
    this.star5=0;
    this.ratingStat=0.0;
  }

  ngOnInit(): void {

    this.actroute.params.subscribe(params=>{
      this.productId=params['pid'];
      console.log(this.productId);
      this.productdetailsservice.getProductDetails(this.productId).subscribe(
        data=>{
            this.product=data;
            this.imageurl=data.image?.at(0) || "";
            this.loading=false;
        }
      );

      this.productdetailsservice.getReviewByProductId(this.productId).subscribe(
        data=>{
          this.reviews=data;
          console.log(this.reviews);
          this.totalRatings=this.reviews.length;
          this.star1=this.reviews.filter(r=>r.ratings==1).length;
          this.star2=this.reviews.filter(r=>r.ratings===2).length;
          this.star3=this.reviews.filter(r=>r.ratings===3).length;
          this.star4=this.reviews.filter(r=>r.ratings===4).length;
          this.star5=this.reviews.filter(r=>r.ratings===5).length;
          this.ratingStat=((this.star1*1 + this.star2*2 + this.star3*3 +
                            this.star4*4 + this.star5*5) / this.totalRatings) || 0.0;
          this.reviews.reverse;
        }
      )
    })

  }

  showImg(image:string){
      this.imageurl=image;
  }

  addItemToCart(productId:string|any){
    try {
      this.productdetailsservice.addItemToCart(productId).subscribe(
        data=>{
          this.appService.cartItems.next(data.totalItems);
          swal( {
            text: "Item added in the cart",
             icon:"success"}
             );
        },
        error=>{
          swal({
            text: "Item already present in cart!",
            icon:"warning"
          });
          console.log(error.error.message);
        }
      );
    } catch (error) {
      swal({
        text: "You need to log-in to add Item in cart",
        icon:"error"
      })
      .then((value)=>{
        if(value)
        this.router.navigateByUrl("login");
      });
    }

  }

}
