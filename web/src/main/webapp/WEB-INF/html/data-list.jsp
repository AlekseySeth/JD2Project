<%--
  Created by a.shestovsky
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data List</title>
</head>
<body>
    <h1>Users</h1>
        <c:forEach var="user" items="${requestScope.users}">
            <p>
                ${user.id}
                ${user.firstName}
                ${user.lastName}
            </p>
        </c:forEach>
    <h1>Products</h1>
        <c:forEach var="product" items="${requestScope.products}">
            <p>
                ${product.id}
                ${product.title}
                ${product.price}
                ${product.qtyInStock}
                ${product.category.name}
                ${product.brand.name}
            </p>
        </c:forEach>
    <h1>Categories</h1>
        <c:forEach var="category" items="${requestScope.categories}">
            <p>
                ${category.id}
                ${category.name}
                ${category.description}
            </p>
        </c:forEach>

    <h1>Brands</h1>
        <c:forEach var="brand" items="${requestScope.brands}">
            <p>
                ${brand.id}
                ${brand.name}
            </p>
        </c:forEach>

    <h1>Deliveries</h1>
        <c:forEach var="delivery" items="${requestScope.deliveries}">
            <p>
                ${delivery.id}
                ${delivery.name}
            </p>
        </c:forEach>
</body>
</html>
