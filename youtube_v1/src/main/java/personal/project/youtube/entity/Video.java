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
public class Video extends BaseEntity {
    @Column(
            nullable = false,
            unique = true,
            length = 50
    )
    private String videoName;

    @Column(
            nullable = false
    )
    private String description;

    @Column(
            nullable = false
    )
    private String avatarImgSrc;

    @Column(
            nullable = false
    )
    private String src;

    @Column(
            columnDefinition = "int default 0"
    )
    private int likes;

    @Column(
            columnDefinition = "int default 0"
    )
    private int views;

    @Column(
            columnDefinition = "int default 0"
    )
    private int unlikes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false)
    @JsonIgnore
    private Channel channel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "playlist_id")
    @JsonIgnore
    private Playlist playlist;

}
