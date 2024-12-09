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

    private Long orderId;
    private Long customerId;
    private Long shopProductId;
    private Double totalPrice;
    private String firstName;
    private String lastName;
    private String shopName;
    private String productName;
    private String shopDelivery;
    private String phone;
    private String deliveryAddress;
    private String photo;
}
