package by.tms.onlinerclonec30onl.dto;

import by.tms.onlinerclonec30onl.domain.Account;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserRegistrationDto {

    @NotBlank
    @NotNull
    @Email
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\." +
            "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@" +
            "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]" +
            "(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "Введите действительный email")
    private String username;
    @NotBlank
    @Pattern(regexp = "([a-zA-Z0-9!@$%^&*()_\\\\\\-+]){8,}", message = "Пароль должен содержать не менее 8 символов латинского алфавита верхнего и нижнего регистра")
    private String password;
    private String role;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;

}

