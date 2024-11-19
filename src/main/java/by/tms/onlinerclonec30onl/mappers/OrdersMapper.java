package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.CustomerDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.domain.Orders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrdersMapper implements RowMapper<Orders> {
    @Autowired
    private CustomerDAO customerDAO;

    @Override
    public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
        Orders orders = new Orders();
        Customer customer = customerDAO.findByID(rs.getInt("id_customer")).get();
        orders.setCustomer(customer);
        orders.setId(rs.getInt("id"));
        orders.setPhone(rs.getString("phone"));
        orders.setStatus(Orders.Status.valueOf(rs.getString("status")));
        orders.setDeliveryAddress(rs.getString("address"));
        orders.setFirstName(rs.getString("first_name"));
        orders.setLastName(rs.getString("last_name"));
        orders.setTotalPrice(rs.getDouble("total_price"));

        return orders;
    }
}
