package bg.softuni.pathfinder.models.entities;

import bg.softuni.pathfinder.models.enums.Level;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "routes")
@Getter
@Setter
public class Route extends BaseEntity {
    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String gpxCoordinates;

    @Enumerated(EnumType.STRING)
    private Level level;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    private User author;

    private String videoUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "route_entity_id"))
    private Set<Category> categories;

    public Route() {
        categories = new HashSet<>();
    }
}
