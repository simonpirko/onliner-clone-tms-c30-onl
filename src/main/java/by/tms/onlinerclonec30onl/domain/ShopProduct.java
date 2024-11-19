package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ShopProduct {

    private Long id;
    private Shop shop;
    private Product product;
    private double price;
    private String delivery;
}
