package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.ProductFromTypeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ShopProductDAO shopProductDAO;

    @Autowired
    private ProductTypeDAO productTypeDAO;

    public List<Product> getAllProductsFromType(Long typeId) {
        return productDAO.findAllByIdProductType(typeId);
    }

    public List<ProductFromTypeDto> getAllProductsFromTypeDto(Long typeId) {
        List<ProductFromTypeDto> productsFromTypeDto = new ArrayList<>();
        List<Product> products = productDAO.findAllByIdProductType(typeId);
        for (Product product : products) {
            ProductFromTypeDto productFromTypeDto = new ProductFromTypeDto();
            productFromTypeDto.setId(product.getId());
            productFromTypeDto.setName(product.getName());
            productFromTypeDto.setDescription(product.getDescription());
            Optional<Double> price = shopProductDAO.findMinPriceByID(product.getId());
            price.ifPresent(productFromTypeDto::setPrice);
            productFromTypeDto.setPhotos(product.getPhotos());
            productsFromTypeDto.add(productFromTypeDto);
        }
        return productsFromTypeDto;
    }

    public Optional<ProductType> getProductTypeById(Long typeId) {
        return productTypeDAO.findByID(typeId);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeDAO.findAll();
    }

}
