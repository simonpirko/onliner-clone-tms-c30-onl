package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    AccountDAO accountDAO;
    @Autowired
    HttpSession session;

    @GetMapping
    public String index(HttpSession session, Model model) {
        return "index";
    }

    @GetMapping("/error")
    public String error(@RequestParam("message") String message, Model model) {
        model.addAttribute("message", message);
        return "error";
    }
}
