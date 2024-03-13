package org.example.eco.order;

import lombok.RequiredArgsConstructor;
import org.example.eco.common.App;
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

import static org.example.eco.order.OrderController.BATH_URL;

@RestController
@RequestMapping(App.BASE_PATH + BATH_URL)
@RequiredArgsConstructor
public class OrderController {

    public static final String BATH_URL = "/order";

    private final OrderService orderService;
    @PreAuthorize("hasAnyAuthority('order:create')")
    @PostMapping("/create")
    public ResponseEntity<OrderResponseDto> create(@RequestBody OrderCreateDto orderCreateDto) throws IOException {
        OrderResponseDto orderResponseDto = orderService.create(orderCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('order:read')")
    @GetMapping("/getById/{id}")
    public ResponseEntity<OrderResponseDto> getId(@PathVariable UUID id){
        OrderResponseDto orderResponseDto = orderService.get(id);
        return ResponseEntity.ok(orderResponseDto);
    }

    @PreAuthorize("hasAnyAuthority('order:read')")
    @GetMapping("/getAll")
    public ResponseEntity<Page<OrderResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<OrderResponseDto> orderResponseDto = orderService.getAll(predicate, pageable);
        return ResponseEntity.ok(orderResponseDto);
    }
    @PreAuthorize("hasAnyAuthority('order:update')")
    @PutMapping("/updateById/{id}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable UUID id, @RequestBody OrderUpdateDto updateDTO){
        OrderResponseDto update = orderService.update(id, updateDTO);
        return ResponseEntity.ok(update);
    }

    @PreAuthorize("hasAnyAuthority('order:delete')")
    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        orderService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
