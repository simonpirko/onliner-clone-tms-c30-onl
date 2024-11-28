package by.tms.onlinerclonec30onl.controller;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.dto.CustomerDto;
import by.tms.onlinerclonec30onl.dto.UserRegistrationDto;
import by.tms.onlinerclonec30onl.service.AccountService;
import by.tms.onlinerclonec30onl.service.CustomerService;
import by.tms.onlinerclonec30onl.domain.Customer;
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

    @GetMapping("/profile/{id}")
    public String index(@PathVariable(value = "id") Long customerId, Model model) {
        CustomerDto customerDto=customerService.getCustomer(customerId);

        model.addAttribute("lastname",customerDto.getLastName());
        model.addAttribute("firstname",customerDto.getFirstName());
        model.addAttribute("phone",customerDto.getPhone());
        model.addAttribute("address",customerDto.getAddress());

        return "accountProfile";
    }


    @PostMapping("/profile/{id}")
    public String registration(@PathVariable(value = "id") Long customerId,
                               @RequestBody String newLastName,
                               @RequestBody String newFirstName,
                               @RequestBody String newPhone,
                               @RequestBody String newAddress){

        Customer customer=customerDAO.findByID(customerId).get();

        customer.setLastName(newLastName);
        customer.setFirstName(newFirstName);
        customer.setPhone(newPhone);
        customer.setAddress(newAddress);

        customerDAO.update(customerId, customer);
        return "accountProfile";
    }

}
