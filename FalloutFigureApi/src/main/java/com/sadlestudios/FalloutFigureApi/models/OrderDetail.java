package com.sadlestudios.FalloutFigureApi.models;

import lombok.Data;
import java.util.UUID;

@Data
public class OrderDetail {
    private UUID orderDetailId;
    private UUID orderId;
    private Figure figure;
    private int itemCount;

    public OrderDetail() { }
    public OrderDetail(UUID orderDetailId, UUID orderId, Figure figure, int itemCount) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.figure = figure;
        this.itemCount = itemCount;
    }
}
