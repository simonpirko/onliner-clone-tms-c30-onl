package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.mappers.OrdersMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class OrdersDAO implements DataAccessObject<Orders> {
    private final JdbcTemplate jdbcTemplate;
    private final OrdersMapper rowMapper;
    private final DataSource dataSource;

    @Autowired
    public OrdersDAO(JdbcTemplate jdbcTemplate, OrdersMapper rowMapper, DataSource dataSource) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
        this.dataSource = dataSource;
    }
    @Override
    public void save(Orders entity) {
        jdbcTemplate.update("INSERT INTO orders VALUES (default,?,?,?,?,?,?,?)",entity.getCustomer().getId(),entity.getTotalPrice(),entity.getFirstName(),entity.getLastName(),entity.getPhone(),entity.getDeliveryAddress(),entity.getStatus().name().toUpperCase());
    }

    @SneakyThrows
    public Long saveReturnId(Orders entity) {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO orders VALUES (default,?,?,?,?,?,?,?) RETURNING id");
        preparedStatement.setLong(1, entity.getCustomer().getId());
        preparedStatement.setDouble(2, entity.getTotalPrice());
        preparedStatement.setString(3, entity.getFirstName());
        preparedStatement.setString(4, entity.getLastName());
        preparedStatement.setString(5, entity.getPhone());
        preparedStatement.setString(6, entity.getDeliveryAddress());
        preparedStatement.setString(7, entity.getStatus().name().toUpperCase());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getLong("id");
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

    public List<Orders> findAllByCustomerId(long customerId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE id_customer=?",rowMapper,customerId);
    }

    public List<Orders> findAllOpenByCustomerId(long customerId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE id_customer=? AND status='OPEN'",rowMapper,customerId);
    }

    public List<Orders> findAllCloseByCustomerId(long customerId) {
        return jdbcTemplate.query("SELECT * FROM orders WHERE id_customer=? AND status='CLOSED'",rowMapper,customerId);
    }

    public List<Orders> findAllOpenByShopId(long shopId) {
        return jdbcTemplate.query("SELECT orders.* FROM orders, order_item, shop_product WHERE shop_product.id_shop = ?" +
                " AND orders.id=order_item.id_orders AND order_item.id_shop_product=shop_product.id AND status='OPEN'",
                rowMapper, shopId);
    }

    public List<Orders> findAllCloseByShopId(long shopId) {
        return jdbcTemplate.query("SELECT orders.* FROM orders, order_item, shop_product WHERE shop_product.id_shop = ?" +
                        " AND orders.id=order_item.id_orders AND order_item.id_shop_product=shop_product.id" +
                        " AND status='CLOSED'", rowMapper, shopId);
    }

    public void closeOrder(Long id) {
        jdbcTemplate.update("UPDATE orders SET status='CLOSED' WHERE id=?",id);
    }
    
}
