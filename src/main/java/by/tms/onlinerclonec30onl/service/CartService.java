package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.Cart;
import by.tms.onlinerclonec30onl.domain.CartItem;
import by.tms.onlinerclonec30onl.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartService {
    @Autowired
    private ShopProductDAO shopProductDAO;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private AccountDAO accountDAO;


    public Cart setNewItemCart(CartDTO cartDTO) {
        Cart cart = cartDTO.getCart();
        cart.setAccount(accountDAO.findByID(cartDTO.getIdAccount()).get());
        if (cart.getProducts() == null) {
            cart.setProducts(new ArrayList<>());
        }
        ArrayList<CartItem> cartItemList = cart.getProducts();
        CartItem cartItem = new CartItem(productDAO.findByID(cartDTO.getIdProduct()).get(),
                shopProductDAO.findByIdProductAndIdShop(cartDTO.getIdProduct(), cartDTO.getIdShop()).get(),
                shopDAO.findByID(cartDTO.getIdShop()).get().getName());
        cartItemList.add(cartItem);
        cart.setProducts(cartItemList);
        return cart;
    }
}
