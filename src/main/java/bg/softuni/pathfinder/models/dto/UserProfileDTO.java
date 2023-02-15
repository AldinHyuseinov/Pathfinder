package bg.softuni.pathfinder.models.dto;

import bg.softuni.pathfinder.models.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserProfileDTO {
    private String fullName;

    private String username;

    private int age;

    private Level level;
}
