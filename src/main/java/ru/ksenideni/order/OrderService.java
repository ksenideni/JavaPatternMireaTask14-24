package ru.ksenideni.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ksenideni.EmailService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
//    private final EmailService emailService;

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
        Order o= orderRepository.save(order);
//        emailService.sendSimpleMessage(o);
        return o;
    }

    public void deleteOrderById(Long orderId) {
        log.info("Remove order with id = {} in order", orderId);
        orderRepository.deleteById(orderId);
    }
}
