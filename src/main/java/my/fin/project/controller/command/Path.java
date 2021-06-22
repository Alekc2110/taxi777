package my.fin.project.controller.command;

public interface Path {
    // jsp pages
    String PAGE_LOGIN = "/login.jsp";
    String PAGE_TAXI_HOME = "/jsp/common/home_page.jsp";
    String PAGE__ERROR_PAGE = "/WEB-INF/error_page.jsp";
    String PAGE_CLIENT_ACCOUNT = "/WEB-INF/jsp/client/clientAccount.jsp";
    String PAGE_ADMIN_ACCOUNT = "/WEB-INF/jsp/admin/adminAccount.jsp";
    String PAGE_DRIVER_ACCOUNT = "/WEB-INF/jsp/driver/driverAccount.jsp";
    String PAGE_REGISTER_CLIENT = "/jsp/common/registerUser.jsp";
    String PAGE_FORBIDDEN_403 = "/WEB-INF/jsp/error/403.jsp";
    String PAGE_ERROR_500 = "/WEB-INF/jsp/error/500.jsp";
    String PAGE_TAXI_ORDER = "/WEB-INF/jsp/client/order.jsp";
    String PAGE_ORDER_STATUS = "/WEB-INF/jsp/client/orderStatus.jsp";
    String PAGE_ORDER_CONFIRM = "/WEB-INF/jsp/client/orderConfirm.jsp";
    String PAGE_SHOW_DRIVER_ORDERS = "/WEB-INF/jsp/driver/showOrders.jsp";
    String PAGE_SHOW_ALL_ORDERS = "/WEB-INF/jsp/admin/showAllOrders.jsp";


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
    String SHOW_ALL_ORDERS = "/showAllOrders";
    String SHOW_DRIVER_ORDERS = "/showDriverOrders";
    String FORBIDDEN = "/forbidden";
    String ENTER_ORDER = "/order";
    String ENTER_NUMBER_OF_ORDER = "/enterNumOrder";
    String REDIRECT = "redirect#";
    String EMPTY_STR = "";
    String CONFIRM_ORDER= "/confirmOrder";
}
