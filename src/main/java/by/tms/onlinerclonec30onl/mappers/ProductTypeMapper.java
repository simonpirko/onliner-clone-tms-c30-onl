package by.tms.onlinerclonec30onl.mappers;

import by.tms.onlinerclonec30onl.domain.ProductType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductTypeMapper implements RowMapper<ProductType> {

    @Override
    public ProductType mapRow(ResultSet rs, int rowNum) throws SQLException {
        ProductType productType = new ProductType();
        productType.setId(rs.getInt("id"));
        productType.setTypeName(rs.getString("type_name"));
        productType.setPhoto(rs.getString("photo"));
        return productType;
    }
}