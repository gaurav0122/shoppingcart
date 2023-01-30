export interface User{
  "id": string,
  "fullName": string,
  "email": string,
  "mobileNo": number,
  "role": string,
  "dateOfBirth"?: Date,
  "gender"?: string,
  "username"?: string,
  "password": string,
  "encodedPassword":string,
  "address": {
      "houseNumber"?: number,
      "streetName"?: string,
      "colonyName"?: string,
      "city"?: string,
      "state"?: string,
      "pinCode"?: number
  }
}

export interface UpdateUser{
  "fullName"?: string,
  "email"?: string,
  "mobileNo"?: number,
  "role"?: string,
  "dateOfBirth"?: Date,
  "gender"?: string,
  "password"?: string,
  "encodedPassword"?:string,
  "address": {
      "houseNumber"?: number,
      "streetName"?: string,
      "colonyName"?: string,
      "city"?: string,
      "state"?: string,
      "pinCode"?: number
  }
}
