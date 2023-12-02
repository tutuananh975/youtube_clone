package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.UserLikeVideo;

@Repository
public interface LikeRepository extends JpaRepository<UserLikeVideo, Long> {
}
