package personal.project.youtube.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import personal.project.youtube.entity.Video;
import personal.project.youtube.payload.request.SaveVideoRequest;

@Mapper
public interface VideoMapper {

    @Mapping(target = "src", source = "fileSrc")
    Video toVideo(SaveVideoRequest saveVideoRequest);
}
