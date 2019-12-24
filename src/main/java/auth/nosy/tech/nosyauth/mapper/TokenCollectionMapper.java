package auth.nosy.tech.nosyauth.mapper;

import auth.nosy.tech.nosyauth.dto.TokenCollectionDto;
import auth.nosy.tech.nosyauth.model.TokenCollection;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class TokenCollectionMapper {
    public static final TokenCollectionMapper INSTANCE = Mappers.getMapper( TokenCollectionMapper.class );
    public abstract TokenCollectionDto toTokenCollectionDto(TokenCollection tokenCollection);
}
