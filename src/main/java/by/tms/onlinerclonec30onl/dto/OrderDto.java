package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderDto {

    private Customer customer;
    private Double totalPrice;
    private String firstName;
    private String lastName;
    private String phone;
    private String deliveryAddress;
    private ShopProduct shopProduct;
}
