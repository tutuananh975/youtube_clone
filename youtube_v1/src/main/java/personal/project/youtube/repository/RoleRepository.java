package personal.project.youtube.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import personal.project.youtube.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

//    @Query("select Permission.permissionName " +
//            "from Permission left join RolePermission on Permission = RolePermission.permission " +
//            "left join Role on RolePermission.role = Role " +
//            "left join UserRoleChannel on UserRoleChannel.role = Role " +
//            "where UserRoleChannel.channel = :channel and UserRoleChannel.user = :currentUser")
//    List<String> getPermission(@Param("currentUser") User currentUser, @Param("channel") Channel channel);
}
