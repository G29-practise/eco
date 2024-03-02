package org.example.eco.address;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.common.service.GenericService;
import org.example.eco.address.dto.AddressCreateDto;
import org.example.eco.address.dto.AddressResponseDto;
import org.example.eco.address.entity.Address;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
@Transactional
public class AddressService extends GenericService<Address, UUID, AddressCreateDto, AddressResponseDto, AddressCreateDto> {
    private final AddressRepository repository;
    private final Class<Address> entityClass = Address.class;
    private final AddressDtoMapper mapper;


    @Override
    protected AddressResponseDto internalCreate(AddressCreateDto addressCreateDto) {
        Address address = mapper.toEntity(addressCreateDto);
        address.setId(UUID.randomUUID());
        Address saved = repository.save(address);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected AddressResponseDto internalUpdate(UUID id, AddressCreateDto addressCreateDto) {
        Address address = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Address not found"));
        mapper.toEntity(addressCreateDto, address);
        Address saved = repository.save(address);
        return mapper.toResponseDto(saved);
    }
}
