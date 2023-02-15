package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.entities.Role;
import bg.softuni.pathfinder.models.enums.RoleType;
import bg.softuni.pathfinder.repositories.RoleRepository;
import bg.softuni.pathfinder.services.interfaces.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    @PostConstruct
    public void importRoles() {

        if (roleRepository.count() <= 0) {
            Role userRole = new Role();
            userRole.setName(RoleType.USER);

            Role moderatorRole = new Role();
            moderatorRole.setName(RoleType.MODERATOR);

            Role adminRole = new Role();
            adminRole.setName(RoleType.ADMIN);

            roleRepository.saveAll(List.of(userRole, moderatorRole, adminRole));
        }
    }
}
