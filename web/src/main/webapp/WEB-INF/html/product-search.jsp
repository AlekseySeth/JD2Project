<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Product Search</title>
</head>
<body>
    <div>
        <form action="${pageContext.request.contextPath}/product-search" method="post">
            <input type="text" placeholder="введите название продукта">
            <label for="category">Категория</label>
            <select name="category" id="category">
                <c:forEach var="category" items="${requestScope.categories}">
                    <option value="${category.name}"></option>
                </c:forEach>
            </select>
            <label for="brand"></label>
            <c:forEach var="brand" items="${requestScope.brands}">
                <input id="brand" type="checkbox" value="${brand.name}">${brand.name}
            </c:forEach>
            <button type="submit">Поиск</button>
        </form>
    </div>

</body>
</html>
