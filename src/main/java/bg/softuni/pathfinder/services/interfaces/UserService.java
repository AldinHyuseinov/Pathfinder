package bg.softuni.pathfinder.services.interfaces;

import bg.softuni.pathfinder.models.dto.UserLoginDTO;
import bg.softuni.pathfinder.models.dto.UserProfileDTO;
import bg.softuni.pathfinder.models.dto.UserRegisterDTO;
import bg.softuni.pathfinder.models.entities.User;
import bg.softuni.pathfinder.models.enums.RoleType;

import java.util.Optional;

public interface UserService {

    void registerUser(UserRegisterDTO userRegisterDTO);

    UserProfileDTO userProfile(String username);
}
