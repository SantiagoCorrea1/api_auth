package com.authb.api_auth.mapper;

import com.authb.api_auth.dto.PermissionDto;
import com.authb.api_auth.entity.Permission;
import com.authb.api_auth.entity.Role;
import com.authb.api_auth.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PermissionMapper {

    private static RoleRepository roleRepository;
    public PermissionMapper(RoleRepository roleRepository){
        PermissionMapper.roleRepository = roleRepository;
    }

    public static PermissionDto toPermissionDto(Permission permission){
        Set<String> roleNames = new HashSet<>();
        for (Role role: permission.getRoles()) {
            roleNames.add(role.getName());
        }
        return new PermissionDto(
                permission.getId(),
                permission.getName(),
                permission.getDescription(),
                roleNames
        );
    }

    public static Permission toPermission(PermissionDto permissionDto){
        Set<Role>roles = new HashSet<>();
        for (String roleName: permissionDto.getRoleName()) {
            roles.add(roleRepository.findByName(roleName).orElse(null));
        }
        return new Permission(
                permissionDto.getId(),
                permissionDto.getName(),
                permissionDto.getDescription(),
                roles
        );
    }
}
