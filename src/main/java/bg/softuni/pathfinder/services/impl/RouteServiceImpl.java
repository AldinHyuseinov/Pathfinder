package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.dto.AddRouteDTO;
import bg.softuni.pathfinder.models.dto.RouteDetailedInfoDTO;
import bg.softuni.pathfinder.models.dto.RouteInfoDTO;
import bg.softuni.pathfinder.models.entities.Route;
import bg.softuni.pathfinder.models.enums.CategoryType;
import bg.softuni.pathfinder.repositories.CategoryRepository;
import bg.softuni.pathfinder.repositories.RouteRepository;
import bg.softuni.pathfinder.repositories.UserRepository;
import bg.softuni.pathfinder.services.interfaces.RouteService;
import bg.softuni.pathfinder.util.gpxparsing.GpxParser;
import bg.softuni.pathfinder.util.gpxparsing.TrackPoint;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RouteServiceImpl implements RouteService {
    private final RouteRepository routeRepository;

    private final CategoryRepository categoryRepository;

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    public void addRoute(AddRouteDTO addRouteDTO, String name) {
        Route route = mapper.map(addRouteDTO, Route.class);
        route.setAuthor(userRepository.findByUsername(name).orElse(null));
        route.getCategories().add(categoryRepository.findByName(addRouteDTO.getType()));

        routeRepository.saveAndFlush(route);
    }

    @Override
    public List<RouteInfoDTO> allRoutes() {
        return routeRepository.findAll().stream().map(route -> mapper.map(route, RouteInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RouteDetailedInfoDTO routeDetails(Long routeId) {
        RouteDetailedInfoDTO routeDetailsDTO = mapper.map(routeRepository.findById(routeId), RouteDetailedInfoDTO.class);
        routeDetailsDTO.setTotalDistance(totalDistance(routeDetailsDTO.getGpxCoordinates()));

        return routeDetailsDTO;
    }

    @Override
    public List<RouteInfoDTO> allRoutesByType(CategoryType categoryType) {
        return routeRepository.findAllByCategoryType(categoryType).stream().map(route -> mapper.map(route, RouteInfoDTO.class))
                .collect(Collectors.toList());
    }

    private double totalDistance(String gpxCoordinates) {
        GpxParser parser = new GpxParser();
        List<TrackPoint> route = parser.parse(gpxCoordinates);
        double totalDistance = 0;

        for (int i = 1; i < route.size(); i++) {
            TrackPoint p1 = route.get(i - 1);
            TrackPoint p2 = route.get(i);
            totalDistance += calculateDistance(p1.getLatitude(), p1.getLongitude(), p2.getLatitude(), p2.getLongitude());
        }
        return totalDistance;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double earthRadius = 6371e3;
        double lat1Rads = Math.toRadians(lat1);
        double lat2Rads = Math.toRadians(lat2);
        double deltaLat = Math.toRadians(lat2 - lat1);
        double deltaLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rads) * Math.cos(lat2Rads) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = earthRadius * c;

        return distance / 1000.0;
    }
}
