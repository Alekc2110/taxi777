<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<div class="header_white clearfix">
    <div class="center">
        <a href="${pageContext.request.contextPath}/taxi/homePage" id="logo" title="HOME_PAGE">
            <img src="${pageContext.request.contextPath}/img/taxi_logo.jpg" width="80px" height="80px">
        </a>
        <div style="float: right; width: 420px">
            <div class="signClient">
                   <c:if test="${sessionScope.loginedUser.role.name().equals('CLIENT')}">
                       <p style="float: left; padding-top: 10px; color: #009EDF">
                           <a href="${pageContext.request.contextPath}/taxi/clientAccount">
                               <c:out value="${sessionScope.loginedUser.username}"/>
                           </a>
                       </p>
                   </c:if>
                   <c:if test="${sessionScope.loginedUser.role.name().equals('ADMIN')}">
                       <p style="float: left; padding-top: 10px; color: #009EDF">
                           <a href="${pageContext.request.contextPath}/taxi/adminAccount">
                               <c:out value="${sessionScope.loginedUser.username}"/>
                           </a>
                       </p>
                   </c:if>
                   <c:if test="${sessionScope.loginedUser.role.name().equals('DRIVER')}">
                       <p style="float: left; padding-top: 10px; color: #009EDF">
                           <a href="${pageContext.request.contextPath}/taxi/driverAccount">
                               <c:out value="${sessionScope.loginedUser.username}"/>
                           </a>
                       </p>
                   </c:if>

                <a href="${pageContext.request.contextPath}/taxi/login">
                    <div style="margin: auto; float: right; height: 36px;">
                        <img src="${pageContext.request.contextPath}/img/enter-icon.jpg" style="height: 56px">
                    </div>
                </a>
            </div>

            <div class="header_city">
                <div class="city_current"><h1><fmt:message key="label.current.city"/></h1></div>
            </div>

            <div class="lang_block">
                <ul id="lang">
                    <li class="lang-item lang-item-5">
                        <a href="?locale=ru">ru</a>
                    </li>
                    <li class="lang-item lang-item-5">
                        <a href="?locale=en">en</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>