package com.sadlestudios.FalloutFigureApi.services;

import com.sadlestudios.FalloutFigureApi.entities.OrderEntity;
import com.sadlestudios.FalloutFigureApi.models.Order;
import com.sadlestudios.FalloutFigureApi.repositories.OrderRepository;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Configuration
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailService orderDetailService;

    public OrderService(OrderRepository orderRepository, OrderDetailService orderDetailService) {
        this.orderRepository = orderRepository;
        this.orderDetailService = orderDetailService;
    }

    /**
     * Get one Order by its id
     * @param id The id of the Order you want
     */
    public Order Get(UUID id) {
        var entity = orderRepository.getOne(id);
        var order = entity.toOrder();
        var orderDetails = orderDetailService.GetByOrderId(id);
        order.setOrderDetail(orderDetails);

        return order;
    }

    /**
     * Get all Orders with th specified userId
     * @param userId The id of the User you want to get Orders for
     */
    public List<Order> GetByUser(UUID userId) {
        // Get entities
        var entities = orderRepository.findByUserId(userId);
        var orders = new ArrayList<Order>();

        for (var entity: entities) {
            var order = entity.toOrder();
            var orderDetails = orderDetailService.GetByOrderId(order.getOrderId());
            order.setOrderDetail(orderDetails);
            orders.add(order);
        }

        return orders;
    }

    /**
     * Creates a new Order and associated OrderDetails in the database
     * @param newOrder The order you wish to create
     */
    public Order Create(Order newOrder) {
        // Create the Order in the database
        var entity = new OrderEntity(newOrder.getUserId(), newOrder.getOrderDate());
        orderRepository.saveAndFlush(entity);
        newOrder.setOrderId(entity.getOrderId());

        // Set the orderId for all sub OrderDetail objects and then save them to the database
        for (var orderDetail : newOrder.getOrderDetail()) {
            orderDetail.setOrderId(newOrder.getOrderId());
            orderDetailService.Create(orderDetail);
        }

        return newOrder;
    }
}
