package by.tms.onlinerclonec30onl.domain;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Cart {
    private Account account;
    private List<CartItem> products = new ArrayList<>();
}
