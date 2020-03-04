package com.sadlestudios.FalloutFigureApi.entities;

import com.sadlestudios.FalloutFigureApi.models.OrderDetail;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity
@Table(name = "orderDetails")
public class OrderDetailEntity {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_DETAIL_ID")
    private UUID orderDetailId;

    @Column(name = "ORDER_ID")
    private UUID orderId;

    @Column(name = "FIGURE_ID")
    private UUID figureId;

    @Column(name = "ITEM_COUNT")
    private int itemCount;

    public OrderDetailEntity() { }
    public OrderDetailEntity(UUID orderDetailId, UUID orderId, UUID figureId, int itemCount) {
        this.orderDetailId = orderDetailId;
        this.orderId = orderId;
        this.figureId = figureId;
        this.itemCount = itemCount;
    }

    /**
     * Returns this object as an OrderDetail, with a null Figure
     */
    public OrderDetail toOrderDetail() {
        return new OrderDetail(this.orderDetailId, this.orderId, null, this.itemCount);
    }
}
