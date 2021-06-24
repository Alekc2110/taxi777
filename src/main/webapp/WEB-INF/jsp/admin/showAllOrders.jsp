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


    <input class="form-control table-sm" id="myInput" type="text" placeholder="Search..">
    <br>
    <div class="ordersHeader">
        <p><fmt:message key="showOrders.orders.title"/></p>
    </div>
    <table id="allOrders" class="table table-bordered table-sm">
        <thead class="thead-light">
        <tr>
            <th data-type="integer" class="th-sm cursor"><fmt:message key="showOrders.orders.id"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="showOrders.street.dep"/></th>
            <th data-type="text" class="th-sm cursor"><fmt:message key="showOrders.street.arr"/></th>
            <th data-type="integer" class="th-sm cursor"><fmt:message key="showOrders.price"/></th>
            <th data-type="date" class="th-sm cursor"><fmt:message key="showOrders.creation.date"/></th>
            <th data-type="integer" class="th-sm cursor"><fmt:message key="showOrders.distance"/></th>
        </tr>
        </thead>
        <tbody id="tbody">
        <c:forEach items="${requestScope.orderList}" var="order" begin="0" end="${requestScope.recordPerPage -1}">
            <tr>
                <td><c:out value="${order.id}"/></td>
                <td><c:out value="${order.originAddress}"/></td>
                <td><c:out value="${order.arriveAddress}"/></td>
                <td><c:out value="${order.cost}"/></td>
                <td><c:out value="${order.creationDate}"/></td>
                <td><c:out value="${order.distance}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<nav aria-label="...">
    <ul class="pagination pagination-sm justify-content-center">
        <c:forEach var="pagNumber" begin="1" end="${requestScope.pageNumbers}">
            <li class="page-item">
                <a class="page-link"
                   href="${pageContext.request.contextPath}/taxi/showAllOrders?pagination=${pagNumber}"><c:out
                        value="${pagNumber}"/></a>
            </li>
        </c:forEach>
    </ul>
</nav>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>

<script src="${pageContext.request.contextPath}/js/search.js"></script>
<script src="${pageContext.request.contextPath}/js/orderSort.js"></script>

</body>
</html>