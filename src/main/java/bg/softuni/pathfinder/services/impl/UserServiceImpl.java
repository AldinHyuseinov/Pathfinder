package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.dto.UserLoginDTO;
import bg.softuni.pathfinder.models.dto.UserProfileDTO;
import bg.softuni.pathfinder.models.dto.UserRegisterDTO;
import bg.softuni.pathfinder.models.entities.Role;
import bg.softuni.pathfinder.models.entities.User;
import bg.softuni.pathfinder.models.enums.Level;
import bg.softuni.pathfinder.models.enums.RoleType;
import bg.softuni.pathfinder.repositories.RoleRepository;
import bg.softuni.pathfinder.repositories.UserRepository;
import bg.softuni.pathfinder.services.interfaces.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;


@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper mapper;

    @Override
    public Optional<User> loginUser(UserLoginDTO userLoginDTO) {
        return userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
    }

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = mapper.map(userRegisterDTO, User.class);
        user.setLevel(Level.BEGINNER);
        userRepository.saveAndFlush(user);

        Role role = new Role();
        role.setName(RoleType.USER);
        role.setUsers(Set.of(user));
        roleRepository.saveAndFlush(role);
    }

    @Override
    public RoleType userRole(Long userId) {
        return roleRepository.findByUser(userId).getName();
    }

    @Override
    public UserProfileDTO userProfile(Long userId) {
        return mapper.map(userRepository.findById(userId), UserProfileDTO.class);
    }

}
