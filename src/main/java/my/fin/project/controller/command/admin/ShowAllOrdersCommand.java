package my.fin.project.controller.command.admin;

import my.fin.project.controller.command.Command;
import my.fin.project.model.entity.Order;
import my.fin.project.model.service.OrderService;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static my.fin.project.controller.command.Path.PAGE_SHOW_ALL_ORDERS;


public class ShowAllOrdersCommand extends Command {
    private final Logger LOG = Logger.getLogger(ShowAllOrdersCommand.class);

    private OrderService orderService;
    private static final String PAGINATION_PARAMETER = "pagination";
    private static final String ORDER_LIST_ATTRIBUTE = "orderList";
    private static final String RECORD_PER_PAGE_ATTRIBUTE = "recordPerPage";
    private static final String PAGE_NUMBERS_ATTRIBUTE = "pageNumbers";

    public ShowAllOrdersCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int pageNumber;
        int totalNumberRecords = orderService.getAllOrdersCount();
        int recordPerPage = 5;
        int startIndex;
        int numberOfPages;
        String sPageNo = request.getParameter(PAGINATION_PARAMETER);
        LOG.info("get number of page " + sPageNo);
        pageNumber = getPageNumber(sPageNo);
        startIndex = (pageNumber * recordPerPage) - recordPerPage;
        List<Order> orderList = orderService.getAllOrders(startIndex, recordPerPage);
        LOG.info("get list Orders: " + orderList);
        request.setAttribute(ORDER_LIST_ATTRIBUTE, orderList);
        request.setAttribute(RECORD_PER_PAGE_ATTRIBUTE, recordPerPage);
        numberOfPages = totalNumberRecords / recordPerPage;
        if (totalNumberRecords > numberOfPages * recordPerPage) {
            numberOfPages = numberOfPages + 1;
        }
        LOG.info("set request numberOfPages " + numberOfPages);
        request.setAttribute(PAGE_NUMBERS_ATTRIBUTE, numberOfPages);
        return PAGE_SHOW_ALL_ORDERS;
    }

    private int getPageNumber(String strNumber) {
        try {
            return Integer.parseInt(strNumber);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return 1;
    }
}
