package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.entities.Category;
import bg.softuni.pathfinder.models.enums.CategoryType;
import bg.softuni.pathfinder.repositories.CategoryRepository;
import bg.softuni.pathfinder.services.interfaces.CategoryService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    @PostConstruct
    public void importCategories() {

        if (categoryRepository.count() <= 0) {
            Category categoryPed = new Category();
            categoryPed.setName(CategoryType.PEDESTRIAN);

            Category categoryBicycle = new Category();
            categoryBicycle.setName(CategoryType.BICYCLE);

            Category categoryMotorcycle = new Category();
            categoryMotorcycle.setName(CategoryType.MOTORCYCLE);

            Category categoryCar = new Category();
            categoryCar.setName(CategoryType.CAR);

            categoryRepository.saveAllAndFlush(List.of(categoryPed, categoryBicycle, categoryMotorcycle, categoryCar));
        }
    }
}
