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
 private String productTypePhotoUrl;
 private String productPhotoUrl1;
 private String productPhotoUrl2;
 private String productPhotoUrl3;
 private String productPhotoUrl4;
 private String productPhotoUrl5;
 }
