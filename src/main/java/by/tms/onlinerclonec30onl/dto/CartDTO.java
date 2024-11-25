package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Cart;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class CartDTO {
    private long idProduct;
    private long idShop;
    private long idAccount;
    private Cart cart;
}
