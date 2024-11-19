package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowcaseProductGroupsDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ShowcaseProductGroupsDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ProductType> index() {
        return jdbcTemplate.query("SELECT * FROM product_type", new BeanPropertyRowMapper<>(ProductType.class));
    }


    public int save(ProductType productType) {
        return jdbcTemplate.update("INSERT INTO product_type (type_name, photo) VALUES (?, ?)",
                productType.getTypeName(), productType.getPhoto());
    }

}
