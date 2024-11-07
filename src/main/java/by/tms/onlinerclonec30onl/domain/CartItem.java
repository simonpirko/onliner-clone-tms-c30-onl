package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartItem {

    private long id;
    private ShopProduct shopProduct;
    private int quantity;
    private double price;
}
