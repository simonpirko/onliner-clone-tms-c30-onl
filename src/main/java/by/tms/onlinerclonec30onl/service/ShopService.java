package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.ShopProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopProductDAO shopProductDAO;
    @Autowired
    private ShopDAO shopDAO;
    @Autowired
    private ProductDAO productDAO;

    public void save(Long shopId, Long productId, Double price, String delivery) {
        ShopProduct shopProduct = new ShopProduct();
        shopProduct.setShop(shopDAO.findByID(shopId).get());
        shopProduct.setProduct(productDAO.findByID(productId).get());
        shopProduct.setPrice(price);
        shopProduct.setDelivery(delivery);
        shopProductDAO.save(shopProduct);
    }
}
