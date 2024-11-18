package by.tms.onlinerclonec30onl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Objects;


@Data
@NoArgsConstructor
@ToString
public class ProductShopDTO {
    public String nameShop;
    public String deliveryShop;
    public Double priceShop;
    public Long idShop;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductShopDTO that = (ProductShopDTO) o;
        return Objects.equals(priceShop, that.priceShop);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(priceShop);
    }
}
