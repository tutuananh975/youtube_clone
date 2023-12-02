package personal.project.youtube.service;

import personal.project.youtube.entity.Subscribe;
import personal.project.youtube.entity.User;
import personal.project.youtube.exeption.AppException;

import java.util.Map;

public interface SubscribeService extends BaseService<Subscribe> {
    Map<String, Long> subscribing(User currentUser, Long channelId) throws AppException;
}
