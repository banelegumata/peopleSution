
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h2>Edit Employee Information</h2>

    <form:form method="POST" action="/manage-employee/updateUser" modelAttribute="employee">
        <form:hidden path="empID" />

        <label>First Name:</label>
        <form:input path="empFName" /><br/>

        <label>Last Name:</label>
        <form:input path="empLName" /><br/>

        <form:select path="empDepartment" id="empDepartment" required="required">
                <form:option value="" label="-- Select Department --" />
                <form:option value="Human Resource" />
                <form:option value="Finance" />
                <form:option value="IT" />
        </form:select><br/>

        <label>Active Status: </label>
        <p>${employee.empActiveStatus}</p><br/>
        <form:hidden path="empActiveStatus" />

        <label>Job Title:</label>
        <form:input path="empJobTitle" /><br/>

        <label>Job Description:</label>
        <form:input path="empJobDescription" /><br/>

        <label>Employee Number: </label><p>${employee.fNumber}</p><br/>
        <form:hidden path="fNumber" />

        <input type="submit" value="Update" />
    </form:form>
</body>
</html>
