package bg.softuni.pathfinder.models.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RouteInfoDTO {
    private Long id;

    private String name;

    private String description;
}
