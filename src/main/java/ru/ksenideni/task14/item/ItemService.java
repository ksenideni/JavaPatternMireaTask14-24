package ru.ksenideni.task14.item;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ksenideni.task14.order.Order;
import ru.ksenideni.task14.order.OrderService;

import java.util.List;

@Service
public class ItemService {
    private final OrderService orderService;
    private final SessionFactory sessionFactory;
//    private Session session;

    //    @PostConstruct
//    void init() {
//        session = ;
//    }
    @Autowired
    public ItemService(OrderService orderService, SessionFactory sessionFactory) {
        this.orderService = orderService;
        this.sessionFactory = sessionFactory;
    }

    public List<Item> getAllItems(Long orderId) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Order o = orderService.getOrderById(orderId);
        List<Item> itemList = session.createQuery("select i from Item i where i.order=:order", Item.class).setParameter("order", o).getResultList();
        transaction.commit();
        session.close();
        return itemList;
    }

    public Item getItemById(Long itemId) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Item i = session.createQuery("select i from Item i where i.id=:itemId", Item.class).setParameter("itemId", itemId).getSingleResult();
        transaction.commit();
        session.close();
        return i;
    }

    public Item create(Long orderId, Item item) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Order order = orderService.getOrderById(orderId);
        item.setOrder(order);
        session.save(item);
        transaction.commit();
        session.close();
        return item;
    }

    public void deleteItemById(Long orderId, Long itemId) {
        Session session = sessionFactory.openSession();
        Item i = getItemById(itemId);
        var transaction = session.beginTransaction();
        session.delete(i);
        transaction.commit();
        session.close();
    }
}
