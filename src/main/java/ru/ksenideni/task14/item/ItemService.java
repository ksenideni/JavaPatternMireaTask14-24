package ru.ksenideni.task14.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ksenideni.task14.order.Order;
import ru.ksenideni.task14.order.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;

    public List<Item> getAllItems(Long orderId) {
        log.info("Find all items in order with id = {}", orderId);
        return orderRepository.findById(orderId).get().getItemList();
    }

    public Item getItemById(Long itemId) {
        log.info("Find item by id = {}", itemId);
        return itemRepository.findById(itemId).get();
    }

    public Item create(Long orderId, Item item) {
        log.info("Save item in order with id = {}", orderId);
        Order order = orderRepository.findById(orderId).get();
        item.setOrder(order);
        return itemRepository.save(item);
    }

    public void deleteItemById(Long itemId) {
        log.info("Remove item with id = {} in order", itemId);
        itemRepository.deleteById(itemId);
    }
}
