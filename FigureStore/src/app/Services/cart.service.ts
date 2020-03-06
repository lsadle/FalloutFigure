import {EventEmitter, Injectable} from '@angular/core';
import {OrderDetail} from '../ApiModels/order-detail';
import {Order} from '../ApiModels/order';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  itemAddedEvent = new EventEmitter();
  constructor() { }

  addToCart(orderDetail: OrderDetail) : void {
    const cartOrderJson = sessionStorage.getItem('cartOrder');
    let order: Order;

    if (cartOrderJson == null) {
      order = new Order();
      order.userId = sessionStorage.getItem('userId');
      order.orderDate = new Date();
      order.orderDetails = [];
    } else {
      order = JSON.parse(cartOrderJson);
    }

    order.orderDetails.push(orderDetail);
    sessionStorage.setItem('cartOrder', JSON.stringify(order));
    this.itemAddedEvent.emit();
  }

  getItemCount(): number {
    const cartOrderJson = sessionStorage.getItem('cartOrder');
    let count = 0;

    if (cartOrderJson != null) {
      const order: Order = JSON.parse(cartOrderJson);
      count = order.orderDetails.length;
    }

    return count;
  }
}
