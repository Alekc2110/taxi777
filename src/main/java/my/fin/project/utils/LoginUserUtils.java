package my.fin.project.utils;

import my.fin.project.model.entity.Person;
import my.fin.project.model.entity.User;

import javax.servlet.http.HttpSession;

public class LoginUserUtils {
    private static final String SESSION_ATTRIBUTE = "loginedUser";

    public static void saveLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute(SESSION_ATTRIBUTE, loginedUser);
    }

    public static User getLoginedUser(HttpSession session) {
        return (User) session.getAttribute(SESSION_ATTRIBUTE);
    }

    public static void updateLoginedUser(HttpSession session, User loginedUser) {
        session.removeAttribute(SESSION_ATTRIBUTE);
        session.setAttribute(SESSION_ATTRIBUTE, loginedUser);
    }
}
