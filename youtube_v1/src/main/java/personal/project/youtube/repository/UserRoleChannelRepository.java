package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.UserRoleChannel;

import java.util.List;

@Repository
public interface UserRoleChannelRepository extends JpaRepository<UserRoleChannel, Long> {
    List<Role> findByUserAndChannel(User currentUser, Channel channel);
}
