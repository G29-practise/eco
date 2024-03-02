package org.example.eco.commit;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.eco.commit.dto.CommitCreateDTO;
import org.example.eco.commit.dto.CommitResponseDto;
import org.example.eco.commit.dto.CommitUpdateDTO;
import org.example.eco.commit.entity.Commit;
import org.example.eco.common.mapper.GenericMapper;
import org.example.eco.common.repository.GenericRepository;
import org.example.eco.common.service.GenericService;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
@RequiredArgsConstructor
@Getter
public class CommitService extends GenericService<Commit, UUID, CommitCreateDTO, CommitResponseDto, CommitUpdateDTO> {
    private final CommitRepository repository;
    private final Class<Commit> entityClass=Commit.class;
    private final CommitDtoMapper mapper;


    @Override
    protected CommitResponseDto internalCreate(CommitCreateDTO commitCreateDTO) {
        Commit entity = mapper.toEntity(commitCreateDTO);
        Commit save = repository.save(entity);
        return mapper.toResponseDto(save);
    }

    @Override
    protected CommitResponseDto internalUpdate(UUID uuid, CommitUpdateDTO commitUpdateDTO) {

        Commit commit = repository.findById(uuid).orElseThrow(EntityNotFoundException::new);
        mapper.toEntity(commitUpdateDTO,commit);
        repository.save(commit);

        return mapper.toResponseDto(commit);
    }
}
