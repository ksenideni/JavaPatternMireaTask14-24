package ru.ksenideni.task14.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ksenideni.task14.order.Order;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {
    @Id
    @SequenceGenerator(name = "items_seq", sequenceName = "items_sequence", allocationSize = 1)
    @GeneratedValue(generator = "items_seq", strategy = GenerationType.SEQUENCE)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "creation_date")
    private String creationDate;
    @Column(name = "price")
    private int price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="order_id")
    private Order order;

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", name='" + name + '\'' + ", creationDate=" + creationDate + ", price=" + price + '}';
    }
}
