package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Shop;
import by.tms.onlinerclonec30onl.mappers.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ShopDAO implements DataAccessObject<Shop> {
    private final JdbcTemplate jdbcTemplate;
    private final ShopMapper rowMapper;
    @Autowired
    public ShopDAO(JdbcTemplate jdbcTemplate, ShopMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Shop entity) {
        jdbcTemplate.update("INSERT INTO shop VALUES (default,?,?)",entity.getAccount().getId(),entity.getName());
    }

    @Override
    public void delete(Shop entity) {
        jdbcTemplate.update("DELETE FROM shop WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM shop WHERE id=?",id);
    }

    @Override
    public void update(long id, Shop entity) {
        jdbcTemplate.update("UPDATE shop SET name = ? WHERE id = ?",entity.getName(),id);
    }

    @Override
    public List<Shop> findAll() {
        return jdbcTemplate.query("SELECT * FROM shop", rowMapper);
    }

    @Override
    public Optional<Shop> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM shop WHERE id=?",rowMapper, id));
    }

    public Shop findByName(String name){
        return jdbcTemplate.query("SELECT * FROM shop WHERE name=?",new Object[]{name},rowMapper).stream().findFirst().orElse(new Shop());
    }

    public List<Shop> findByAccount(Account account){
        return jdbcTemplate.query("SELECT * FROM shop WHERE id_account=?", rowMapper, account.getId());
    }
}
