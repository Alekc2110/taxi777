<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>


<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jspf/parts/head_tag.jsp"/>
<body class="home blog">
<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jspf/parts/header.jsp"/>
        <div class="header_green">
            <jsp:include page="/WEB-INF/jspf/parts/navi_block.jsp"/>
        </div>
    </header>

    <div class="center">
        <div class="nazar">
            <div class="ordersHeader">
                <p><fmt:message key="showOrders.orders.title"/></p>
            </div>
            <div class="viewData">
                <div class="idOrder">
                    <p><strong><fmt:message key="showOrders.orders.id"/></strong></p>
                </div>
                <div class="street">
                    <p><strong><fmt:message key="showOrders.street.dep"/></strong></p>
                </div>
                <div class="street">
                    <p><strong><fmt:message key="showOrders.street.arr"/></strong></p>
                </div>
                <div class="cost">
                    <p><strong><fmt:message key="showOrders.price"/></strong></p>
                </div>
                <div class="date">
                    <p><strong><fmt:message key="showOrders.creation.date"/></strong></p>
                </div>
                <div class="distance">
                    <p><strong><fmt:message key="showOrders.distance"/></strong></p>
                </div>
            </div>
            <c:forEach items="${requestScope.orderList}" var="order" begin="0" end="${requestScope.recordPerPage -1}">
                <div class="viewData data">
                    <div class="idOrder">
                        <p><c:out value="${order.id}"/></p>
                    </div>
                    <div class="street">
                        <p><c:out value="${order.originAddress}"/></p>
                    </div>
                    <div class="street">
                        <p><c:out value="${order.arriveAddress}"/></p>
                    </div>
                    <div class="cost">
                        <p><c:out value="${order.cost}"/></p>
                    </div>
                    <div class="date">
                        <p><c:out value="${order.creationDate}"/></p>
                    </div>
                    <div class="distance">
                        <p><c:out value="${order.distance}"/></p>
                    </div>
                </div>
            </c:forEach>
        </div>
        <nav aria-label="...">
            <ul class="pagination pagination-sm justify-content-center">
                <c:forEach var="pagNumber" begin = "1" end = "${requestScope.pageNumbers}">
                    <li class="page-item">
                        <a class="page-link" href="${pageContext.request.contextPath}/taxi/showAllOrders?pagination=${pagNumber}"><c:out value = "${pagNumber}"/></a>
                    </li>
                </c:forEach>
            </ul>
        </nav>
    </div>
</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>