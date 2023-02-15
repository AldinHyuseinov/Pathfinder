package bg.softuni.pathfinder.repositories;

import bg.softuni.pathfinder.models.entities.Route;
import bg.softuni.pathfinder.models.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    @Query("SELECT r FROM Route as r JOIN r.categories AS c WHERE c.name =:name")
    List<Route> findAllByCategoryType(CategoryType name);
}
