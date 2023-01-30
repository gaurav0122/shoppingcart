

export interface Product{
  productid?:string;
  productType?:string;
  productName?:string;
  category:Category1;
  image?:string[];
  price?:number;
  merchantName?:string;

}

export interface Category1{
  id?:string;
  categoryName?:string;
  categoryImg?:string;
}
