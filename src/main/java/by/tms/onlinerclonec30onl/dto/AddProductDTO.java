package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddProductDTO {
 @NotNull
 @NotBlank
 private String productName;
 private String productDescription;
 @NotNull
 @NotBlank
 private long productTypeId;
 private String productTypeName;
 private String productPhotoUrl1;
 private String productPhotoUrl2;
 private String productPhotoUrl3;
 private String productPhotoUrl4;
 private String productPhotoUrl5;

 public AddProductDTO(Product product) {
  this.productName = product.getName();
  this.productDescription = product.getDescription();
  if(product.getProductType() != null) {
   this.productTypeId = product.getProductType().getId();
   this.productTypeName = product.getProductType().getTypeName();}
   this.productPhotoUrl1 = product.getPhotos().get(0);
   this.productPhotoUrl2 = product.getPhotos().get(1);
   this.productPhotoUrl3 = product.getPhotos().get(2);
   this.productPhotoUrl4 = product.getPhotos().get(3);
   this.productPhotoUrl5 = product.getPhotos().get(4);
  }
 }
