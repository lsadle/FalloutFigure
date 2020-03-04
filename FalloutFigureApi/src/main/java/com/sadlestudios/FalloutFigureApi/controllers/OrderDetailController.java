package com.sadlestudios.FalloutFigureApi.controllers;

import com.sadlestudios.FalloutFigureApi.models.OrderDetail;
import com.sadlestudios.FalloutFigureApi.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("orderDetail")
public class OrderDetailController {
    private final OrderDetailService orderDetailService;

    @Autowired
    public OrderDetailController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/{id}")
    public OrderDetail Get(@PathVariable("id")UUID id) {
        return orderDetailService.Get(id);
    }

    @GetMapping("/byOrderId/{orderId}")
    public List<OrderDetail> GetByOrderId(@PathVariable("orderId")UUID orderId) {
        return orderDetailService.GetByOrderId(orderId);
    }
}
