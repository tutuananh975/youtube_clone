package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}
