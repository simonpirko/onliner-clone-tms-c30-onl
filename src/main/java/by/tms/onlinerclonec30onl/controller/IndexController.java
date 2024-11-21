package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    AccountDAO accountDAO;

    @GetMapping
    public String index() {
        return "index";
    }
}
