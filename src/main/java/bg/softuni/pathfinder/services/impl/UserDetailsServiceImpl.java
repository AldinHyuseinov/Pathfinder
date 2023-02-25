package bg.softuni.pathfinder.services.impl;

import bg.softuni.pathfinder.models.enums.RoleType;
import bg.softuni.pathfinder.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

@AllArgsConstructor(onConstructor_ = @Autowired)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).map(user -> map(username, user.getPassword()))
                .orElseThrow(() -> new UsernameNotFoundException("User with name " + username + " not found!"));
    }

    private UserDetails map(String username, String password) {
        return new User(username, password, List.of(mapRole()));
    }

    private GrantedAuthority mapRole() {
        return new SimpleGrantedAuthority("ROLE_" + RoleType.USER.name());
    }
}
