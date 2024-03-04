package org.example.eco.cart;

import lombok.RequiredArgsConstructor;
import org.example.eco.cart.dto.CartCreateDto;
import org.example.eco.cart.dto.CartResponseDto;
import org.example.eco.cart.dto.CartUpdateDto;
import org.example.eco.cart.entity.Cart;
import org.example.eco.common.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CartDtoMapper extends GenericMapper<Cart, CartCreateDto, CartResponseDto, CartUpdateDto> {
    private final ModelMapper mapper;
    @Override
    public Cart toEntity(CartCreateDto cartCreateDto) {
        return mapper.map(cartCreateDto,Cart.class);
    }

    @Override
    public CartResponseDto toResponseDto(Cart cart) {
//        String userName = cart.getUser().getName();
        //        cartResponseDto.setUserName(userName);
        return mapper.map(cart, CartResponseDto.class);
    }

    @Override
    public void toEntity(CartUpdateDto cartUpdateDto, Cart cart) {
       mapper.map(cartUpdateDto,cart);
    }
}
