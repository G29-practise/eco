package org.example.eco.commit;

import lombok.RequiredArgsConstructor;
import org.example.eco.commit.dto.CommitCreateDTO;
import org.example.eco.commit.dto.CommitResponseDto;
import org.example.eco.commit.dto.CommitUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequiredArgsConstructor
@RequestMapping("/commit")
public class CommitController {
    private final CommitService commitService;
    @PreAuthorize("hasAnyAuthority('commit:create')")

    @PostMapping("/create")
    public ResponseEntity<CommitResponseDto> create(@RequestBody CommitCreateDTO commitCreateDTO){
        CommitResponseDto commitResponseDto = commitService.create(commitCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(commitResponseDto);
    }
    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/{id}")
    public ResponseEntity<CommitResponseDto> getId(@PathVariable UUID id){
        CommitResponseDto commitResponseDto = commitService.get(id);
        return ResponseEntity.ok(commitResponseDto);
    }
    @PreAuthorize("hasAnyAuthority('commit:read')")

    @GetMapping("/getAll")
    public ResponseEntity<Page<CommitResponseDto>> getAll(Pageable pageable, @RequestParam(required = false) String predicate){
        Page<CommitResponseDto> all = commitService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }
    @PreAuthorize("hasAnyAuthority('commit:update')")

    @PutMapping("/{id}")
    public ResponseEntity<CommitResponseDto> update(@PathVariable UUID id, @RequestBody CommitUpdateDTO updateDTO){
        CommitResponseDto update = commitService.update(id, updateDTO);
        return ResponseEntity.ok(update);
    }
    @PreAuthorize("hasAnyAuthority('commit:delete')")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id){
        commitService.delete(id);
    }
}
