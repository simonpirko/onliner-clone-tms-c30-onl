package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.dto.UserLoginDTO;
import by.tms.onlinerclonec30onl.dto.UserRegistrationDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    CustomerDAO customerDAO;
    @Autowired
    private HttpSession httpSession;

    public void reg (UserRegistrationDto userRegistrationDto) {
    Account account = new Account();
    account.setUsername(userRegistrationDto.getUsername());
    account.setPassword(userRegistrationDto.getPassword());
    account.setRole(Account.Role.USER);
    accountDAO.save(account);
    Customer customer = new Customer();
    customer.setAccount(accountDAO.findByUsername(userRegistrationDto.getUsername()).get());
    customer.setFirstName(userRegistrationDto.getFirstName());
    customer.setLastName(userRegistrationDto.getLastName());
    customerDAO.save(customer);
}
 public boolean Login (UserLoginDTO accountDTO) {
     Optional<Account> account = accountDAO.findByUsername(accountDTO.getUsername());
     if (account.isPresent()) {
         if (account.get().getPassword().equals(accountDTO.getPassword())) {
             httpSession.setAttribute("currentUser", account.get());
         return true;
         }
     }
     return false;
 }
    public boolean checkUsernameExist(String username) {
        List<Account> account = accountDAO.findAll();
        for (Account a : account) {
            if (a.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
}
