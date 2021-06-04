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
        <div class="header_blue">
            <jsp:include page="/WEB-INF/jspf/parts/navi_block.jsp"/>
        </div>
    </header>
    <div class="center">
        <div class="register">
            <form method="POST" action="${pageContext.request.contextPath}/taxi/register">
                <div class="container">
                    <h1><fmt:message key="register.title"/></h1>
                    <p><fmt:message key="register.ask.to.fill"/></p>
                    <hr>

                    <label><b><fmt:message key="register.name"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.name.error"/></p>
                    </c:if>
                    <input type="text" placeholder="<fmt:message key="register.name"/>" name="name" required>

                    <label><b><fmt:message key="register.phone.number"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.phone.number.error"/></p>
                    </c:if>
                    <c:if test="${param.badPhoneNumber == true}">
                        <p class="errorsM"><fmt:message key="register.bad.phone.number"/></p>
                    </c:if>
                    <input type="text" placeholder="<fmt:message key="register.phone.number"/>" name="phone_number" required>
                    <br>

                    <label><b><fmt:message key="register.email"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.email.error"/></p>
                    </c:if>
                    <c:if test="${param.badEmail == true}">
                        <p class="errorsM"><fmt:message key="register.email.error2"/></p>
                    </c:if>
                    <input type="email" placeholder="<fmt:message key="register.email"/>" name="email" required>
                    <hr>

                    <label><b><fmt:message key="register.password"/></b></label>
                    <c:if test="${param.badInput == true}">
                        <p class="errorsM"><fmt:message key="register.password.error"/></p>
                    </c:if>
                    <input type="password" placeholder="<fmt:message key="register.enter.password"/>" name="password" required>

                    <label><b><fmt:message key="register.repeat.password"/></b></label>
                    <input type="password" placeholder="<fmt:message key="register.repeat.password"/>" name="password_repeat" required>
                    <hr>

                    <p><fmt:message key="register.agree.creating"/><a href="#">
                        <fmt:message key="register.terms.privacy"/></a>.</p>
                    <button type="submit" class="register_btn"><fmt:message key="register.btn.submit"/></button>
                </div>

                <div class="container signin">
                    <p><fmt:message key="register.have.acc"/> <a href="${pageContext.request.contextPath}/taxi/login">
                        <fmt:message key="register.sign.in"/></a>.
                    </p>
                </div>
                <div style="height: 60px"></div>
            </form>
        </div>


    </div>

</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>