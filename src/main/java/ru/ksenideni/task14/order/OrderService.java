package ru.ksenideni.task14.order;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final SessionFactory sessionFactory;


    @Autowired
    public OrderService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Order> getAllOrders() {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        List<Order> orderList = session.createQuery("select o from Order o", Order.class).getResultList();
        transaction.commit();
        return orderList;
    }

    public Order getOrderById(Long id) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Order order = session.get(Order.class, id);
        transaction.commit();
//        session.close();
        return order;
    }

    public void create(Order order) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        session.saveOrUpdate(order);
        transaction.commit();
    }

    public void deleteOrderById(Long orderId) {
        Session session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        Order o = session.get(Order.class, orderId);
        session.delete(o);
        transaction.commit();
        session.close();
    }
}
