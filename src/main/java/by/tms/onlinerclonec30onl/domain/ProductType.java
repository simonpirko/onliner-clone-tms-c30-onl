package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class ProductType {
    private long id;
    private String nameType;
    private String nameTable;

    public ProductType(String type, String table) {
        this.nameType = type;
        this.nameTable = table;
    }
}
