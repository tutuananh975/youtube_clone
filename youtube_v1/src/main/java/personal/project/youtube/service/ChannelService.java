package personal.project.youtube.service;

import personal.project.youtube.entity.Channel;

public interface ChannelService extends BaseService<Channel> {
    Channel register();
}
