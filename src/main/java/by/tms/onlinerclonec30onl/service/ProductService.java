package by.tms.onlinerclonec30onl.service;

import by.tms.onlinerclonec30onl.dao.*;
import by.tms.onlinerclonec30onl.domain.Product;
import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import by.tms.onlinerclonec30onl.domain.ProductType;
import by.tms.onlinerclonec30onl.dto.AddProductDTO;
import by.tms.onlinerclonec30onl.dto.ProductDTO;
import by.tms.onlinerclonec30onl.dto.ProductFromTypeDto;
import by.tms.onlinerclonec30onl.dto.ProductShopDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
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
    private ProductPhotoDAO productPhotoDAO;

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
    public AddProductDTO convertProductToAddProductDTO(Product product) {
        //product.setPhotos(inspectPhotoAndSetDefault(product.getPhotos()));
        AddProductDTO addProductDTO = new AddProductDTO();
        addProductDTO.setProductName(product.getName());
        addProductDTO.setProductDescription(product.getDescription());
        if(product.getProductType() != null) {
            addProductDTO.setProductTypeId(product.getProductType().getId());
            addProductDTO.setProductTypeName(product.getProductType().getTypeName());}
        if (product.getPhotos().size() > 0) addProductDTO.setProductPhotoUrl1(product.getPhotos().get(0));
        if (product.getPhotos().size() > 1) addProductDTO.setProductPhotoUrl2(product.getPhotos().get(1));
        if (product.getPhotos().size() > 2) addProductDTO.setProductPhotoUrl3(product.getPhotos().get(2));
        if (product.getPhotos().size() > 3) addProductDTO.setProductPhotoUrl4(product.getPhotos().get(3));
        if (product.getPhotos().size() > 4) addProductDTO.setProductPhotoUrl5(product.getPhotos().get(4));
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
     if(!addProductDTO.getProductPhotoUrl1().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
         photos.add(addProductDTO.getProductPhotoUrl1());
     }
     if (!addProductDTO.getProductPhotoUrl2().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
         photos.add(addProductDTO.getProductPhotoUrl2());
     }
     if (!addProductDTO.getProductPhotoUrl3().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
         photos.add(addProductDTO.getProductPhotoUrl3());
     }
     if (!addProductDTO.getProductPhotoUrl4().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
         photos.add(addProductDTO.getProductPhotoUrl4());
     }
     if (!addProductDTO.getProductPhotoUrl5().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
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
    public Product updateProductByAddProductDTO(Product product, AddProductDTO addProductDTO) {
        product.setName(addProductDTO.getProductName());
        product.setDescription(addProductDTO.getProductDescription());
        product.setProductType(productTypeDAO.findByID(addProductDTO.getProductTypeId()).get());
        List<String> photos = new ArrayList<>();
        if(!addProductDTO.getProductPhotoUrl1().isEmpty() && addProductDTO.getProductPhotoUrl1() != null) {
            photos.add(addProductDTO.getProductPhotoUrl1());
        }
        if (!addProductDTO.getProductPhotoUrl2().isEmpty() && addProductDTO.getProductPhotoUrl2() != null) {
            photos.add(addProductDTO.getProductPhotoUrl2());
        }
        if (!addProductDTO.getProductPhotoUrl3().isEmpty() && addProductDTO.getProductPhotoUrl3() != null) {
            photos.add(addProductDTO.getProductPhotoUrl3());
        }
        if (!addProductDTO.getProductPhotoUrl4().isEmpty() && addProductDTO.getProductPhotoUrl4() != null) {
            photos.add(addProductDTO.getProductPhotoUrl4());
        }
        if (!addProductDTO.getProductPhotoUrl5().isEmpty() && addProductDTO.getProductPhotoUrl5() != null) {
            photos.add(addProductDTO.getProductPhotoUrl5());
        }
        product.setPhotos(photos);
        return product;
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
    //Костыль номер 1...
    public List<ProductPhoto> savePhoto(Product product) {
        List<ProductPhoto> photos = new ArrayList<>();
        product.getPhotos().stream().forEach(photo -> {
            ProductPhoto photoForSave = new ProductPhoto();
            photoForSave.setPhoto(photo);
            photos.add(photoForSave);
        });
        return photos;
    }
    public Optional<ProductType> getProductTypeById(Long typeId) {
        return productTypeDAO.findByID(typeId);
    }

    public List<ProductType> getAllProductTypes() {
        return productTypeDAO.findAll();
    }


}
