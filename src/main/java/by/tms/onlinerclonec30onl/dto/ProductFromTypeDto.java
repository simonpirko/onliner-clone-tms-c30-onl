package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.ProductPhoto;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductFromTypeDto {

    private Long id;
    private String name;
    private String description;
    private Double price;
    private List<String> photos;
}
