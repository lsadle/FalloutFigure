package com.sadlestudios.FalloutFigureApi.controllers;

import com.sadlestudios.FalloutFigureApi.models.Order;
import com.sadlestudios.FalloutFigureApi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public Order Get(@PathVariable("id")UUID id) {
        return orderService.Get(id);
    }

    @GetMapping("/byUserId/{userId}")
    public List<Order> GetByUserId(@PathVariable("userId") UUID userId) {
        return orderService.GetByUser(userId);
    }

    @PostMapping("/")
    public Order Create(@RequestBody Order newOrder) {
        return orderService.Create(newOrder);
    }
}
