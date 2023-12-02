package personal.project.youtube.mapper;

import org.mapstruct.Mapper;
import personal.project.youtube.entity.User;
import personal.project.youtube.payload.request.RegisterRequest;

@Mapper
public interface UserMapper {
    User toUser(RegisterRequest registerRequest);
}
