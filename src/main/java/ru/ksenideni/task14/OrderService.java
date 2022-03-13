package ru.ksenideni.task14;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {

    List<Order> orderList=new ArrayList<>();
    private OrderService(){
        Item i1=new Item(1, "i1.1", "Idate1.1", 10.0);
        Item i2=new Item(2, "i1.2", "Idate1.2", 20.0);
        Item i3=new Item(3, "i3.1", "Idate1.23", 30.0);
        Item i4=new Item(4, "i3.4", "Idate1.23", 40.0);

        orderList.add(new Order(1, "odate1", new ArrayList<>(Arrays.asList(i1, i2))));
        orderList.add(new Order(2, "odate2", new ArrayList<>(Arrays.asList(i3, i4))));

    }

    public List<Order> getAllOrders(){
        return orderList;
    }

    public Order getOrderById(Long id) {
        return orderList.stream().filter(o->{
            return o.getId()==id;
        }).findAny().get();
    }

    public void create(Order order) {
        orderList.add(order);
    }
}
