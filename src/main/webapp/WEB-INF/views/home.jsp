<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Hello Spring ARCUS</title>
</head>
<body>
	<h1>Hello Spring ARCUS!</h1>

	<h4>cache key</h4>
	key: <c:out value="${key}"/></br>

	<h4>cache value:</h4>
    value: <c:out value="${value}"/></br>
	</p>

	<c:if test="${empty value}">
		<p>No cache data found</p>
	</c:if>
</body>
</html>
