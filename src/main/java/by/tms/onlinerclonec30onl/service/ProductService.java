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
  
    @Autowired
    private ShopDAO shopDAO;

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
  
  public ProductDTO getProductPageData(Long id) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct(productDAO.findByID(id));
        List<Map<String, Object>> allByIDProduct = shopProductDAO.findAllByIDProduct(id);
        List<ProductShopDTO> productShopDTOList = new ArrayList<>();
        for (Map<String, Object> map : allByIDProduct) {
            ProductShopDTO productShopDTO = new ProductShopDTO();
            productShopDTO.setIdShop((Long) map.get("id_shop"));
            productShopDTO.setNameShop(shopDAO.findByID((Long) map.get("id_shop")).getName());
            String str = map.get("price").toString();
            Double price = Double.valueOf(str);
            productShopDTO.setPriceShop(price);
            productShopDTO.setDeliveryShop((String) map.get("delivery"));
            productShopDTOList.add(productShopDTO);
        }

        productDTO.setChooseShop(defaultBestPrice(productShopDTOList));
        productDTO.setProductShopDTOList(sortByPriceProductShopDTO(productShopDTOList));
        return productDTO;
    }

    private List<ProductShopDTO> sortByPriceProductShopDTO(List<ProductShopDTO> productShopDTOList) {
        ProductShopDTO a;
        for (int i = 0; i < productShopDTOList.size() - 1; i++) {
            for (int j = 0; j < productShopDTOList.size() - i - 1; j++) {
                if (productShopDTOList.get(j + 1).getPriceShop() < productShopDTOList.get(j).getPriceShop()) {
                    a = productShopDTOList.get(j);
                    productShopDTOList.set(j, productShopDTOList.get(j + 1));
                    productShopDTOList.set(j + 1, a);
                }
            }
        }

        return productShopDTOList;
    }

    public Optional<ProductType> getProductTypeById(Long typeId) {
        return productTypeDAO.findByID(typeId);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeDAO.findAll();
    }
  
    private ProductShopDTO defaultBestPrice(List<ProductShopDTO> productShopDTOList) {
        return productShopDTOList.get(0);
    }
}
