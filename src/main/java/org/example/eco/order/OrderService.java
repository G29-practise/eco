package org.example.eco.order;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.address.AddressRepository;
import org.example.eco.address.AddressService;
import org.example.eco.address.entity.Address;
import org.example.eco.cart.CartRepository;
import org.example.eco.cart.entity.Cart;
import org.example.eco.common.exceptions.ProductException;
import org.example.eco.common.service.GenericService;
import org.example.eco.order.dto.OrderCreateDto;
import org.example.eco.order.dto.OrderResponseDto;
import org.example.eco.order.dto.OrderUpdateDto;
import org.example.eco.order.entity.Order;
import org.example.eco.product.ProductRepository;
import org.example.eco.product.entity.Product;
import org.example.eco.productSet.ProductSetRepository;
import org.example.eco.productSet.entity.ProductSet;
import org.example.eco.user.UserRepository;
import org.example.eco.user.entity.User;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;

    @Override
    protected OrderResponseDto internalCreate(OrderCreateDto orderCreateDto) {

        Order order = mapper.toEntity(orderCreateDto);

        UUID userId = orderCreateDto.getUserId();
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + userId));

        String city = orderCreateDto.getCity();
        String region = orderCreateDto.getRegion();
        String country = orderCreateDto.getCountry();
        int postCode = orderCreateDto.getPostCode();
        Address address = new Address(UUID.randomUUID(), city, country, postCode, region, order);

        Cart cart = user.getCart();
        Set<ProductSet> allProductInCart = cart.getProducts();
        List<ProductSet> orderedProducts = new ArrayList<>();

        if (allProductInCart != null) {
            order.setId(UUID.randomUUID());
            order.setUser(user);
            order.setAddress(address);
            address.setOrder(order);
            for (ProductSet productSet : allProductInCart) {
                Optional<Product> optionalProduct = productRepository.findById(productSet.getProduct().getId());
                if (optionalProduct.isPresent()) {
                    Product product = optionalProduct.get();
                    int availableQuantity = product.getQuantity();
                    if (availableQuantity >= productSet.getQuantity()) {
                        product.setQuantity(availableQuantity - productSet.getQuantity());
                        productRepository.save(product);
                        orderedProducts.add(productSet);
                    } else {
                        throw new ProductException("Oops! Available quantity of products : " + availableQuantity);
                    }
                } else {
                    throw new ProductException("No Product Found With This Product Id : " + productSet.getProduct());
                }
            }
        }

//        cart.getProducts().clear();
        order.setProducts(orderedProducts);
//        productSetRepository.deleteAllById(s -> orderedProducts.stream().forEach(orderedProducts.get());
        cart.setProducts(new HashSet<>());
//        cartRepository.save(cart);
        Order saved = repository.save(order);
        addressRepository.save(address);
        userRepository.save(user);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected OrderResponseDto internalUpdate(UUID uuid, OrderUpdateDto orderUpdateDto) {

        Order order = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(orderUpdateDto, order);
        Order saved = repository.save(order);

        return mapper.toResponseDto(saved);
    }
}
