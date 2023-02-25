package bg.softuni.pathfinder.services.interfaces;

import bg.softuni.pathfinder.models.dto.AddPictureDTO;
import bg.softuni.pathfinder.models.dto.PictureDTO;
import bg.softuni.pathfinder.models.enums.CategoryType;

import java.util.List;

public interface PictureService {
    void addPicture(AddPictureDTO pictureDTO, String username);

    List<PictureDTO> allDistinctPictures();

    List<PictureDTO> getAllByRoute(Long routeId);

    List<PictureDTO> allDistinctPicturesByRouteType(CategoryType categoryType);

    List<PictureDTO> allPictures();
}
