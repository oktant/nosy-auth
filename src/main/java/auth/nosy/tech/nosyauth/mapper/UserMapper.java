package auth.nosy.tech.nosyauth.mapper;

import auth.nosy.tech.nosyauth.dto.UserDto;
import auth.nosy.tech.nosyauth.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public static final UserMapper INSTANCE = Mappers.getMapper( UserMapper.class );

    public abstract UserDto toUserDto(User user);
    public abstract User toUser(UserDto userDto);
}
