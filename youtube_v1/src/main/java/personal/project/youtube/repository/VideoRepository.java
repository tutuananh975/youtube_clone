package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.Video;

@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
