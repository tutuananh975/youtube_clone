package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import personal.project.youtube.entity.UserLikeVideo;
import personal.project.youtube.repository.LikeRepository;
import personal.project.youtube.service.LikeService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {
    private final LikeRepository likeRepository;
    @Override
    public UserLikeVideo save(UserLikeVideo entity) {
        return likeRepository.save(entity);
    }

    @Override
    public Optional<UserLikeVideo> findById(Long id) {
        return likeRepository.findById(id);
    }
}
