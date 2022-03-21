package ru.ksenideni.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    //to demonstrate queries by criteria
    List<Item> findAllByPriceGreaterThan(int price);

    List<Item> findAllByPriceLessThan(int price);
}
