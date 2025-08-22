<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Employees</title>
     <link rel="stylesheet" type="text/css" href="/styles/ViewAllUsers2.css">
</head>
<body>

<h2>All Employees</h2><br>
<a href="/search">Search for User</a>
<table>
    <thead>
        <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Department</th>
            <th>Job Title</th>
            <th>Job Description</th>
            <th>Active</th>
            <th>F Number</th>
            <th>Action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="emp" items="${employees}" varStatus="status">
            <c:if test="${status.index != 0}">
                <tr>
                    <td>${emp.empFName}</td>
                    <td>${emp.empLName}</td>
                    <td>${emp.empDepartment}</td>
                    <td>${emp.empJobTitle}</td>
                    <td>${emp.empJobDescription}</td>
                    <td>${emp.empActiveStatus}</td>
                    <td><c:out value="${emp.fNumber}" default="N/A"/></td>
                    <td>
                       <form action="/manage-employee/viewUser" " method="post">
                            <input type="hidden" name="empID" value="${emp.empID}" />
                            <button type="submit" class="view-button">View</button>
                       </form>

                       <form action="/manage-employee/deleteEmployee" method="post">
                            <input type="hidden" name="empID" value="${emp.empID}" />
                            <button type="submit" onclick="return confirm('Are you sure you want to delete this employee?');"class="delete-button">Delete</button>
                       </form>
                    </td>
                </tr>
            </c:if>
        </c:forEach>
    </tbody>
</table>

</body>
</html>