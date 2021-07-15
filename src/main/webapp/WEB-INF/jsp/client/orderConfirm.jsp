<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<html lang="${param.lang}">
<jsp:include page="/WEB-INF/jspf/parts/head_tag.jsp"/>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap-grid.css">
<body class="home blog">

<div id="wrapper">
    <header id="header">
        <jsp:include page="/WEB-INF/jspf/parts/header.jsp"/>
        <div class="header_green">
            <jsp:include page="/WEB-INF/jspf/parts/navi_block.jsp"/>
        </div>
    </header>

    <div class="center">
        <div class="nazar" >
            <div class="row">
                <div class="col-lg-6">
                    <img src="${pageContext.request.contextPath}/img/client-logo.png" style="width: 65% ">
                </div>

                <div class="col-lg-6">
                    <strong><p class="order-text-about"><fmt:message key="taxi.show.order.dear"/> <c:out value="${sessionScope.loginedUser.username}"/>
                        <fmt:message key="taxi.show.order.conf"/></p></strong>
                    <p class="order-text-about"> <fmt:message key="taxi.show.order.driver"/> <c:out value="${requestScope.driverName}"/>.
                        <fmt:message key="taxi.show.order.driver.phone.number"/> : <c:out value="${requestScope.phoneNumber}"/></p>
                    <p class="order-text-about"><fmt:message key="taxi.show.order.thanks"/></p>

                    <c:if test="${param.otherCarPropose == true}">
                        <p class="errorsM"><fmt:message key="taxi.order.foundCar.error"/></p>
                    </c:if>
                    <p class="order-text-about"><fmt:message key="taxi.show.order.car.info"/> <c:out value="${requestScope.carModel}"/>
                        <c:out value="${requestScope.carNumber}"/>
                        <fmt:message key="taxi.show.order.car.type"/><c:out value="${requestScope.carType}"/> </p>
                    <p class="order-text-about"><fmt:message key="taxi.show.order.costs"/> <c:out value="${requestScope.price}"/> <fmt:message key="taxi.show.order.money"/></p>
                    <p class="order-text-about"><fmt:message key="taxi.show.order.wait"/> <c:out value="${requestScope.timeWait}"/> <fmt:message key="taxi.show.order.min"/></p>
                    <form class="dr" action="${pageContext.request.contextPath}/taxi/confirmOrder">

                        <button type="submit" class="logout"><fmt:message key="show.order.confirm"/></button>
                    </form>
                    <form class="dr" action="${pageContext.request.contextPath}/taxi/homePage">
                        <button type="submit" class="logout"><fmt:message key="show.order.cancel"/></button>
                    </form>


                </div>
            </div>
            <div>
                <form class="dr" action="${pageContext.request.contextPath}/taxi/homePage">
                    <button type="submit" class="logoutDriver"><fmt:message key="show.order.home"/></button>
                </form>
            </div>
        </div>
    </div>
    </section>
</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>