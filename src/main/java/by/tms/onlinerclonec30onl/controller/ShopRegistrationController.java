
package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Shop;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/shop-registration")
public class ShopRegistrationController {

    @Autowired
    ShopDAO shopDAO;
    @Autowired
    HttpSession session;
    @GetMapping
    public String index() {
        return "shopRegistration";
    }

    @PostMapping
    public String registration(@RequestBody String name,Shop shop, Model model) {
        if (shopDAO.findByName(name).getName() == null) {
            shop.setName(name);
           shop.setAccount((Account) session.getAttribute("currentUser"));

            shopDAO.save(shop);
        } else {
            model.addAttribute("error", "This name is already taken");
            return "shopRegistration";
        }
        return "redirect:/shop_profile";
    }
}