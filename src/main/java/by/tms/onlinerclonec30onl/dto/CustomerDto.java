
package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Account;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CustomerDto {
    private Account account;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;
}