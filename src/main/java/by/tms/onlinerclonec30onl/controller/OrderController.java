package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Cart;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.dto.OrderDto;
import by.tms.onlinerclonec30onl.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String addOrder(@RequestParam("idProduct") long idProduct,
                           @RequestParam("idShop") long idShop,
                           @RequestParam("idAccount") long idAccount,
                           Model model) {
        OrderDto orders = orderService.getOrders(idAccount, idShop, idProduct);
        model.addAttribute("order", orders);
        return "order";
    }

    @PostMapping
    public String addOrder(OrderDto orderDto, Model model, HttpSession session) {

        orderService.save(orderDto);

        Cart cart = (Cart) session.getAttribute("cart");
        for(int i = 0; i < cart.getProducts().size(); i++){
            if(Objects.equals(cart.getProducts().get(i).getShopProduct().getId(), orderDto.getShopProductId())){
                cart.getProducts().remove(i);
            }
        }
        session.setAttribute("cart", cart);
        return "redirect:/cart";
    }

    @GetMapping("/user-orders")
    public String userOrders(Model model, HttpSession session) {
        Account account = (Account) session.getAttribute("currentUser");
        List<OrderDto> orderOpenDtos = orderService.getAllOpenUserOrders(account.getId());
        List<OrderDto> orderCloseDtos = orderService.getAllCloseUserOrders(account.getId());
        model.addAttribute("orderOpenDtos", orderOpenDtos);
        model.addAttribute("orderCloseDtos", orderCloseDtos);
        return "user-orders";
    }

    @GetMapping("/close")
    public String closeOrder(@RequestParam("orderId") long orderId,
                             @RequestParam("shopId") long shopId) {
        orderService.closeOrder(orderId);
        return "redirect:/shop/profile/" + shopId;
    }
}
