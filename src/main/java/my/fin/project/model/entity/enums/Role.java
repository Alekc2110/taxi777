package my.fin.project.model.entity.enums;

import my.fin.project.model.entity.User;

public enum Role {
    CLIENT, DRIVER, ADMIN;

    public static Role getRole(User user) {
        return user.getRole();
    }

    public String getName() {
        return name().toLowerCase();
    }
}
