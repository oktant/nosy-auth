package auth.nosy.tech.nosyauth.mapper;

import auth.nosy.tech.nosyauth.dto.LoginUserDto;
import auth.nosy.tech.nosyauth.model.LoginUser;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class LoginUserMapper {
    public static final LoginUserMapper INSTANCE = Mappers.getMapper( LoginUserMapper.class );

    public abstract LoginUserDto toLoginUserDto(LoginUser loginUser);
    public abstract LoginUser toLoginUser(LoginUserDto loginUserDto);
}
