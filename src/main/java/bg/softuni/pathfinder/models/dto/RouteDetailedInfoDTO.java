package bg.softuni.pathfinder.models.dto;

import bg.softuni.pathfinder.models.enums.Level;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RouteDetailedInfoDTO {
    private String name;

    private double totalDistance;

    private String authorUsername;

    private Level level;

    private String gpxCoordinates;

    private String videoUrl;

    private String description;
}
