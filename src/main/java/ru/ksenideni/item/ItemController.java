package ru.ksenideni.item;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/orders/{orderId}/items")
public class ItemController {
    private final ItemService itemService;

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
        itemService.deleteItemById(itemId);
        return new RedirectView("/api/v1/orders/" + orderId + "/items");
    }

    @PostMapping
    public Item create(@PathVariable Long orderId, @RequestBody Item item) {
        return itemService.create(orderId, item);
    }
}
