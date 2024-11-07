package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Shop {

    private Long id;
    private Account account;
    private String name;
}
