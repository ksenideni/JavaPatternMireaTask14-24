package ru.ksenideni.task14.criterias;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.ksenideni.task14.item.Item;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class ItemCriteria {
    private final SessionFactory sessionFactory;

    @Autowired
    public ItemCriteria(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Item> getAllItems() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Item> itemCriteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = itemCriteriaQuery.from(Item.class);
        itemCriteriaQuery.select(itemRoot);
        List<Item> result = session.createQuery(itemCriteriaQuery).getResultList();
        session.close();
        return result;
    }

    public List<Item> findByPriceLessThan(int price) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Item> itemcq = cb.createQuery(Item.class);
        Root<Item> itemRoot = itemcq.from(Item.class);
        itemcq.select(itemRoot).where(cb.lt(itemRoot.get("price"), price));
        List<Item> result = session.createQuery(itemcq).getResultList();
        session.close();
        return result;
    }

    public List<Item> findByPriceGreaterThan(int price) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Item> itemcq = cb.createQuery(Item.class);
        Root<Item> itemRoot = itemcq.from(Item.class);
        itemcq.select(itemRoot).where(cb.gt(itemRoot.get("price"), price));
        List<Item> result = session.createQuery(itemcq).getResultList();
        session.close();
        return result;
    }
}
