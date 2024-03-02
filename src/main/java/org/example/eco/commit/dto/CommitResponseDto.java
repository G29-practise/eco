package org.example.eco.commit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommitResponseDto extends CommitBaseDTO{
    private UUID id;
}
