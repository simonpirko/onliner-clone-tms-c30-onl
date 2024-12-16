package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dao.OrdersDAO;
import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.domain.*;
import by.tms.onlinerclonec30onl.dto.OrderDto;
import by.tms.onlinerclonec30onl.service.OrderService;
import by.tms.onlinerclonec30onl.service.ShopService;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ShopService shopService;

    @GetMapping
    public String shop(Model model) {
        List<Shop> shops = shopDAO.findByAccount((Account) session.getAttribute("currentUser"));
        model.addAttribute("shops", shops);
        return "shop";
    }

    @GetMapping("/create")
    public String index() {
        return "shopRegistration";
    }

    @PostMapping("/create")
    public String registration(@RequestParam("name") String name, Shop shop, Model model) {
        if (shopDAO.findByName(name).getName() == null) {
            shop.setName(name);
            shop.setAccount((Account) session.getAttribute("currentUser"));

            shopDAO.save(shop);
        } else {
            model.addAttribute("error", "This name is already taken");
            return "shopRegistration";
        }
        return "redirect:/shop/profile/" + shopDAO.findByName(shop.getName()).getId();
    }

    @GetMapping("/profile/{idShop}")
    public String profile(@PathVariable(value = "idShop") Long idShop, Model model) {

        Account account = (Account) session.getAttribute("currentUser");

        Shop shop=shopDAO.findByID(idShop).get();
        model.addAttribute("shop", shop);

        Customer customer=customerDAO.findByIDAccount(account.getId()).get();
        model.addAttribute("customer", customer);

        List<OrderDto> openOrders=orderService.getAllOpenShopOrders(idShop);
        model.addAttribute("openOrders",openOrders);
        List<OrderDto> closeOrders=orderService.getAllCloseShopOrders(idShop);
        model.addAttribute("closeOrders",closeOrders);


        return "shopProfile";
    }

    @GetMapping("/add-product")
    public String addProduct(@RequestParam("shopId") Long shopId, Model model) {
        List<Product> products = productDAO.findAllByNotShop(shopId);
        model.addAttribute("shopId", shopId);
        model.addAttribute("products", products);
        return "shop-add-product";
    }

    @PostMapping("/add-product")
    public String addProduct(@RequestParam("productId") Long productId,
                             @RequestParam("shopId") Long shopId,
                             @RequestParam("price") Double price,
                             @RequestParam("delivery") String delivery, Model model) {
        shopService.save(shopId, productId, price, delivery);
        return "redirect:/shop/add-product?shopId=" + shopId;
    }
}