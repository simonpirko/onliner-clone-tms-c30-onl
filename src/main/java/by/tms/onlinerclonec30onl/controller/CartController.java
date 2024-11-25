package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Cart;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.dto.CartDTO;
import by.tms.onlinerclonec30onl.service.CartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    private CartService cartService;
    @GetMapping
    public String cart() {
        return "cart";
    }

    @PostMapping
    public String addCart(@RequestParam("idProduct") long idProduct,
                          @RequestParam("idShop") long idShop,
                          @RequestParam("idAccount") long idAccount,
                          Model model) {
        if (idAccount == -1) {
            return "redirect:/product/" + idProduct;
        }
        Cart cart = (Cart) model.getAttribute("cart");
        CartDTO cartDTO = new CartDTO(idProduct, idShop, idAccount, cart);
        model.addAttribute("cart",cartService.setNewItemCart(cartDTO));
        return "redirect:/cart";
    }
}
