<%--
  Created by a.shestovsky
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User</title>
</head>
<body>
    ${requestScope.user.id}
    ${requestScope.user.firstName}
    ${requestScope.user.lastName}
</body>
</html>