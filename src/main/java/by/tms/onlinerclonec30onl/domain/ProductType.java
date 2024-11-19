package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ProductType {
    private long id;
    private String typeName;
    private String photo;

    public ProductType(String typeName, String photo) {
        this.typeName = typeName;
        this.photo = photo;
    }
}
