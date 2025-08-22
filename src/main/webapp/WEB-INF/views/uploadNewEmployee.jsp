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

    <form:form modelAttribute="employee" action="/manage-employee/addEmployee"  method="post" onsubmit="return this.reportValidity()">
        <label for="empID">Identity number ID:</label>
        <form:input path="empID" id="empID" placeholder="13-digit SA ID fool"
                    required="required" pattern="\\d{13}" minlength="13" maxlength="13" />
        <span class="error-hint">Must be exactly 13 digits</span>

        <label for="empFName">First Name:</label>
        <form:input path="empFName" id="empFName" required="required" />
        <span class="error-hint">First name is required</span>

        <label for="empLName">Last Name:</label>
        <form:input path="empLName" id="empLName" required="required" />
        <span class="error-hint">Last name is required</span>

        <label for="empDepartment">Department:</label>
        <form:select path="empDepartment" id="empDepartment" required="required">
            <form:option value="" label="-- Select Department --" />
            <form:option value="Human Resource" />
            <form:option value="Finance" />
            <form:option value="IT" />
        </form:select>
        <span class="error-hint">Please select a department</span>

        <label for="empActiveStatus">Active Status:</label>
        <form:select path="empActiveStatus" id="empActiveStatus" required="required">
            <form:option value="" label="-- Select Status --" />
            <form:option value="true" />
            <form:option value="false" />
        </form:select>
        <span class="error-hint">Please select a status</span>

        <label for="empJobTitle">Job Title:</label>
        <form:input path="empJobTitle" id="empJobTitle" required="required" />
        <span class="error-hint">Job title is required</span>

        <label for="empJobDescription">Job Description:</label>
        <form:input path="empJobDescription" id="empJobDescription" required="required" />
        <span class="error-hint">Job description is required</span>

        <input type="submit" value="Submit" />
    </form:form>

    <div class="back-home-wrapper">
        <a href="/dashboard" class="back-home-btn">Back to Dashboard</a>
    </div>
</div>


<script>
    document.querySelector("form").addEventListener("submit", function (e) {
        this.querySelectorAll("input[type=text]").forEach(input => {
            input.value = input.value.trim();
        });
    });
</script>

</body>
</html>
