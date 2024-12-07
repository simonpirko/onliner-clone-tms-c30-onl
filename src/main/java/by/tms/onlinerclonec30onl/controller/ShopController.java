package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.OrdersDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.domain.Shop;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ShopDAO shopDAO;
    @Autowired
    HttpSession session;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    OrdersDAO ordersDAO;

    @GetMapping("/create")
    public String index() {
        return "shopRegistration";
    }

    @PostMapping("/create")
    public String registration(@RequestBody String name,Shop shop, Model model) {
        if (shopDAO.findByName(name).getName() == null) {
            shop.setName(name);
            shop.setAccount((Account) session.getAttribute("currentUser"));

            shopDAO.save(shop);
        } else {
            model.addAttribute("error", "This name is already taken");
            return "shopRegistration";
        }
        return "redirect:/shop/profile";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        Account account = (Account) session.getAttribute("currentUser");

        Shop shop=shopDAO.findByID(account.getId()).get();
        model.addAttribute("shop", shop);

        Customer customer=customerDAO.findByID(account.getId()).get();
        model.addAttribute("customer", customer);

        List<Orders> openOrders=ordersDAO.findAllOpenByCustomerId(customer.getId());
        model.addAttribute("openOrders",openOrders);
        List<Orders> closeOrders=ordersDAO.findAllCloseByCustomerId(customer.getId());
        model.addAttribute("closeOrders",closeOrders);


        return "shopProfile";
    }

}