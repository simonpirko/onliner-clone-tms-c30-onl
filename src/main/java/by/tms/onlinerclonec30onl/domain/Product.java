package by.tms.onlinerclonec30onl.domain;

import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Product {

    private Long id;
    private ProductType productType;
    private String name;
    private String description;
    private List<String> photos;
}
