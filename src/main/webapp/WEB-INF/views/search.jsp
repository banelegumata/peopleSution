
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/styles/search.css">
    <title>Search Employee</title>
</head>
<body>
    <div class="search-container">

        <form:form modelAttribute="employee" action="/manage-employee/searchForEmp" method="post" onsubmit="return this.reportValidity()" class="search-form">

            <label for="empFName">Search by name or Fnumber:</label>
            <form:input path="empFName" id="empFName" placeholder="Search by name or Fnumber" class="search-input" required="required" />

            <input type="submit" value="Search" class="search-button" />
        </form:form>

        <!-- Back to Home Link -->
        <div class="back-home-wrapper">
            <a href="/dashboard" class="back-home-btn">Back to Dashboard</a>
        </div>
    </div>
</body>
</html>
