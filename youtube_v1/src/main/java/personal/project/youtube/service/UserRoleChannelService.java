package personal.project.youtube.service;

import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.UserRoleChannel;

import java.util.List;

public interface UserRoleChannelService extends BaseService<UserRoleChannel> {
    List<Role> findByUserAndChannel(User currentUser, Channel channel);
}
