package org.example.eco.commit;

import org.example.eco.commit.dto.CommitCreateDTO;
import org.example.eco.commit.dto.CommitResponseDto;
import org.example.eco.commit.dto.CommitUpdateDTO;
import org.example.eco.commit.entity.Commit;
import org.example.eco.common.repository.GenericRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommitRepository extends GenericRepository<Commit, UUID> {
}
