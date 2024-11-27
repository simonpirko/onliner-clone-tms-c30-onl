package by.tms.onlinerclonec30onl.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping
    public String order(Model model) {

        return "order";
    }
    @PostMapping
    public String addOrder(@RequestParam("idProduct") long idProduct,
                           @RequestParam("idShop") long idShop,
                           @RequestParam("idAccount") long idAccount,
                           Model model, HttpSession session) {
        //параметры товара переданного для оформления и id пользователя осуществившего заказ
        System.out.println(idProduct);
        System.out.println(idShop);
        System.out.println(idAccount);
        return "redirect:/order";
    }
}
