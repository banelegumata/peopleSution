<%@ page language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/styles/upNewEmp.css">
</head>
<body>
<div class="form-container">
    <h2>Employee Upload</h2>
    <p>${message}</p>
    <form:form modelAttribute="employee" action="/addEmployee" method="post">
        <label for="empID">Identity number ID:</label>
        <form:input path="empID" id="empID" placeholder="ID Number must be 13 Characters long" />
        <form:errors path="empID" cssClass="error-message" />

        <label for="empFName">First Name:</label>
        <form:input path="empFName" id="empFName" placeholder="First Name" />
        <form:errors path="empFName" cssClass="error-message" />

        <label for="empLName">Last Name:</label>
        <form:input path="empLName" id="empLName" placeholder="Last Name" />
        <form:errors path="empLName" cssClass="error-message" />

        <label for="empDepartment">Department:</label>
        <form:select path="empDepartment" id="empDepartment">
            <form:option value="" label="-- Select Department --" />
            <form:option value="Human Resource" />
            <form:option value="Finance" />
            <form:option value="IT" />
        </form:select>
        <form:errors path="empDepartment" cssClass="error-message" />

        <label for="empActiveStatus">Active Status:</label>
        <form:select path="empActiveStatus" id="empActiveStatus">
            <form:option value="" label="-- Select Status --" />
            <form:option value="true" />
            <form:option value="false" />
        </form:select>
        <form:errors path="empActiveStatus" cssClass="error-message" />

        <label for="empJobTitle">Job Title:</label>
        <form:input path="empJobTitle" id="empJobTitle" placeholder="Job Title" />
        <form:errors path="empJobTitle" cssClass="error-message" />

        <label for="empJobDescription">Job Description:</label>
        <form:input path="empJobDescription" id="empJobDescription" placeholder="Enter Full Job Description" />
        <form:errors path="empJobDescription" cssClass="error-message" />

        <input type="submit" value="Submit" />
    </form:form>

    <!-- Back to Home Link -->
    <div class="back-home-wrapper">
        <a href="/dashboard" class="back-home-btn">Back to Dashboard</a>
    </div>
</div>
</body>
</html>
