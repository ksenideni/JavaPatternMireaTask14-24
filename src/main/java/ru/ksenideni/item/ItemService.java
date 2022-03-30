package ru.ksenideni.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ksenideni.EmailService;
import ru.ksenideni.order.Order;
import ru.ksenideni.order.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final OrderRepository orderRepository;
    private final EmailService emailService;

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
        Item i =  itemRepository.save(item);
        emailService.sendSimpleMessage(i);
        return i;
    }

    public void deleteItemById(Long itemId) {
        log.info("Remove item with id = {} in order", itemId);
        itemRepository.deleteById(itemId);
    }
}
