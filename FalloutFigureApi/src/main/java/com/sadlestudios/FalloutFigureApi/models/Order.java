package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class Order {
    private UUID orderId;
    private UUID userId;
    private Date orderDate;

    private List<OrderDetail> orderDetail;

    public Order() { }
    public Order(UUID orderId, UUID userId, Date orderDate, List<OrderDetail> orderDetail) {
        this.orderId = orderId;
        this.userId = userId;
        this.orderDate = orderDate;
        this.orderDetail = orderDetail;
    }
}
