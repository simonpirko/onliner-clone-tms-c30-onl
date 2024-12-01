package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dto.OrderDto;
import by.tms.onlinerclonec30onl.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public String addOrder(@RequestParam("idProduct") long idProduct,
                           @RequestParam("idShop") long idShop,
                           @RequestParam("idAccount") long idAccount,
                           Model model, HttpSession session) {
        //параметры товара переданного для оформления и id пользователя осуществившего заказ
        OrderDto orders = orderService.getOrders(idProduct, idShop, idAccount);
        model.addAttribute("order", orders);
        return "order";
    }
}
