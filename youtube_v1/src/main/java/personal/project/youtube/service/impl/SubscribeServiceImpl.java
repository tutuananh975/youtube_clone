package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import personal.project.youtube.entity.Channel;
import personal.project.youtube.entity.Subscribe;
import personal.project.youtube.entity.User;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.repository.SubscribeRepository;
import personal.project.youtube.service.ChannelService;
import personal.project.youtube.service.SubscribeService;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {
    private final SubscribeRepository subscribeRepository;
    private final ChannelService channelService;
    @Override
    public Map<String, Long> subscribing(User currentUser, Long channelId) throws AppException {
        Channel channel = channelService.findById(channelId)
                .orElseThrow(() -> new AppException(
                        HttpStatus.NOT_FOUND, "Cannot find channel with id = " + channelId
                ));
        Subscribe subscribe = Subscribe.builder()
                .user(currentUser)
                .channel(channel)
                .build();
        subscribeRepository.save(subscribe);
        Map<String, Long> response = new HashMap<>();
        response.put("subscriberId", currentUser.getId());
        return response;
    }

    @Override
    public Subscribe save(Subscribe entity) {
        return subscribeRepository.save(entity);
    }

    @Override
    public Optional<Subscribe> findById(Long id) {
        return Optional.empty();
    }
}
