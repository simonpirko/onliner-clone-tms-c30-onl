package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Account;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRegistrationDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String role;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}

