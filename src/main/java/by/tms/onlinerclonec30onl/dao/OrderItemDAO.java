package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.OrderItem;
import by.tms.onlinerclonec30onl.mappers.OrderIteemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class OrderItemDAO implements DataAccessObject<OrderItem> {

    private final JdbcTemplate jdbcTemplate;
    private final OrderIteemMapper rowMapper;

@Autowired
    public OrderItemDAO(JdbcTemplate jdbcTemplate, OrderIteemMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }
    @Override
    public void save(OrderItem entity) {
        jdbcTemplate.update("INSERT INTO order_item VALUES (default,?,?,?,?)",entity.getOrders().getId(),entity.getShopProduct().getId(),entity.getQuantity(),entity.getPrice());
    }

    @Override
    public void delete(OrderItem entity) {
        jdbcTemplate.update("DELETE FROM order_item WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM order_item WHERE id=?",id);
    }

    @Override
    public void update(long id, OrderItem entity) {
        jdbcTemplate.update("UPDATE order_item SET id_shop_product = ?, quantity = ?, price = ? WHERE id = ?",entity.getShopProduct().getId(),entity.getQuantity(),entity.getPrice(),id);
    }

    @Override
    public List<OrderItem> findAll() {
        return jdbcTemplate.query("SELECT * FROM order_item", rowMapper);
    }

    @Override
    public Optional<OrderItem> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM order_item WHERE id=?",rowMapper,id));
    }

    public List<OrderItem> findAllByOrderId(Long id) {
        return jdbcTemplate.query("SELECT * FROM order_item WHERE id_orders = ?",rowMapper,id);
    }
}
