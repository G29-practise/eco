package org.example.eco.order;

import lombok.RequiredArgsConstructor;
import org.example.eco.order.dto.OrderCreateDto;
import org.example.eco.order.dto.OrderResponseDto;
import org.example.eco.order.dto.OrderUpdateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("order")
public class OrderController {
    private final OrderService orderService;
    @PreAuthorize("hasAnyAuthority('order:create')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateDto orderCreateDto) throws IOException {
        OrderResponseDto orderResponseDto = orderService.create(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('order:read')")
    @GetMapping("/{id}")
    public OrderResponseDto getId(@PathVariable UUID id){
        OrderResponseDto orderResponseDto = orderService.get(id);
        return orderResponseDto;
    }

    @PreAuthorize("hasAnyAuthority('order:read')")
    @GetMapping
    public Page<OrderResponseDto> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        return orderService.getAll(predicate, pageable);
    }
    @PreAuthorize("hasAnyAuthority('order:update')")

    @PutMapping("/{id}")
    public OrderResponseDto update(@PathVariable UUID id, @RequestBody OrderUpdateDto updateDTO){
        return orderService.update(id, updateDTO);
    }
    @PreAuthorize("hasAnyAuthority('order:delete')")

    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        orderService.delete(id);
    }

}
