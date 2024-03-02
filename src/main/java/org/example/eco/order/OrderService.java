package org.example.eco.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.address.AddressRepository;
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
    private final Class<Order> entityClass= Order.class;
    private final OrderDtoMapper mapper;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ProductSetRepository productSetRepository;


    @Override
    protected OrderResponseDto internalCreate(OrderCreateDto orderCreateDto) {

        Order order = mapper.toEntity(orderCreateDto);
        order.setId(UUID.randomUUID());

        User user = userRepository.findById(orderCreateDto.getUser_Id()).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + orderCreateDto.getUser_Id()));
        order.setUser(user);

        Address save = addressRepository.save(orderCreateDto.getAddress());
        order.setAddress(save);

        Set<Product> products1=new HashSet<>();
        for (ProductSet productSet : orderCreateDto.getCart().getProductSets()) {
            Product productId = productSet.getProductId();
            products1.add(productId);
        }

        order.setProductList(products1);


        return mapper.toResponseDto(order);
    }

    @Override
    protected OrderResponseDto internalUpdate(UUID uuid, OrderUpdateDto orderUpdateDto) {

        Order order = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(orderUpdateDto,order);
        Order order1 = repository.save(order);

        return mapper.toResponseDto(order1);
    }
}
