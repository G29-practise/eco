package org.example.eco.user.permission;

import org.example.eco.common.repository.GenericRepository;
import org.example.eco.user.permission.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends GenericRepository<Permission, String> {
}
