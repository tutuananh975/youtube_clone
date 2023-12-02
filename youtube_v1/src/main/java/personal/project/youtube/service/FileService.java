package personal.project.youtube.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.web.multipart.MultipartFile;
import personal.project.youtube.entity.User;
import personal.project.youtube.entity.Video;
import personal.project.youtube.exeption.AppException;
import personal.project.youtube.payload.response.FileUploadResponse;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

public interface FileService extends BaseService<Video> {
    FileUploadResponse uploadVideo(MultipartFile multipartFile, User currentUser) throws IOException, AppException;

    Map<String, String> saveVideoToDb(Video video) throws AppException;

    InputStreamResource watchVideo(Long videoId) throws FileNotFoundException, AppException;
}
