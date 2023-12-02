package personal.project.youtube.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity{

    @Column(
            nullable = false,
            unique = true,
            length = 30
    )
    private String username;

    @Column(
            nullable = false
    )
    private String password;

    @Column(
            nullable = false,
            length = 50
    )
    private String displayName;

    @Column(
            nullable = false
    )
    private String email;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;
}
