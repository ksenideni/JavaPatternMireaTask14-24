package ru.ksenideni.task14.criterias;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ksenideni.task14.item.Item;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/items")
public class TestCriteriaController {
    private final ItemCriteria ic;

    @Autowired
    public TestCriteriaController(ItemCriteria ic) {
        this.ic = ic;
    }

    @GetMapping
    public List<Item> getAllItems() {
        return ic.getAllItems();
    }

    @GetMapping("/greater")
    public List<Item> getItemsWithPriceMoreThan(@RequestParam int price) {
        return ic.findByPriceGreaterThan(price);
    }
    //localhost:8080/api/v1/items/greater?price=60

    @GetMapping("/less")
    public List<Item> getItemsWithPriceLessThan(@RequestParam int price) {
        return ic.findByPriceLessThan(price);
    }
    //localhost:8080/api/v1/items/less?price=60
}
