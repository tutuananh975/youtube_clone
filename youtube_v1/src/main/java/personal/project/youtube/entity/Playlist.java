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
public class Playlist extends BaseEntity {
    @Column(
            nullable = false,
            length = 50
    )
    private String playlistName;

    @Column(
            nullable = false
    )
    private String description;

    @Column
    private String avatarImgSrc;

    @Column
    private String coverImgSrc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "channel_id", nullable = false)
    private Channel channel;
}
