import {OrderDetail} from './order-detail';

export class Order {
    orderId: string;
    userId: string;
    orderDate: Date;
    orderDetails: OrderDetail[];
}
