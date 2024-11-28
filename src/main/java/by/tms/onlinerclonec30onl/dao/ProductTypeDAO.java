package by.tms.onlinerclonec30onl.dao;


import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.mappers.ProductTypeMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProductTypeDAO {
    private final JdbcTemplate jdbcTemplate;
    private final ProductTypeMapper rowMapper;

    public ProductTypeDAO(JdbcTemplate jdbcTemplate, ProductTypeMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;

    }
//    @Override
    public void save(ProductType productType) {
        jdbcTemplate.update("INSERT INTO product_type VALUES (default, ?, ?)",
                productType.getTypeName(), productType.getPhoto());
    }

//    @Override
    public void delete(ProductType productType) {
        jdbcTemplate.update("DELETE FROM product_type WHERE id = ?", productType.getId());
    }

//    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM product_type WHERE id = ?", id);
    }

//    @Override
    public void update(ProductType productType) {
        jdbcTemplate.update("UPDATE product_type SET type_name = ?, photo = ? WHERE id = ?",
                productType.getTypeName(), productType.getPhoto(), productType.getId());
    }

//    @Override
    public List<ProductType> findAll() {
        return jdbcTemplate.query("SELECT * FROM product_type", rowMapper);
    }

//    @Override
    public Optional<ProductType> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM product_type WHERE id = ?",
                rowMapper, id));
    }
}