package org.example.eco.address;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.address.dto.AddressCreateDto;
import org.example.eco.address.dto.AddressResponseDto;
import org.example.eco.address.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddressDtoMapper extends GenericMapper<Address, AddressCreateDto, AddressResponseDto, AddressCreateDto> {
    private final ModelMapper mapper;

    @Override
    public Address toEntity(AddressCreateDto addressDto) {
        return mapper.map(addressDto, Address.class);
    }

    @Override
    public AddressResponseDto toResponseDto(Address address) {
        return mapper.map(address, AddressResponseDto.class);
    }

    @Override
    public void toEntity(AddressCreateDto addressCreateDto, Address address) {
        mapper.map(addressCreateDto, address);
    }
}
