package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.AccountDAO;
import by.tms.onlinerclonec30onl.domain.Account;
import by.tms.onlinerclonec30onl.domain.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@Component
public class ShopMapper implements RowMapper<Shop> {
    @Autowired
    private AccountDAO accountDAO;
    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {
        Shop shop = new Shop();
        Account account = accountDAO.findByID(rs.getInt("id_account")).get(); //поправить
        shop.setAccount(account);
        shop.setId(rs.getLong("id"));
        shop.setName(rs.getString("name"));

        return shop;
    }
}
