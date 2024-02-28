package org.example.eco.user.role.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleBaseDto {
    private String name;
    private Set<String> permissions;
}
