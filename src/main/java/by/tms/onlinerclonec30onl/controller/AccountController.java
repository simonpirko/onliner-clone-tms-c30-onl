package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dto.UserRegistrationDto;
import by.tms.onlinerclonec30onl.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/reg")
    public String registration(@ModelAttribute("userRegistrationDto") UserRegistrationDto accountDto) {
        return "registration";
    }

    @PostMapping("/reg")
    public String registration (@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto accountDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        if (accountService.checkUsernameExist(accountDto.getUsername())) {
            model.addAttribute("errorMessage", "Такой пользователь уже существует");
            return "registration";
        }
        accountService.reg(accountDto);
        return "registration";
    }
}
