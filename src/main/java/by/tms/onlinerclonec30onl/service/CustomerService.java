package by.tms.onlinerclonec30onl.service;
import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.dto.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerDAO customerDAO;


    public CustomerDto getCustomer(long id) {
        Customer customer=customerDAO.findByIDAccount(id).get();

        CustomerDto customerDto = new CustomerDto();

        customerDto.setAccount(customer.getAccount());
        customerDto.setFirstName(customer.getFirstName());
        customerDto.setLastName(customer.getLastName());
        customerDto.setPhone(customer.getPhone());
        customerDto.setAddress(customer.getAddress());

        return customerDto;
    }
}