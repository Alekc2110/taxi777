package my.fin.project.controller.command;

public interface Path {
    // jsp pages
    String PAGE_LOGIN = "/login.jsp";
    String PAGE_TAXI_HOME = "/jsp/common/home_page.jsp";
    String PAGE__ERROR_PAGE = "/WEB-INF/error_page.jsp";
    String PAGE_CLIENT_ACCOUNT = "/WEB-INF/jsp/client/clientAccount.jsp";
    String PAGE_ADMIN_ACCOUNT = "/WEB-INF/jsp/admin/adminAccount.jsp";
    String PAGE_DRIVER_ACCOUNT = "/WEB-INF/jsp/driver/driverAccount.jsp";


   //commands
    String REGISTER_CLIENT = "/registerClient";
    String REGISTER = "/register";
    String HOME = "/homePage";
    String MAKE_ORDER = "/makeOrder";
    String ENTER_LOGIN = "/enterLogin";
    String LOGIN = "/login";
    String LOGOUT = "/logOut";
    String CLIENT_ACCOUNT = "/clientAccount";
    String DRIVER_ACCOUNT = "/driverAccount";
    String ADMIN_ACCOUNT = "/adminAccount";
    String SHOW_ALL_ORDERS_PAG = "/showAllOrders";
    String SHOW_DRIVER_ORDERS_PAG = "/showDriverOrders";
    String FORBIDDEN = "/forbidden";
  // String ENTER_ORDER = "/enterOrder";
    String SHOW_CLIENT_ORDER = "/showClientOrder";
    String NO_COMMAND = "/noCommand";
    String ENTER_NUMBER_OF_ORDER = "/enterNumOrder";
    String REDIRECT = "redirect#";
    String EMPTY_STR = "";
}
