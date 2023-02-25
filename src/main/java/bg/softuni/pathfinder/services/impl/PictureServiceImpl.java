package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.dto.AddPictureDTO;
import bg.softuni.pathfinder.models.dto.PictureDTO;
import bg.softuni.pathfinder.models.entities.Picture;
import bg.softuni.pathfinder.models.enums.CategoryType;
import bg.softuni.pathfinder.repositories.PictureRepository;
import bg.softuni.pathfinder.repositories.RouteRepository;
import bg.softuni.pathfinder.repositories.UserRepository;
import bg.softuni.pathfinder.services.interfaces.PictureService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;

    private final RouteRepository routeRepository;

    private final UserRepository userRepository;

    private final ModelMapper mapper;

    @Override
    @Transactional
    public void addPicture(AddPictureDTO pictureDTO, String username) {
        Picture picture = mapper.map(pictureDTO, Picture.class);
        picture.setId(null);
        picture.setRoute(routeRepository.findById(pictureDTO.getTrackId()).orElse(null));
        picture.setAuthor(userRepository.findByUsername(username).orElse(null));

        Picture existingPicture = pictureRepository.findByUrl(picture.getUrl());

        if (existingPicture != null) {
            existingPicture.setTitle(picture.getTitle());
            existingPicture.setRoute(picture.getRoute());
            existingPicture.setAuthor(picture.getAuthor());
            pictureRepository.save(existingPicture);
        } else {
            pictureRepository.save(picture);
        }
    }

    @Override
    public List<PictureDTO> allDistinctPictures() {
        List<PictureDTO> pictureDTOS = new ArrayList<>();

        pictureRepository.findAll().stream()
                .map(picture -> mapper.map(picture, PictureDTO.class)).forEach(pictureDTOS::add);

        removeDuplicates(pictureDTOS);
        return pictureDTOS;
    }

    @Override
    public List<PictureDTO> getAllByRoute(Long routeId) {
        return pictureRepository.findAllByRouteId(routeId).stream().map(picture -> mapper.map(picture, PictureDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PictureDTO> allDistinctPicturesByRouteType(CategoryType categoryType) {
        List<PictureDTO> pictureDTOS = new ArrayList<>();

        pictureRepository.findAllByRouteType(categoryType).stream()
                .map(picture -> mapper.map(picture, PictureDTO.class)).forEach(pictureDTOS::add);

        removeDuplicates(pictureDTOS);
        return pictureDTOS;
    }

    @Override
    public List<PictureDTO> allPictures() {
        return pictureRepository.findAll().stream().map(picture -> mapper.map(picture, PictureDTO.class))
                .collect(Collectors.toList());
    }

    private void removeDuplicates(List<PictureDTO> pictureDTOS) {
        for (int i = 0; i < pictureDTOS.size(); i++) {

            for (int j = 1; j < pictureDTOS.size(); j++) {

                if (i == j) {
                    continue;
                }

                if (pictureDTOS.get(i).getRouteId().longValue() == pictureDTOS.get(j).getRouteId().longValue()) {
                    pictureDTOS.remove(j);
                    j--;
                }
            }
        }
    }
}
