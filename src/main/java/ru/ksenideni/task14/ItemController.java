package ru.ksenideni.task14;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/orders/{orderId}/items")
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<Item> getItems(@PathVariable Long orderId){
        return itemService.getAllItems(orderId);
    }

    @GetMapping("/{itemId}")
    public Item getItem(@PathVariable Long orderId, @PathVariable Long itemId){
        return itemService.getItemById(orderId, itemId);
    }

    @PostMapping
    public Item create(@PathVariable Long orderId, @RequestBody Item item){
        itemService.create(orderId, item);
        return itemService.getItemById(orderId, item.getId());
    }








}
