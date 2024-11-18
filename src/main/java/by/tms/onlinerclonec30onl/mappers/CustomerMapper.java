package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class CustomerMapper implements RowMapper<Customer> {
    @Autowired
    private AccountDAO accountDAO;
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        Account account = accountDAO.findByID(rs.getInt("id_account")).get();
        customer.setAccount(account);
        customer.setId(rs.getLong("id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));

        return customer;
    }
}
