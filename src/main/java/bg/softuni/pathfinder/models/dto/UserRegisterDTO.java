package bg.softuni.pathfinder.models.dto;

import bg.softuni.pathfinder.util.validation.FieldMatch;
import bg.softuni.pathfinder.util.validation.UniqueUsername;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldMatch(first = "password", second = "confirmPassword", message = "Passwords should match.")
public class UserRegisterDTO {
    @Size(min = 2, message = "Username length must be more than 2 characters.")
    @UniqueUsername
    private String username;

    @Size(min = 2, message = "Full name length must be more than 2 characters.")
    private String fullName;

    @NotNull(message = "Email must not be empty.")
    @Email(regexp = ".+@.+", message = "Must be valid email.")
    private String email;

    @NotNull(message = "Age must not be empty.")
    @Min(value = 0, message = "Must be valid age.")
    private Integer age;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters.")
    private String password;

    private String confirmPassword;
}
