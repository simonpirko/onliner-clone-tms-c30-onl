package by.tms.onlinerclonec30onl.dao;


import by.tms.onlinerclonec30onl.domain.Customer;
import by.tms.onlinerclonec30onl.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CustomerDAO implements DataAccessObject<Customer> {
    private final JdbcTemplate jdbcTemplate;
    private final CustomerMapper rowMapper;

    @Autowired
    public CustomerDAO(JdbcTemplate jdbcTemplate, CustomerMapper rowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.rowMapper = rowMapper;
    }

    @Override
    public void save(Customer entity) {
jdbcTemplate.update("INSERT INTO customer VALUES (default,?,?,?,?,?)",entity.getAccount().getId(),entity.getFirstName(),entity.getLastName(),entity.getPhone(),entity.getAddress());
    }

    @Override
    public void delete(Customer entity) {
jdbcTemplate.update("DELETE FROM customer WHERE id=?",entity.getId());
    }

    @Override
    public void deleteById(long id) {
jdbcTemplate.update("DELETE FROM customer WHERE id=?",id);
    }

    @Override
    public void update(long id, Customer entity) {
        jdbcTemplate.update("UPDATE customer SET id_account = ?,first_name = ?,last_name = ?,phone = ?, address = ? WHERE id = ?",entity.getAccount().getId(),entity.getFirstName(),entity.getLastName(),entity.getPhone(),entity.getAddress(),id);
    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.query("SELECT * FROM customer", rowMapper);
    }

    @Override
    public Optional<Customer> findByID(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id=?",rowMapper, id));
    }

    public Optional<Customer> findByIDAccount(long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("SELECT * FROM customer WHERE id_account=?",rowMapper, id));
    }
}
