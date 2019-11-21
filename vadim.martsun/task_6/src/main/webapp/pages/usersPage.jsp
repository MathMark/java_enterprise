<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Bank</title>
</head>
<body>
<style>
    DIV.table
    {
        display:table; width: 100%;;
    }

    FORM.tr, DIV.tr
    {
        display:table-row;
        padding: 10px;
        margin: 10px;
    }
    SPAN.td
    {
        display:table-cell;
        padding: 10px;
        margin: 10px;
    }
    SPAN.th
    {
        display:table-cell;
        font-weight: bold;
    }
</style>

<div class="table">
    <div class="tr">
        <span class="th">First Name</span>
        <span class="th">Last Name</span>
        <span class="th">Birth Date</span>
        <span class="th">Gender</span>
    </div>
    <c:forEach items="${users}" var="user">

        <form:form class="tr" modelAttribute="userAttribute" method="post">
            <span class="td"><c:out value="${user.firstName}"/></span>
            <span class="td"><c:out value="${user.lastName}"/></span>
            <span class="td"><c:out value="${user.birthDate}"/></span>
            <span class="td"><c:out value="${user.gender}"/></span>
        </form:form>
    </c:forEach>
</div>


<input type="button" onclick="location.href='add';" value="Add new user"/>
</body>
</html>