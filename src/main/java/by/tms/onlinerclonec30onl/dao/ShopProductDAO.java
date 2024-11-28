package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.ShopProduct;
import by.tms.onlinerclonec30onl.mappers.ShopProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class ShopProductDAO implements DataAccessObject<ShopProduct> {
    private final JdbcTemplate jdbcTemplate;
    private final ShopProductMapper rowMapper;

    @Autowired
    public ShopProductDAO(final JdbcTemplate jdbcTemplate, final ShopProductMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }


    @Override
    public void save(ShopProduct entity) {
        jdbcTemplate.update("INSERT INTO shop_product VALUES (default,?,?,?,?)", entity.getShop().getId(), entity.getProduct().getId(), entity.getPrice(), entity.getDelivery());
    }

    @Override
    public void delete(ShopProduct entity) {
        jdbcTemplate.update("DELETE FROM shop_product WHERE id=?", entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM shop_product WHERE id=?", id);
    }

    @Override
    public void update(long id, ShopProduct entity) {
        jdbcTemplate.update("UPDATE shop_product SET id_product = ?,price = ?,delivery = ? WHERE id = ?", entity.getProduct().getId(), entity.getPrice(), entity.getDelivery(), id);
    }

    @Override
    public List<ShopProduct> findAll() {
        return jdbcTemplate.query("SELECT * FROM shop_product", rowMapper);
    }

    public List<Map<String, Object>> findAllByIDProduct(long idProduct) {
        return jdbcTemplate.queryForList("SELECT * FROM shop_product WHERE id_product=?", idProduct);
    }

    public Optional<ShopProduct> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM shop_product WHERE id=?",rowMapper,id));
    }

    public Optional<ShopProduct> findByIdProductAndIdShop(long idProduct, long idShop) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM shop_product WHERE id_shop=? and id_product=?",rowMapper,idShop,idProduct));
    }

    public Optional<Double> findMinPriceByID(long id) {
       try {
           return Optional.of(jdbcTemplate.queryForObject("SELECT min(price) FROM shop_product WHERE id_product = ?", Double.class, id));
       } catch (NullPointerException e) {
           return Optional.empty();
       }
    }
}
