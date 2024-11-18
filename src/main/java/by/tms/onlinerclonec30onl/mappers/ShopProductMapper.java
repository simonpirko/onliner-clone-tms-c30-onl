package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.Shop;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
@Component
public class ShopProductMapper implements RowMapper<ShopProduct> {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private ShopDAO shopDAO;

    @Override
    public ShopProduct mapRow(ResultSet rs, int rowNum) throws SQLException {
        ShopProduct shopProduct = new ShopProduct();
//        Shop shop = shopDAO.findByID(rs.getInt("id_shop"));
//        shopProduct.setShop(shop);
        shopProduct.setId(rs.getLong("id"));
//        Product product = productDAO.findByID(rs.getInt("id_product"));
//        shopProduct.setProduct(product);
        shopProduct.setPrice(rs.getDouble("price"));
        shopProduct.setDelivery(rs.getString("delivery"));

        return shopProduct;
    }
}
