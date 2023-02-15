package bg.softuni.pathfinder.services.interfaces;

import bg.softuni.pathfinder.models.dto.AddRouteDTO;
import bg.softuni.pathfinder.models.dto.RouteDetailedInfoDTO;
import bg.softuni.pathfinder.models.dto.RouteInfoDTO;
import bg.softuni.pathfinder.models.enums.CategoryType;

import java.util.List;

public interface RouteService {
    void addRoute(AddRouteDTO addRouteDTO);

    List<RouteInfoDTO> allRoutes();

    RouteDetailedInfoDTO routeDetails(Long routeId);

    List<RouteInfoDTO> allRoutesByType(CategoryType categoryType);
}
