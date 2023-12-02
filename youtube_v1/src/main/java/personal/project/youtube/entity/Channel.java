package personal.project.youtube.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Channel extends BaseEntity {
    @Column(
            nullable = false,
            length = 50
    )
    private String channelName;

    @Column(
            nullable = false
    )
    private String description;

    @Column
    private String avatarImgSrc;

    @Column
    private String coverImgSrc;

    @Column(
            columnDefinition = "int default 0"
    )
    private Integer numbersOfSubscribe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private User owner;
}
