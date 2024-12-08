package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.dto.CustomerDto;
import by.tms.onlinerclonec30onl.dto.UserRegistrationDto;
import by.tms.onlinerclonec30onl.service.AccountService;
import by.tms.onlinerclonec30onl.service.CustomerService;
import by.tms.onlinerclonec30onl.domain.Customer;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class AccountController {
    @Autowired
    AccountService accountService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerDAO customerDAO;
    @Autowired
    private AccountDAO accountDAO;

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

    @GetMapping("/profile")
    public String index(HttpSession session, Model model) {
        Account account = (Account) session.getAttribute("currentUser");
        CustomerDto customerDto=customerService.getCustomer(account.getId());

        model.addAttribute("lastname",customerDto.getLastName());
        model.addAttribute("firstname",customerDto.getFirstName());
        model.addAttribute("phone",customerDto.getPhone());
        model.addAttribute("address",customerDto.getAddress());
        model.addAttribute("role",account.getRole());

        return "accountProfile";
    }


    @PostMapping("/profile")
    public String registration(@RequestParam("lastname") String newLastName,
                               @RequestParam("name") String newFirstName,
                               @RequestParam("phone") String newPhone,
                               @RequestParam("address") String newAddress,
                               @RequestParam("role") String role,
                               HttpSession session) {

        Account account = (Account) session.getAttribute("currentUser");
        Customer customer=customerDAO.findByID(account.getId()).get();

        customer.setLastName(newLastName);
        customer.setFirstName(newFirstName);
        customer.setPhone(newPhone);
        customer.setAddress(newAddress);

        customerDAO.update(account.getId(), customer);

        account.setRole(Account.Role.valueOf(role));
        accountDAO.update(account.getId(), account);
        return "redirect:/user/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/catalog";
    }

}
