package ru.ksenideni;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.ksenideni.item.Item;
import ru.ksenideni.item.ItemRepository;
import ru.ksenideni.order.Order;
import ru.ksenideni.order.OrderService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@ManagedResource

public class ScheduleCashService {
    private final OrderService orderService;
    private final ItemRepository itemRepository;
    // name of generated csv

    @ManagedOperation
    @Scheduled(fixedRate = 30 * 60 * 1_000)//вызывается каждые полчаса
    public void exportToCSV() throws IOException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException {
        String CSV_LOCATION = "src/main/resources/cash";
        //--очистка директории
        File cashDir = new File(CSV_LOCATION);
        for (File f : cashDir.listFiles()) {
            f.delete();
        }
        String CSV_LOCATION_ORDERS = "src/main/resources/cash/Orders_";
        String CSV_LOCATION_ITEMS = "src/main/resources/cash/Items_";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String currentDateTime = formatter.format(date);
        System.out.println(currentDateTime);
        CSV_LOCATION_ORDERS += currentDateTime + ".csv";
        CSV_LOCATION_ITEMS += currentDateTime + ".csv";
        //------orders------
        List<Order> orderList = orderService.getAllOrders();

        FileWriter writerOrders = new FileWriter(CSV_LOCATION_ORDERS);

        ColumnPositionMappingStrategy mappingStrategy1 = new ColumnPositionMappingStrategy();
        mappingStrategy1.setType(Order.class);

        String[] columns1 = new String[]{"id", "orderDate"};
        mappingStrategy1.setColumnMapping(columns1);

        StatefulBeanToCsvBuilder<Order> builder1 = new StatefulBeanToCsvBuilder(writerOrders);
        StatefulBeanToCsv beanWriter1 = builder1.withMappingStrategy(mappingStrategy1).build();

        beanWriter1.write(orderList);
        writerOrders.close();

        //------items------
        List<Item> itemList = itemRepository.findAll();

        FileWriter writerItems = new FileWriter(CSV_LOCATION_ITEMS);

        ColumnPositionMappingStrategy mappingStrategy2 = new ColumnPositionMappingStrategy();
        mappingStrategy2.setType(Item.class);

        String[] columns2 = new String[]{"id", "name", "creationDate", "price"};
        mappingStrategy2.setColumnMapping(columns2);

        StatefulBeanToCsvBuilder<Item> builder2 = new StatefulBeanToCsvBuilder(writerItems);
        StatefulBeanToCsv beanWriter2 = builder2.withMappingStrategy(mappingStrategy2).build();

        beanWriter2.write(itemList);
        writerItems.close();
    }
}
