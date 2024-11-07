package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductTypeDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductTypeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductType> index() {
        return jdbcTemplate.query("SELECT * FROM product_type",
                new BeanPropertyRowMapper<>(ProductType.class));
    }

    public List<ProductType> show(long id) {
        return jdbcTemplate.query("SELECT * FROM product_type WHERE id = ?",
                new Object[]{id}, new BeanPropertyRowMapper<>(ProductType.class));
    }

    public int save(ProductType productType) {
        return jdbcTemplate.update("INSERT INTO product_type (name_type, name_table) VALUES (?, ?)",
                productType.getNameType(), productType.getNameTable());
    }

    public int update(ProductType productType, long id) {
        return jdbcTemplate.update("UPDATE product_type SET name_type = ?, name_table = ? WHERE id = ?",
                productType.getNameType(), productType.getNameTable(), id);
    }

    public int delete(long id) {
        return jdbcTemplate.update("DELETE FROM product_type WHERE id = ?", id);
    }
}