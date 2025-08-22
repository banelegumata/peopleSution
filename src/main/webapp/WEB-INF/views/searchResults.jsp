<%@page language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Search Results</title>
    <link rel="stylesheet" type="text/css" href="/styles/searchResults5.css">
</head>
<body>
    <div class="container">
        <h2>Search Results</h2>
        <c:if test="${empty results}">
            <div class="no-results">
                <p>No matching employees found.
                <br>please try <a href="/search">Search</a> again </p>
            </div>
        </c:if>
        <c:forEach var="emp" items="${results}">
            <div class="card">
                <div class="card-left">
                    <p><strong>Name:</strong> ${emp.empFName} ${emp.empLName}</p>
                    <p><strong>F Number:</strong> ${emp.fNumber}</p>
                    <p><strong>Department:</strong> ${emp.empDepartment}</p>
                    <form action="/manage-employee/viewUser" method="post">
                        <input type="hidden" name="empID" value="${emp.empID}" />
                        <button type="submit" class="view-button">View</button>
                    </form>
                </div>
                <c:choose>
                    <c:when test="${empty emp.profileImagePath}">
                        <img src="/images/default-profile.png" alt="Profile Picture" class="profile-picture" />
                    </c:when>
                    <c:otherwise>
                        <img src="${emp.profileImagePath}" alt="Profile Picture" class="profile-picture" />
                    </c:otherwise>
                </c:choose>
            </div>
        </c:forEach>
    </div>
</body>
</html>
