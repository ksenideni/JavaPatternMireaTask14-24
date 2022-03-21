package ru.ksenideni.task14.criterias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ksenideni.task14.item.Item;
import ru.ksenideni.task14.item.ItemRepository;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/items")
public class TestCriteriaController {
    private final ItemRepository itemRepository;

    @Autowired
    public TestCriteriaController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @GetMapping("/greater")
    public List<Item> getItemsWithPriceMoreThan(@RequestParam int price) {
        return itemRepository.findAllByPriceGreaterThan(price);
    }
    //localhost:8080/api/v1/items/greater?price=60

    @GetMapping("/less")
    public List<Item> getItemsWithPriceLessThan(@RequestParam int price) {
        return itemRepository.findAllByPriceLessThan(price);
    }
    //localhost:8080/api/v1/items/less?price=60
}
