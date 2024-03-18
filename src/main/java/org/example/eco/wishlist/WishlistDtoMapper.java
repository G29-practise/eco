package org.example.eco.wishlist;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.wishlist.dto.WishlistCreateDto;
import org.example.eco.wishlist.dto.WishlistResponseDto;
import org.example.eco.wishlist.dto.WishlistUpdateDto;
import org.example.eco.wishlist.entity.Wishlist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WishlistDtoMapper extends GenericMapper<Wishlist, WishlistCreateDto, WishlistResponseDto, WishlistUpdateDto> {
    private ModelMapper mapper;
    @Override
    public Wishlist toEntity(WishlistCreateDto wishlistCreateDto) {
        return mapper.map(wishlistCreateDto,Wishlist.class);
    }

    @Override
    public WishlistResponseDto toResponseDto(Wishlist wishlist) {
        return mapper.map(wishlist, WishlistResponseDto.class);
    }

    @Override
    public void toEntity(WishlistUpdateDto wishlistUpdateDto, Wishlist wishlist) {
        mapper.map(wishlistUpdateDto,wishlist);
    }
}
