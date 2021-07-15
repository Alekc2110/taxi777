<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}"/>
<fmt:setBundle basename="${bundle}"/>

<footer id="footer">
    <div class="center">
        <div class="foot"><img src="${pageContext.request.contextPath}/img/taxi_logo.jpg" style="height: 100px"></div>
        <div class="copyright">
            <div class="textwidget">
                <fmt:message key="footer.info"/>
            </div>
        </div>
    </div>

</footer>
