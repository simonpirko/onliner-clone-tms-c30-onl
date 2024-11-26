package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CartItem {
    private Product product;
    private ShopProduct shopProduct;
    private String nameShop;
}
