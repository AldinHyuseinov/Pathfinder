package bg.softuni.pathfinder.services.impl;

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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final ModelMapper mapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserRegisterDTO userRegisterDTO) {
        User user = mapper.map(userRegisterDTO, User.class);
        user.setLevel(Level.BEGINNER);
        user.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));
        userRepository.saveAndFlush(user);

        Role role = new Role();
        role.setName(RoleType.USER);
        role.setUsers(Set.of(user));
        roleRepository.saveAndFlush(role);
    }

    @Override
    public UserProfileDTO userProfile(String username) {
        return mapper.map(userRepository.findByUsername(username), UserProfileDTO.class);
    }

}
