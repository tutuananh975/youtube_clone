package personal.project.youtube.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    @Column(
            nullable = false,
            length = 50
    )
    private String roleName;
}
