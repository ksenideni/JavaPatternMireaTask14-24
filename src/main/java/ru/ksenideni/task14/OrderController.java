package ru.ksenideni.task14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public String create(@RequestBody Order order){
        orderService.create(order);
        return orderService.getOrderById(order.getId()).toString();
    }








}
