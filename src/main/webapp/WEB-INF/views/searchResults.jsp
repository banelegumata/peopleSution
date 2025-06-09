<%@page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="/styles/searchResults.css">
</head>
<body>
    <div class="container">
        <h2>Search Results</h2>
        <c:if test="${empty results}">
            <div class="no-results">
                <p>No matching employees found.
                <br>please try <a href="/views/search.jsp">Search</a> again </p>
            </div>
        </c:if>
        <c:forEach var="emp" items="${results}">
            <div class="card">
                <p><strong>Name:</strong> ${emp.empFName} ${emp.empLName}</p>
                <p><strong>Department:</strong> ${emp.empDepartment}</p>
                <p><strong>FNumber:</strong> ${emp.fNumber}</p>
            </div>
        </c:forEach>
    </div>
</body>
</html>
