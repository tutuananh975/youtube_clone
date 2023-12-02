package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.repository.ChannelRepository;
import personal.project.youtube.service.ChannelService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository channelRepository;
    @Override
    public Channel save(Channel entity) {
        return channelRepository.save(entity);
    }

    @Override
    public Optional<Channel> findById(Long id) {
        return channelRepository.findById(id);
    }

    @Override
    public Channel register() {
        return null;
    }
}
