package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.mappers.ProductMapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO implements DataAccessObject<Product> {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private ProductMapper rowMapper;

    @Override
    public void save(Product entity) {
      jdbcTemplate.update("INSERT INTO product VALUES (default,?,?,?)",entity.getProductType().getId(),entity.getName(),entity.getDescription());
    }
    @SneakyThrows
    public Long saveAndReturnID(Product entity) {
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO product VALUES (default,?,?,?) RETURNING id");
        preparedStatement.setLong(1, entity.getProductType().getId());
        preparedStatement.setString(2, entity.getName());
        preparedStatement.setString(3, entity.getDescription());
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getLong("id");
    }

    @Override
    public void delete(Product entity) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?",id);
    }

    @Override
    public void update(long id, Product entity) {
        jdbcTemplate.update("UPDATE product SET id_product_type = ?, name = ?, description = ? WHERE id = ?",entity.getProductType().getId(),entity.getName(),entity.getDescription(),id);
    }

    @Override
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM product", rowMapper);
    }

    @Override
    public Optional<Product> findByID(long id) {
        return Optional.ofNullable( jdbcTemplate.queryForObject("SELECT * FROM product WHERE id=?", rowMapper, id));
    }

    public List<Product> findAllByIdProductType(Long id) {
        return jdbcTemplate.query("SELECT * FROM product WHERE id_product_type = ?", rowMapper, id);
    }

    public List<Product> findAllByName(String name) {
        return jdbcTemplate.query("SELECT * FROM product WHERE name ILIKE ?", rowMapper, "%" + name + "%");
    }

    public List<Product> findAllByNotShop(Long shopId) {
        return jdbcTemplate.query("SELECT DISTINCT product.* FROM product LEFT JOIN shop_product " +
                "ON product.id=shop_product.id_product " +
                "WHERE product.id NOT IN (SELECT shop_product.id_product FROM shop_product WHERE id_shop=?)",
                rowMapper, shopId);
    }
}
