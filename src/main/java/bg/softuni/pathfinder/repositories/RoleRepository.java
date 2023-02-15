package bg.softuni.pathfinder.repositories;

import bg.softuni.pathfinder.models.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role AS r JOIN r.users AS u WHERE u.id = :id")
    Role findByUser(Long id);
}
