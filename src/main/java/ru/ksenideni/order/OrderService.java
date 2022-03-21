package ru.ksenideni.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        log.info("Find all orders");
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        log.info("Find order by id = {}", id);
        return orderRepository.findById(id).get();
    }

    public Order create(Order order) {
        log.info("Save order");
        return orderRepository.save(order);
    }

    public void deleteOrderById(Long orderId) {
        log.info("Remove order with id = {} in order", orderId);
        orderRepository.deleteById(orderId);
    }
}
