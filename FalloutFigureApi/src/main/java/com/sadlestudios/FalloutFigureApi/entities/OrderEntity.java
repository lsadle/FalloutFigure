package com.sadlestudios.FalloutFigureApi.entities;

import com.sadlestudios.FalloutFigureApi.models.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private UUID orderId;

    @Column(name = "USER_ID")
    private UUID userId;

    @Column(name = "ORDER_DATE")
    private Date orderDate;

    public OrderEntity() { }
    public OrderEntity(UUID userId, Date orderDate) {
        this.userId = userId;
        this.orderDate = orderDate;
    }

    /**
     * Returns this object as an Order, with a null OrderDetails
     */
    public Order toOrder() {
        return new Order(this.orderId, this.userId, this.orderDate, null);
    }
}
