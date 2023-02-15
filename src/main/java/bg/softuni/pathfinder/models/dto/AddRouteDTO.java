package bg.softuni.pathfinder.models.dto;

import bg.softuni.pathfinder.models.enums.CategoryType;
import bg.softuni.pathfinder.models.enums.Level;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddRouteDTO {
    @Size(min = 5, message = "Name length must be more than 4 characters.")
    private String name;

    @Size(min = 5, message = "Description length must be more than 4 characters.")
    private String description;

    private String gpxCoordinates;

    @NotNull(message = "Level is required.")
    private Level level;

    @Size(min = 11, max = 11, message = "Only eleven characters are required.")
    private String videoUrl;

    @NotNull(message = "Category is required.")
    private CategoryType type;
}
