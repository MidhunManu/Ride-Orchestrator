package com.RideOrchestrator.UserMicroService.Enums;

public enum RoleEnum {
    Rider("rider"),
    User("user");

    private final String role;

    RoleEnum(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
