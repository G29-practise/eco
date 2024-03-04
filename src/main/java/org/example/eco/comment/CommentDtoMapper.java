package org.example.eco.comment;

import lombok.RequiredArgsConstructor;
import org.example.eco.comment.dto.CommentCreateDTO;
import org.example.eco.comment.dto.CommentResponseDto;
import org.example.eco.comment.dto.CommentUpdateDTO;
import org.example.eco.comment.entity.Comment;
import org.example.eco.common.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommentDtoMapper extends GenericMapper<Comment, CommentCreateDTO, CommentResponseDto, CommentUpdateDTO> {
    private final ModelMapper mapper;
    @Override
    public Comment toEntity(CommentCreateDTO commitCreateDTO) {
        return mapper.map(commitCreateDTO, Comment.class);
    }

    @Override
    public CommentResponseDto toResponseDto(Comment commit) {
        return mapper.map(commit, CommentResponseDto.class);
    }

    @Override
    public void toEntity(CommentUpdateDTO commitUpdateDTO, Comment commit) {
      mapper.map(commitUpdateDTO,commit);
    }
}
