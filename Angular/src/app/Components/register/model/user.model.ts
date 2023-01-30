export interface UserReg{
  "fullName": string,
  "email": string,
  "mobileNo": number,
  "role": string,
  "dateOfBirth"?: Date,
  "gender": string,
  "password": string,
  "address": {
    "houseNumber"?: number,
    "streetName"?: string,
    "colonyName"?: string,
    "city"?: string,
    "state"?: string,
    "pinCode"?: number
}
}
