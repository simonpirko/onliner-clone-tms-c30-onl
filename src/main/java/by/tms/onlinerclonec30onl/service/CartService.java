package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.domain.Cart;
import by.tms.onlinerclonec30onl.dto.CartDTO;
import org.springframework.stereotype.Component;

@Component
public class CartService {
    public Cart setNewItemCart(CartDTO cartDTO) {

    return new Cart();
    }
}
