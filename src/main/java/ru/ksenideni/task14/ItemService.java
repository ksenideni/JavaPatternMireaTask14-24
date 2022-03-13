package ru.ksenideni.task14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
    private final OrderService orderService;
    @Autowired
    public ItemService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Item> getAllItems(Long orderId) {
        return orderService.getOrderById(orderId).getItemList();
    }

    public Item getItemById(Long orderId, Long itemId) {
        return orderService.getOrderById(orderId).getItemList().stream().filter(o->{
            return o.getId()==itemId;
        }).findAny().get();
    }

    public void create(Long orderId, Item item) {
        orderService.getOrderById(orderId).getItemList().add(item);
    }
}
