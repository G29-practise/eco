package org.example.eco.comment;

import lombok.RequiredArgsConstructor;
import org.example.eco.comment.dto.CommentCreateDTO;
import org.example.eco.comment.dto.CommentResponseDto;
import org.example.eco.comment.dto.CommentUpdateDTO;
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
@RequestMapping("/commit")
public class CommentController {
    private final CommentService commitService;
    @PreAuthorize("hasAnyAuthority('commit:create')")

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> create(@RequestBody CommentCreateDTO commitCreateDTO) throws IOException {
        CommentResponseDto commitResponseDto = commitService.create(commitCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commitResponseDto);
    }
    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponseDto> getId(@PathVariable UUID id){
        CommentResponseDto commitResponseDto = commitService.get(id);
        return ResponseEntity.ok(commitResponseDto);
    }
    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/getAll")
    public ResponseEntity<Page<CommentResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<CommentResponseDto> all = commitService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }
    @PreAuthorize("hasAnyAuthority('commit:update')")

    @PutMapping("/{id}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable UUID id, @RequestBody CommentUpdateDTO updateDTO){
        CommentResponseDto update = commitService.update(id, updateDTO);
        return ResponseEntity.ok(update);
    }
    @PreAuthorize("hasAnyAuthority('commit:delete')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        commitService.delete(id);
    }
}
