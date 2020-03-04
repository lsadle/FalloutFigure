package com.sadlestudios.FalloutFigureApi.services;

import com.sadlestudios.FalloutFigureApi.entities.OrderDetailEntity;
import com.sadlestudios.FalloutFigureApi.models.Figure;
import com.sadlestudios.FalloutFigureApi.models.OrderDetail;
import com.sadlestudios.FalloutFigureApi.repositories.FigureRepository;
import com.sadlestudios.FalloutFigureApi.repositories.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final FigureRepository figureRepository;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, FigureRepository figureRepository) {
        this.orderDetailRepository = orderDetailRepository;
        this.figureRepository = figureRepository;
    }
    /**
     * Get one OrderDetail by its id
     * @param id The id of the OrderDetail you want
     */
    public OrderDetail Get(UUID id) {
        var entity = orderDetailRepository.getOne(id);
        var figure = figureRepository.getOne(entity.getFigureId());

        return new OrderDetail(entity.getOrderDetailId(), entity.getOrderId(), figure.toFigure(), entity.getItemCount());
    }

    /**
     * Get all OrderDetails with the specified orderId
     * @param orderId The orderId you want to search by
     */
    public List<OrderDetail> GetByOrderId(UUID orderId) {
        var entities = orderDetailRepository.findByOrderId(orderId);
        var figureIds = entities.stream().map(e -> e.getFigureId()).collect(Collectors.toList());
        var figures = figureRepository.findByFigureIdIsIn(figureIds);

        var orderDetails = new ArrayList<OrderDetail>();

        for (var entity : entities) {
            // Convert entity to Dto
            var orderDetail = entity.toOrderDetail();

            // Get corresponding Figure
            var figureEntity = figures.stream().filter(f -> f.getFigureId().equals(entity.getFigureId())).findFirst().orElse(null);
            Figure figure = null;

            // Null check (◔_◔)
            if (figureEntity != null) {
                figure = figureEntity.toFigure();
            }

            orderDetail.setFigure(figure);
            orderDetails.add(orderDetail);
        }

        return  orderDetails;
    }

    /**
     * Creates a new OrderDetail
     * @param newOrderDetail The OrderDetail you want to create
     */
    public OrderDetail Create(OrderDetail newOrderDetail) {
        var entity = new OrderDetailEntity(null, newOrderDetail.getOrderId(), newOrderDetail.getFigure().getFigureId(), newOrderDetail.getItemCount());
        orderDetailRepository.saveAndFlush(entity);
        newOrderDetail.setOrderId(entity.getOrderId());
        return newOrderDetail;
    }
}
