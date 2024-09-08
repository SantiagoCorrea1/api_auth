package com.authb.api_auth.mapper;

import com.authb.api_auth.dto.RoleDto;
import com.authb.api_auth.entity.Permission;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.repository.PermissionRepository;
import com.authb.api_auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RolMapper {

    private static RoleRepository roleRepository;
    private static PermissionRepository permissionRepository;

    public RolMapper(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public static RoleDto toRoleDto(Role role){
        Set<String>permissionNames = null;
        for (Permission permission: role.getPermissions()) {
            permissionNames.add(permission.getName());
        }
        return new RoleDto(
                role.getId(),
                role.getName(),
                role.getDescription(),
                permissionNames
        );
    }

    public static Role toRole(RoleDto roleDto){
        Set<Permission> permissions = null;
        for (String permissionName: roleDto.getPermissionNames()) {
            permissions.add(permissionRepository.findByName(permissionName).orElse(null));
        }
        return new Role(
                roleDto.getId(),
                roleDto.getName(),
                roleDto.getDescription(),
                permissions
        );
    }
}
