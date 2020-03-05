import {Address} from './address';

export class User {
  userId: string;
  password: string;
  username: string;
  shippingAddress: Address;
  billingAddress: Address;
}

