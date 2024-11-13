package by.tms.onlinerclonec30onl.domain;

import lombok.*;
import org.springframework.core.annotation.Order;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderItem {

    private long id;
    private Order order;
    private ShopProduct shopProduct;
    private int quantity;
    private double price;
}
