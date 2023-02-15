package bg.softuni.pathfinder.services.interfaces;

import bg.softuni.pathfinder.models.dto.UserLoginDTO;
import bg.softuni.pathfinder.models.dto.UserProfileDTO;
import bg.softuni.pathfinder.models.dto.UserRegisterDTO;
import bg.softuni.pathfinder.models.entities.User;
import bg.softuni.pathfinder.models.enums.RoleType;

import java.util.Optional;

public interface UserService {
    Optional<User> loginUser(UserLoginDTO userLoginDTO);

    void registerUser(UserRegisterDTO userRegisterDTO);

    RoleType userRole(Long userId);

    UserProfileDTO userProfile(Long userId);
}
