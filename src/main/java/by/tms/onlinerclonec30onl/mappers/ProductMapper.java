package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.dao.ProductPhotoDAO;
import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Autowired
    private ProductTypeDAO productTypeDAO;

    @Autowired
    private ProductPhotoDAO productPhotoDAO;

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        ProductType productType = productTypeDAO.findByID(rs.getInt("id_product_type")).get();
        product.setProductType(productType);
        product.setId(rs.getLong("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        List<String> productPhotos = productPhotoDAO.findAllByProductId(rs.getInt("id"));
        product.setPhotos(productPhotos);
        return product;
    }
}