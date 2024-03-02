package org.example.eco.address;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.address.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AddressRepository extends GenericRepository<Address, UUID> {
}
