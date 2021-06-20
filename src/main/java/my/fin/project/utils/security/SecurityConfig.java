package my.fin.project.utils.security;

import my.fin.project.controller.command.Path;
import my.fin.project.model.entity.enums.Role;

import java.util.*;

public class SecurityConfig {
    private static final Map<Role, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {
        List<String> urlClientPatterns = new ArrayList<>();

        urlClientPatterns.add(Path.MAKE_ORDER);
        urlClientPatterns.add(Path.CLIENT_ACCOUNT);
        urlClientPatterns.add(Path.LOGOUT);
        urlClientPatterns.add(Path.ENTER_ORDER);

        mapConfig.put(Role.CLIENT, urlClientPatterns);

        List<String> urlDRiverPatterns = new ArrayList<>();
        urlDRiverPatterns.add(Path.SHOW_DRIVER_ORDERS_PAG);
        urlDRiverPatterns.add(Path.DRIVER_ACCOUNT);
        urlDRiverPatterns.add(Path.LOGOUT);

        mapConfig.put(Role.DRIVER, urlDRiverPatterns);

        List<String> urlAdminPatterns = new ArrayList<>();
        urlAdminPatterns.add(Path.SHOW_ALL_ORDERS_PAG);
        urlAdminPatterns.add(Path.ADMIN_ACCOUNT);
        urlAdminPatterns.add(Path.LOGOUT);
        mapConfig.put(Role.ADMIN, urlAdminPatterns);
    }

    public static Set<Role> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(Role role) {
        return mapConfig.get(role);
    }
}
