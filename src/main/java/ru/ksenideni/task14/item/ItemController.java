package ru.ksenideni.task14.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/orders/{orderId}/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems(@PathVariable Long orderId) {
        return itemService.getAllItems(orderId);
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @DeleteMapping("/{itemId}")
    public List<Item> remove(@PathVariable Long orderId, @PathVariable Long itemId) {
        return itemService.deleteItemById(orderId, itemId);
    }

    @PostMapping
    public Item create(@PathVariable Long orderId, @RequestBody Item item) {
        return itemService.create(orderId, item);
//        return itemService.getItemById(orderId, item.getId());
    }


}
