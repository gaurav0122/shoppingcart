export interface Statements{
    "statementId":string,
    "transactionType": string,
    "amount": number,
    "date": Date,
    "orderId"?: number,
    "transactionRemarks"?: string,
    "ewalletId": string
}
