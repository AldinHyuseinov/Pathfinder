package bg.softuni.pathfinder.repositories;

import bg.softuni.pathfinder.models.entities.Category;
import bg.softuni.pathfinder.models.enums.CategoryType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(CategoryType name);
}
