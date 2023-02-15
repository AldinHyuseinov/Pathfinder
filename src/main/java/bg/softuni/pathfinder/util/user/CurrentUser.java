package bg.softuni.pathfinder.util.user;

import bg.softuni.pathfinder.models.enums.RoleType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
@NoArgsConstructor
@Getter
@Setter
public class CurrentUser {
    private Long id;

    private RoleType role;
}
