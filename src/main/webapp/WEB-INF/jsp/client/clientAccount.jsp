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
    		</div>

    		<div class="col-lg-6">
    			<ul>
    				<li>
    					<strong><p><c:out value="${sessionScope.loginedUser.username}"/></p></strong>
    				</li>
    				<li class="liFont">
						<p><fmt:message key="client.account.mail"/> <c:out value="${sessionScope.loginedUser.email}"/></p>
    				</li>

    				<li class="liFont">
						<p><fmt:message key="client.account.phone"/> <c:out value="${sessionScope.loginedUser.phoneNumber}"/></p>
    				</li>

    			</ul>
    		</div>
    		</div>
    		<div style="text-align: center;">
	    		<form action="${pageContext.request.contextPath}/taxi/logOut">
	    			<button type="submit" class="logout"><fmt:message key="client.account.logout"/></button>
	    		</form>
    		</div>
    	</div>
    </div>
    </section>

</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>