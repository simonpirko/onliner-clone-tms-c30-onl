package by.tms.onlinerclonec30onl.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeDTO {
    private String typeName;
    private String Photo;
}