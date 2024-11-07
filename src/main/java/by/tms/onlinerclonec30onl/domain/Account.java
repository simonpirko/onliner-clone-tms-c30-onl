package by.tms.onlinerclonec30onl.domain;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Account {

    private Long id;
    private String username;
    private String password;
    private Role role;

    public enum Role {
        ADMIN, USER, BUSINESS
    }

}
