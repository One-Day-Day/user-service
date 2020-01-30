package com.lovecode.system.enums;

public enum RoleType {

    GUEST("guest"),
    DEV("developer"),
    PM("project manager"),
    TL("tech leader"),
    BA("business analyze");

    private String status;

    RoleType(String status) {
        this.status = status;
    }

    public String getName() {
        return this.status;
    }
}
