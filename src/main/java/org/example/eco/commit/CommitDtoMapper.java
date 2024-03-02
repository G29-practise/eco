package org.example.eco.commit;

import lombok.RequiredArgsConstructor;
import org.example.eco.commit.dto.CommitCreateDTO;
import org.example.eco.commit.dto.CommitResponseDto;
import org.example.eco.commit.dto.CommitUpdateDTO;
import org.example.eco.commit.entity.Commit;
import org.example.eco.common.mapper.GenericMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommitDtoMapper extends GenericMapper<Commit, CommitCreateDTO, CommitResponseDto, CommitUpdateDTO> {
    private final ModelMapper mapper;
    @Override
    public Commit toEntity(CommitCreateDTO commitCreateDTO) {
        return mapper.map(commitCreateDTO,Commit.class);
    }

    @Override
    public CommitResponseDto toResponseDto(Commit commit) {
        return mapper.map(commit, CommitResponseDto.class);
    }

    @Override
    public void toEntity(CommitUpdateDTO commitUpdateDTO, Commit commit) {
      mapper.map(commitUpdateDTO,commit);
    }
}
