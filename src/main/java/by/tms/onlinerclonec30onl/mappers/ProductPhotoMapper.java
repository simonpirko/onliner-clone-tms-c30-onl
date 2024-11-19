package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductPhotoMapper implements RowMapper<ProductPhoto> {

    @Autowired
    private ProductDAO productDAO;

    @Override
    public ProductPhoto mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductPhoto productPhoto = new ProductPhoto();
        Product product = productDAO.findByID(rs.getInt("id_product")).get();
        productPhoto.setProduct(product);
        productPhoto.setPhoto(rs.getString("photo"));
        productPhoto.setId(rs.getLong("id"));

        return productPhoto;
    }
}
