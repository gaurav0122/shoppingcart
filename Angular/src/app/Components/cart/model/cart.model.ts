export interface Cart{
    "cartId": string,
    "totalPrice": number,
    "userId": string,
    "totalItems": number,
    "items": [
        {
            "productId": string,
            "productName": string,
            "productImg": string,
            "price": number,
            "quantity": number
        }
    ]
}
