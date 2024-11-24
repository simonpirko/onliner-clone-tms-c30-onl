package by.tms.onlinerclonec30onl.controller;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.dto.CustomerDto;
import by.tms.onlinerclonec30onl.service.CustomerDtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/profile/{customerId}")
public class AccountProfileController {

    @Autowired
    private CustomerDtoService customerDtoService;
    @Autowired
    private CustomerDAO customerDAO;



   @GetMapping
    public String index(@PathVariable(value = "customerId") Long customerId,Model model) {
        CustomerDto customerDto=customerDtoService.getCustomerDto(customerId);

        model.addAttribute("lastname",customerDto.getLastName());
        model.addAttribute("firstname",customerDto.getFirstName());
        model.addAttribute("phone",customerDto.getPhone());
        model.addAttribute("address",customerDto.getAddress());

        return "accountProfile";
    }


    @PostMapping
    public String registration(@PathVariable(value = "customerId") Long customerId,
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