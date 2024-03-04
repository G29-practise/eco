package org.example.eco.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.address.AddressRepository;
import org.example.eco.address.AddressService;
import org.example.eco.address.entity.Address;
import org.example.eco.common.service.GenericService;
import org.example.eco.order.dto.OrderCreateDto;
import org.example.eco.order.dto.OrderResponseDto;
import org.example.eco.order.dto.OrderUpdateDto;
import org.example.eco.order.entity.Order;
import org.example.eco.product.entity.Product;
import org.example.eco.productSet.ProductSetRepository;
import org.example.eco.productSet.entity.ProductSet;
import org.example.eco.user.UserRepository;
import org.example.eco.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Getter
public class OrderService extends GenericService<Order, UUID, OrderCreateDto, OrderResponseDto, OrderUpdateDto> {
    private final OrderRepository repository;
    private final Class<Order> entityClass = Order.class;
    private final OrderDtoMapper mapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final AddressService addressService;
    private final ProductSetRepository productSetRepository;


    @Override
    protected OrderResponseDto internalCreate(OrderCreateDto orderCreateDto) {

        Order order = mapper.toEntity(orderCreateDto);
        UUID userId = orderCreateDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));


        Address address = new Address();
        String city = orderCreateDto.getCity();
        String region = orderCreateDto.getRegion();
        String country = orderCreateDto.getCountry();
        int postCode = orderCreateDto.getPostCode();
        address.setCity(city);
        address.setCountry(country);
        address.setRegion(region);
        address.setPostCode(postCode);
        address.setId(UUID.randomUUID());
        address.setOrder(order);

        Set<Product> products = new HashSet<>();
        Set<ProductSet> productSets = user.getCart().getProducts();
        if (productSets != null) {
            for (ProductSet productSet : productSets) {
                Product product = productSet.getProduct();
                products.add(product);
            }
        }
        order.setProducts(products);
        order.setId(UUID.randomUUID());
        order.setUser(user);
        order.setAddress(address);

        userRepository.save(user);
        Order saved = repository.save(order);
        addressRepository.save(address);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected OrderResponseDto internalUpdate(UUID uuid, OrderUpdateDto orderUpdateDto) {

        Order order = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(orderUpdateDto, order);
        Order order1 = repository.save(order);

        return mapper.toResponseDto(order1);
    }
}
