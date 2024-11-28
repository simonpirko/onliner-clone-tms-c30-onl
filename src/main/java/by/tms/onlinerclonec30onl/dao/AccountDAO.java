package by.tms.onlinerclonec30onl.dao;

import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.mappers.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class AccountDAO implements DataAccessObject<Account> {
    private final JdbcTemplate jdbcTemplate;
    private final AccountMapper rowMapper;

    @Autowired
    public AccountDAO(JdbcTemplate jdbcTemplate, AccountMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Account entity) {
        jdbcTemplate.update("INSERT INTO account VALUES (default,?,?,?)", entity.getUsername(), entity.getPassword(), entity.getRole().name().toUpperCase());
    }

    @Override
    public void delete(Account entity) {
        jdbcTemplate.update("DELETE FROM account WHERE id=?", entity.getId());
    }

    @Override
    public void deleteById(long id) {
        jdbcTemplate.update("DELETE FROM account WHERE id=?", id);
    }

    @Override
    public void update(long id, Account entity) {
        jdbcTemplate.update("UPDATE account SET username = ?,password = ?,role = ? WHERE id = ?", entity.getUsername(), entity.getPassword(), entity.getRole().name().toUpperCase(), id);
    }

    @Override
    public List<Account> findAll() {
        return jdbcTemplate.query("SELECT * FROM account", rowMapper);
    }

    @Override
    public Optional<Account> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM account WHERE id=?", rowMapper, id));
    }

    public Optional<Account> findByUsername(String username) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM account WHERE username=?", rowMapper, username));
    }
}
