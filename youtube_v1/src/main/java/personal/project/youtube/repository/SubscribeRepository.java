package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.Subscribe;

@Repository
public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
}
