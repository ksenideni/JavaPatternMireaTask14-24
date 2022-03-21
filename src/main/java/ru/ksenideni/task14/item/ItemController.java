package ru.ksenideni.task14.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView remove(@PathVariable Long orderId, @PathVariable Long itemId) {
        itemService.deleteItemById(orderId, itemId);
        return new RedirectView("/api/v1/orders/" + orderId + "/items");
    }

    @PostMapping
    public Item create(@PathVariable Long orderId, @RequestBody Item item) {
        return itemService.create(orderId, item);
    }
}
