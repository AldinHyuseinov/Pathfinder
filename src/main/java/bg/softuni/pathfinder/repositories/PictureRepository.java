package bg.softuni.pathfinder.repositories;

import bg.softuni.pathfinder.models.entities.Picture;
import bg.softuni.pathfinder.models.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> findAllByRouteId(Long routeId);

    Picture findByUrl(String url);

    @Query("SELECT p FROM Picture AS p JOIN p.route AS r JOIN r.categories AS c WHERE c.name =:name")
    List<Picture> findAllByRouteType(CategoryType name);
}
