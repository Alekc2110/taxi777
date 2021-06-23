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
        <jsp:include page="/WEB-INF/jspf/parts/header1.jsp"/>
        <div class="header_green">
            <jsp:include page="/WEB-INF/jspf/parts/navi_block.jsp"/>
        </div>
    </header>

    <div class="center">
        <form method="POST" action="${pageContext.request.contextPath}/taxi/order">
            <div class="order">
                <div style="height: 60px"></div>
                <div class="formsOrder">
                    <div class="container">
                        <div style="width: 25%; border-bottom: 4px solid #30a3da">
                            <h1 class="order-text"><fmt:message key="taxi.order.place"/></h1>
                        </div>

                        <hr style="width: 97%; margin-bottom: 15px">
                        <c:if test="${param.sameAddress == true}">
                            <p class="errorsM"><fmt:message key="taxi.order.choose.error"/></p>
                        </c:if>
                        <c:if test="${param.notFoundAddress == true}">
                            <p class="errorsM"><fmt:message key="taxi.order.enterAddress.error"/></p>
                        </c:if>
                        <label for="add_dep"><b><fmt:message key="taxi.show.address.dep"/></b></label>
                        <input type="text"  id="add_dep" placeholder="<fmt:message key="taxi.order.place.departure"/>" name="addressDeparture"  required/>

                        <label for="add_arr"><b><fmt:message key="taxi.show.address.arrive"/></b></label>
                        <input type="text" id="add_arr" placeholder="<fmt:message key="taxi.order.place.dest"/>" name="addressArrive"  required/>

                    </div>
                </div>
            </div>

            <div class="order">
                <div style="height: 30px"></div>
                <div class="formsOrder">
                    <div class="container">
                        <div style="width: 25%; border-bottom: 4px solid #30a3da">
                            <h1 class="order-text"><fmt:message key="taxi.order.requirements"/></h1>
                        </div>
                        <hr style="width: 97%; margin-bottom: 15px">
                        <div class="labelOrder"><h1 class="order-text label-text"><fmt:message key="taxi.order.auto.type"/></h1></div>
                        <div class="inputOrder" style="padding: 10px;">

                            <div>
                                <c:if test="${param.noAvailableCar == true}">
                                    <p class="errorsM"><fmt:message key="taxi.order.no.car"/></p>
                                </c:if>
                                <div class="cc-selector">
                                    <div class="radbox">
                                        <input id="car_std" type="radio" name="carType" value="STANDARD" checked="checked"/>
                                        <label class="drinkcard-cc car_std" for="car_std" title="STANDARD"></label>
                                    </div>
                                    <div class="radbox">
                                        <input id="car_comf" type="radio" name="carType" value="COMFORT" checked="checked"/>
                                        <label class="drinkcard-cc car_comf" for="car_comf" title="COMFORT"></label>
                                    </div>
                                    <div class="radbox">
                                        <input id="wagon" type="radio" name="carType" value="UNIVERSAL" checked="checked"/>
                                        <label class="drinkcard-cc wagon" for="wagon" title="UNIVERSAL"></label>
                                    </div>

                                    <div class="radbox">
                                        <input id="van" type="radio" name="carType" value="MINIVAN" checked="checked"/>
                                        <label class="drinkcard-cc van" for="van" title="MINIVAN"></label>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div style="clear: both;"></div>
                    </div>
                </div>
            </div>
            <div class="order">
                <div style="height: 10px"></div>

                <div class="container" style="display: flex;">
                    <button type="submit" class="buttonSub"><fmt:message key="taxi.make.order.client"/></button>
                </div>

                <div style="height: 100px"></div>
            </div>
        </form>
    </div>


</div>
<jsp:include page="/WEB-INF/jspf/parts/footer.jsp"/>
</body>
</html>