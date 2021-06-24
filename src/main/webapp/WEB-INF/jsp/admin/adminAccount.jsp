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

        <div class="header_blue">
            <jsp:include page="/WEB-INF/jspf/parts/navi_block.jsp"/>
        </div>

    </header>

    <div class="center">
        <div class="nazar" >
            <div class="row">
            <div class="col-lg-6">
                <img src="${pageContext.request.contextPath}/img/client-logo.png" style="width: 65% ">

                <div class="row">
                    <div class="col-lg-12">
                        <form class="dr" action="${pageContext.request.contextPath}/taxi/logOut">
                            <button type="submit" class="account-admin-btns"><fmt:message key="admin.account.logout"/></button>
                        </form>
                        <a href="${pageContext.request.contextPath}/taxi/showAllOrders?pagination=1">
                            <button class="account-admin-btns"><fmt:message key="admin.account.show.pag"/></button>
                        </a>
                    </div>
                </div>
            </div>

            <div class="col-lg-6">
                <ul>
                    <li>
                       <strong>
                           <p><c:out value="${sessionScope.loginedUser.username}"/>
                       </strong>
                    </li>

                    <li class="liFont">
                        <p><fmt:message key="admin.phone"/>: <c:out value="${sessionScope.loginedUser.phoneNumber}"/></p>
                    </li>

                    <li class="liFont">
                        <p><fmt:message key="admin.role"/>: <c:out value="${sessionScope.loginedUser.role.name()}"/></p>
                    </li>
                </ul>
            </div>
            </div>
        </div>
    </div>
    </section>
</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>