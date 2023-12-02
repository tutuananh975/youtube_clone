package personal.project.youtube.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

public class FileUtil {
    public static String saveFile(String fileName, MultipartFile multipartFile, String username) throws IOException {
        Path uploadPath = Paths.get("Videos/" + username);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String fileCode = RandomStringUtils.randomAlphanumeric(8);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException exception) {
            throw  new IOException("Could not save file" + fileName, exception);
        }
        return fileCode;
    }
    public static String getFileSrc(String username, String fileCode, String fileName) {
        return "Videos/" + username + "/" + fileCode + "-" + fileName;
    }

    public static void deleteFile(String fileSrc) {
        File fileObj = new File(fileSrc);
        fileObj.delete();
    }

    public String getFileName(MultipartFile multipartFile) {
        return StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
    }

}
