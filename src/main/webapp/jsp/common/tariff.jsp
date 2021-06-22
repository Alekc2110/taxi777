<%@ page contentType="text/html;charset=UTF-8" %>
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

            <section class="slider_home">
                <ul id="main_slider">
                    <li>
                        <img src="${pageContext.request.contextPath}/img/taxi-777.jpg" class="desktop-slide" width="1680"
                             height="1050">
                        <section class="text_section">
                            <div class="center text">
                                <h1><fmt:message key="home.taxi.tariff.title"/></h1>
                                <span><fmt:message key="home.text.tariff.first"/></span>
                                <h2><span><fmt:message key="home.benefit.text"/></span></h2>
                                <ul>
                                    <li><span><fmt:message key="home.text.tariff.standard"/></span>
                                    </li>
                                    <li><span><fmt:message key="home.text.tariff.comfort"/></span>
                                    </li>
                                    <li><span><fmt:message key="home.text.tariff.minivan"/></span>
                                    </li>
                                    <li><span><fmt:message key="home.text.tariff.wagon"/></span>
                                    </li>
                                </ul>
                            </div>
                        </section>
                        <div class="slide_block">
                            <img src="${pageContext.request.contextPath}/img/est-logo.png" class="mobile-slide" width="320"
                                 height="475">
                        </div>
                    </li>
                </ul>
            </section>
        </div>
    </header>
</div>

<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>