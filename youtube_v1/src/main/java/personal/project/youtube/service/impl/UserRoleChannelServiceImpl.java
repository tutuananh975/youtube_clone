package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Role;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.UserRoleChannel;
import personal.project.youtube.repository.UserRoleChannelRepository;
import personal.project.youtube.service.UserRoleChannelService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleChannelServiceImpl implements UserRoleChannelService {
    private final UserRoleChannelRepository userRoleChannelRepository;
    @Override
    public UserRoleChannel save(UserRoleChannel entity) {
        return userRoleChannelRepository.save(entity);
    }

    @Override
    public Optional<UserRoleChannel> findById(Long id) {
        return userRoleChannelRepository.findById(id);
    }

    @Override
    public List<Role> findByUserAndChannel(User currentUser, Channel channel) {
        return userRoleChannelRepository.findByUserAndChannel(currentUser, channel);
    }
}
