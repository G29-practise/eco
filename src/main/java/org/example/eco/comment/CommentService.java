package org.example.eco.comment;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.comment.dto.CommentCreateDTO;
import org.example.eco.comment.dto.CommentResponseDto;
import org.example.eco.comment.dto.CommentUpdateDTO;
import org.example.eco.comment.entity.Comment;
import org.example.eco.common.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@Getter
public class CommentService extends GenericService<Comment, UUID, CommentCreateDTO, CommentResponseDto, CommentUpdateDTO> {
    private final CommentRepository repository;
    private final Class<Comment> entityClass= Comment.class;
    private final CommentDtoMapper mapper;


    @Override
    protected CommentResponseDto internalCreate(CommentCreateDTO commitCreateDTO) {
        Comment entity = mapper.toEntity(commitCreateDTO);
        Comment save = repository.save(entity);
        return mapper.toResponseDto(save);
    }

    @Override
    protected CommentResponseDto internalUpdate(UUID uuid, CommentUpdateDTO commitUpdateDTO) {

        Comment commit = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(commitUpdateDTO,commit);
        repository.save(commit);

        return mapper.toResponseDto(commit);
    }
}
