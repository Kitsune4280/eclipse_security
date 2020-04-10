package com.edu.security.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import static com.edu.security.security.UserPermission.*;

import com.google.common.collect.Sets;

public enum UserRole {
	ADMIN(Sets.newHashSet(PERSON_READ, PERSON_WRITE, DOCTOR_READ, DOCTOR_WRITE, INTERN_READ, INTERN_WRITE)),
    DOCTOR(Sets.newHashSet(PERSON_READ, PERSON_WRITE, INTERN_READ)),
    INTERN(Sets.newHashSet(PERSON_READ)),
    PERSON(Sets.newHashSet());


    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority>  getGrantedAuthorities(){

        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        System.out.println("------------------------------");
        System.out.println(this.toString());
        System.out.println(permissions);
        return permissions;
    }

}
