package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductDAO implements DataAccessObject<Product> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ProductMapper rowMapper;

    @Override
    public void save(Product entity) {
        jdbcTemplate.update("INSERT INTO product VALUES (default,?,?,?)",entity.getProductType().getId(),entity.getName(),entity.getDescription());
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
}
