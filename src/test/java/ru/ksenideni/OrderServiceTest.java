package ru.ksenideni;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.ksenideni.order.Order;
import ru.ksenideni.order.OrderRepository;
import ru.ksenideni.order.OrderService;

import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepository orderRepository;
    @Captor
    ArgumentCaptor<Long> captorId;

    @Test
    void getOrders() {
        Order order1 = new Order();
        order1.setOrderDate("29.03.22");
        Order order2 = new Order();
        order2.setOrderDate("20.03.22");

        Mockito.when(orderRepository.findAll()).thenReturn(List.of(order1, order2));

        OrderService orderService = new OrderService(orderRepository);
        Assertions.assertEquals(2, orderService.getAllOrders().size());

    }

    @Test
    void deleteOrderById(){
        Order order1 = new Order();
        order1.setOrderDate("29.03.22");
        order1.setId(1L);

        OrderService orderService = new OrderService(orderRepository);

        //when
        orderService.deleteOrderById(order1.getId());

        //then
        Mockito.verify(orderRepository).deleteById(captorId.capture());
    }

}
