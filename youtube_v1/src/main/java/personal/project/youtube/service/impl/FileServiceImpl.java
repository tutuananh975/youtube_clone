package personal.project.youtube.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.Video;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.payload.response.FileUploadResponse;
import personal.project.youtube.repository.VideoRepository;
import personal.project.youtube.service.FileService;
import personal.project.youtube.util.FileUtil;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    private final VideoRepository videoRepository;
    @Override
    public FileUploadResponse uploadVideo(MultipartFile multipartFile, User currentUser) throws AppException {
        String username = currentUser.getUsername();
        String fileName = getFileName(multipartFile);
        Long size = multipartFile.getSize();
        String fileCode;
        try {
            fileCode = FileUtil.saveFile(fileName, multipartFile, username);
        } catch (IOException ex) {
            throw new AppException("Failure to upload file!");
        }

        return FileUploadResponse.builder()
                .fileName(fileName)
                .size(size)
                .downloadUri("/downloadFile/" + fileCode)
                .fileCode(fileCode)
                .build();
    }

    @Override
    public Map<String, String> saveVideoToDb(Video video) throws AppException {
        try {
            videoRepository.save(video);
            Map<String, String> response = new HashMap<>();
            response.put("message", "save video to db successfully!");
            return response;
        } catch (Exception e) {
            FileUtil.deleteFile(video.getSrc());
            throw new AppException(HttpStatus.INTERNAL_SERVER_ERROR, "Save video failure!");
        }
    }

    @Override
    public InputStreamResource watchVideo(Long videoId) throws FileNotFoundException, AppException {
        Video video = findById(videoId).orElseThrow(() ->
                new FileNotFoundException("Cannot file the video with id = " + videoId)
        );
        File videoFile = new File(video.getSrc());
        if (!videoFile.exists()) {
            throw new AppException("file Not found");
        }
        return new InputStreamResource(new FileInputStream(videoFile));
    }

    private String getFileName(MultipartFile multipartFile) {
        return StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

    @Override
    public Video save(Video entity) {
        return videoRepository.save(entity);
    }

    @Override
    public Optional<Video> findById(Long id) {
        return videoRepository.findById(id);
    }
}
