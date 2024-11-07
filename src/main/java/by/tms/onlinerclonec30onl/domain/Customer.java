package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Customer {

    private Long id;
    private Account account;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}
