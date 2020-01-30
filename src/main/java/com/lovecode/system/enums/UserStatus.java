package com.lovecode.system.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum UserStatus {
    ACTIVE("can use in system"),
    INACTIVE("can not use in system"),
    ARCHIVE("deleted user");


    private String status;

    UserStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return this.status;
    }

    @JsonCreator
    public static UserStatus fromRole(String status) {
        for (UserStatus type : UserStatus.values()) {
            if (type.getName().equals(status)) {
                return type;
            }
        }
        return null;
    }
}
