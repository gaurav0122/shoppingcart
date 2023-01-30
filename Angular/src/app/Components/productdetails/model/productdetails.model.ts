export interface ProductDetails{

  productid?:string;
  productType?:string;
  productName?:string;
  category:Category1;
  image?:string[];
  price?:number;
  description?:string;
  specification?:Map<string,string>;
  merchantName?:string;
}


export interface Category1{
  id?:string;
  categoryName?:string;
}
