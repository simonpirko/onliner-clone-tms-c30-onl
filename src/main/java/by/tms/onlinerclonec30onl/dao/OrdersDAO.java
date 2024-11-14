package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.mappers.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrdersDAO implements InterfaceDAO<Orders> {
    private final JdbcTemplate jdbcTemplate;
    private final OrdersMapper rowMapper;
    
    @Autowired
    public OrdersDAO(JdbcTemplate jdbcTemplate, OrdersMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }
    @Override
    public void save(Orders entity) {
        jdbcTemplate.update("INSERT INTO orders VALUES (default,?,?,?,?,?,?,?)",entity.getCustomer().getId(),entity.getTotalPrice(),entity.getFirstName(),entity.getLastName(),entity.getPhone(),entity.getDeliveryAddress(),entity.getStatus().name().toUpperCase());
    }

    @Override
    public void delete(Orders entity) {
        jdbcTemplate.update("DELETE FROM orders WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM orders WHERE id=?",id);
    }

    @Override
    public void update(long id, Orders entity) {
        jdbcTemplate.update("UPDATE orders SET first_name = ?, last_name = ?, phone = ?, address = ?, status = ? WHERE id = ?",entity.getFirstName(),entity.getLastName(),entity.getPhone(),entity.getDeliveryAddress(),entity.getStatus(),id);
    }

    @Override
    public List<Orders> findAll() {
        return jdbcTemplate.query("SELECT * FROM orders", rowMapper);
    }

    @Override
    public Optional<Orders> findByID(long id) {
        return Optional.ofNullable( jdbcTemplate.queryForObject("SELECT * FROM orders WHERE id=?",rowMapper, id));
    }
    
}
