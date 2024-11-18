package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.OrdersDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.OrderItem;
import by.tms.onlinerclonec30onl.domain.Orders;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderIteemMapper implements RowMapper<OrderItem> {
    @Autowired
    private OrdersDAO ordersDAO;
    @Autowired
    private ShopProductDAO shopProductDAO;

    @Override
    public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        OrderItem orderItem = new OrderItem();
        Orders orders = ordersDAO.findByID(rs.getInt("id_orders")).get();
        orderItem.setOrders(orders);
        ShopProduct shopProduct = shopProductDAO.findByID(rs.getInt("id_shop_product")).get();
        orderItem.setShopProduct(shopProduct);
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getDouble("price"));
        orderItem.setId(rs.getInt("id"));
        return orderItem;
    }
}
