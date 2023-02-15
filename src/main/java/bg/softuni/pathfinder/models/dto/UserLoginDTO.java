package bg.softuni.pathfinder.models.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginDTO {
    @Size(min = 2, message = "Username length must be more than 2 characters.")
    private String username;

    @Size(min = 5, max = 20, message = "Password length must be between 5 and 20 characters.")
    private String password;
}
