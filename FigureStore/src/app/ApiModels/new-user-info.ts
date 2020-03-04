import {Address} from './address';

export class NewUserInfo {
  plainTextPassword: string;
  username: string;
  billingAddress: Address;
  shippingAddress: Address;

  constructor(username: string, plainTextPassword: string, billingAddress: Address, shippingAddress: Address) {
    this.username = username;
    this.plainTextPassword = plainTextPassword;
    this.billingAddress = billingAddress;
    this.shippingAddress = shippingAddress;
  }
}
