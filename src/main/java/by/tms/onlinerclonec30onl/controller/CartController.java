package by.tms.onlinerclonec30onl.controller;


import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Cart;

import by.tms.onlinerclonec30onl.domain.CartItem;
import by.tms.onlinerclonec30onl.dto.CartDTO;
import by.tms.onlinerclonec30onl.service.CartService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String cart() {
        return "cart";
    }

    @PostMapping
    public String addCart(@RequestParam("idProduct") long idProduct,
                          @RequestParam("idShop") long idShop,
                          @RequestParam("idAccount") long idAccount,
                          Model model, HttpSession session) {
        if (idAccount == -1) {
            return "redirect:/product/" + idProduct;
        }
        Cart cart = (Cart) session.getAttribute("cart");
        CartDTO cartDTO = new CartDTO(idProduct, idShop, idAccount, cart);
        session.setAttribute("cart", cartService.setNewItemCart(cartDTO));
        Cart cart1 = (Cart) session.getAttribute("cart");
        //model.addAttribute("CurrentUser", (Account) session.getAttribute("currentUser"));
        return "redirect:/cart";


    }
    @PostMapping("/delete")
    public String deleteItemCart(@RequestParam("idItem") long idItem,
                          Model model, HttpSession session) {
        Cart cart = (Cart)session.getAttribute("cart");
        for(int i = 0; i < cart.getProducts().size(); i++){
            if(cart.getProducts().get(i).getShopProduct().getId() == idItem){
                cart.getProducts().remove(i);
            }
        }
        session.setAttribute("cart", cart);

        return "redirect:/cart";


    }
}
