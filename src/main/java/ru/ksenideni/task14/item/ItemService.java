package ru.ksenideni.task14.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ksenideni.task14.order.Order;
import ru.ksenideni.task14.order.OrderRepository;

import java.util.List;

@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, OrderRepository orderRepository) {
        this.itemRepository = itemRepository;
        this.orderRepository = orderRepository;
    }

    public List<Item> getAllItems(Long orderId) {
        return orderRepository.findById(orderId).get().getItemList();
    }

    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).get();
    }

    public Item create(Long orderId, Item item) {

        Order order = orderRepository.findById(orderId).get();
        item.setOrder(order);
        return itemRepository.save(item);
    }

    public void deleteItemById(Long itemId) {
        itemRepository.deleteById(itemId);
    }
}
