package com.authb.api_auth.service;

import com.authb.api_auth.dto.RoleDto;
import com.authb.api_auth.entity.Permission;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.repository.PermissionRepository;
import com.authb.api_auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RolService {

    private static PermissionRepository permissionRepository;
    private static RoleRepository roleRepository;

    public RolService(PermissionRepository permissionRepository, RoleRepository roleRepository){
        RolService.permissionRepository = permissionRepository;
        RolService.roleRepository = roleRepository;
    }


    public static RoleDto toRoleDto(Role role){
        Set<String>permissionNames = new HashSet<>();
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
        Set<Permission> permissions = new HashSet<>();
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

    public static Role findById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }
}
