package com.BankOfAmerica_Security_App.BankOfAmerica_Security_App.entity;

import java.util.Set;

public enum Role {

    ADMIN(Set.of(Permissions.BANK_READ, Permissions.BANK_WRITE, Permissions.BANK_DELETE)),
    USER(Set.of(Permissions.BANK_READ));

    private final Set<Permissions> permissions;

    Role(Set<Permissions> permissions) {
        this.permissions = permissions;
    }

    public Set<Permissions> getPermissions() {
        return permissions;
    }
}
