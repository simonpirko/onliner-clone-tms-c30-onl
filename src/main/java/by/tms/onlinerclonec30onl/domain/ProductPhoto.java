package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductPhoto {

    private Long id;
    private Product product;
    private String photo;
}
