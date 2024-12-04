package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.ProductDAO;
import by.tms.onlinerclonec30onl.dao.ProductTypeDAO;
import by.tms.onlinerclonec30onl.dao.ShopDAO;
import by.tms.onlinerclonec30onl.dao.ShopProductDAO;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.AddProductDTO;
import by.tms.onlinerclonec30onl.dto.ProductDTO;
import by.tms.onlinerclonec30onl.dto.ProductFromTypeDto;
import by.tms.onlinerclonec30onl.dto.ProductShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
        productsFromTypeDto = mapperProductFromTypeDto(products);
        return productsFromTypeDto;
    }

    public List<ProductFromTypeDto> mapperProductFromTypeDto(List<Product> products) {
        List<ProductFromTypeDto> productsFromTypeDto = new ArrayList<>();
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

    public List<ProductFromTypeDto> searchProductsFromTypeDto(String search) {
        List<ProductFromTypeDto> productsFromTypeDto = new ArrayList<>();
        List<Product> products = productDAO.findAllByName(search);
        productsFromTypeDto = mapperProductFromTypeDto(products);
        return productsFromTypeDto;
    }

    public ProductDTO getProductPageData(Long id) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProduct(productDAO.findByID(id).get());
        List<Map<String, Object>> allByIDProduct = shopProductDAO.findAllByIDProduct(id);
        List<ProductShopDTO> productShopDTOList = new ArrayList<>();
        for (Map<String, Object> map : allByIDProduct) {
            ProductShopDTO productShopDTO = new ProductShopDTO();
            productShopDTO.setIdShop((Long) map.get("id_shop"));
            productShopDTO.setNameShop(shopDAO.findByID((Long) map.get("id_shop")).get().getName());
            String str = map.get("price").toString();
            Double price = Double.valueOf(str);
            productShopDTO.setPriceShop(price);
            productShopDTO.setDeliveryShop((String) map.get("delivery"));
            productShopDTOList.add(productShopDTO);
        }
        productDTO.getProduct().setPhotos(inspectPhotoAndSetDefault(productDTO.getProduct().getPhotos()));

        productDTO.setProductShopDTOList(sortByPriceProductShopDTO(productShopDTOList));


        return productDTO;
    }
    public AddProductDTO getProductPageDataForAddProduct(Product product) {
        product.setPhotos(inspectPhotoAndSetDefault(product.getPhotos()));
        AddProductDTO addProductDTO = new AddProductDTO(product);
        return addProductDTO;
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
    public Product convertAddProductDTOToProduct(AddProductDTO addProductDTO) {
     Product product = new Product();
     product.setName(addProductDTO.getProductName());
     product.setDescription(addProductDTO.getProductDescription());
     product.setProductType(productTypeDAO.findByID(addProductDTO.getProductTypeId()).get());
     List<String> photos = new ArrayList<>();
     if(!addProductDTO.getProductPhotoUrl1().isEmpty()) {
         photos.add(addProductDTO.getProductPhotoUrl1());
     }
     if (!addProductDTO.getProductPhotoUrl2().isEmpty()) {
         photos.add(addProductDTO.getProductPhotoUrl2());
     }
     if (!addProductDTO.getProductPhotoUrl3().isEmpty()) {
         photos.add(addProductDTO.getProductPhotoUrl3());
     }
     if (!addProductDTO.getProductPhotoUrl4().isEmpty()) {
         photos.add(addProductDTO.getProductPhotoUrl4());
     }
     if (!addProductDTO.getProductPhotoUrl5().isEmpty()) {
         photos.add(addProductDTO.getProductPhotoUrl5());
     }
     product.setPhotos(photos);
        return product;
    }

    public ProductType convertAddProductDTOToProductType(AddProductDTO addProductDTO) {
        ProductType productType = new ProductType();
        productType.setTypeName(addProductDTO.getProductTypeName());
        productType.setPhoto(addProductDTO.getProductTypePhotoUrl());
        return productType;
    }

    private List<String> inspectPhotoAndSetDefault(List<String> photos) {
        String defaultNoImage = "https://www.sales-soluciones.es/server/Portal_0010494/img/products/no_image_xxl.jpg";
        if (photos.isEmpty()) {
            for (int i = 0; i < 5; i++) {
                photos.add(defaultNoImage);
            }
        }
        if (!photos.isEmpty() && photos.size()<=5) {
            for (int i = photos.size(); photos.size() < 5; i++) {
                photos.add(defaultNoImage);
            }
        }
        return photos;
    }

    public Optional<ProductType> getProductTypeById(Long typeId) {
        return productTypeDAO.findByID(typeId);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeDAO.findAll();
    }


}
