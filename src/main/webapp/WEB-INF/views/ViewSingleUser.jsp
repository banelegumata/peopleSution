<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <!DOCTYPE html>
 <html>
    <head>
         <meta charset="UTF-8">
         <title>Submission Successful</title>
            <link rel="stylesheet" type="text/css" href="/styles/viewSingleUser.css">
         <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    </head>


    <body>
        <div class="container">
            <p><strong>First Name:</strong> ${employee.empFName}</p>
            <p><strong>Last Name:</strong> ${employee.empLName}</p>
            <p><strong>Department:</strong> ${employee.empDepartment}</p>
            <p><strong>Job Title:</strong> ${employee.empJobTitle}</p>
            <p><strong>Job Description:</strong> ${employee.empJobDescription}</p>
            <p><strong>Employee Number:</strong> ${employee.fNumber}</p>

            <c:choose>
                <c:when test="${empty employee.profileImagePath}">
                    <img src="/images/default-profile.png" alt="Profile Picture" class="profile-picture" />
                </c:when>
                <c:otherwise>
                    <img src="${employee.profileImagePath}" alt="Profile Picture" class="profile-picture" />
                </c:otherwise>
            </c:choose>


            <div class="button-group">

                <form action="/manage-employee/editUser" method="post" style="display:inline;">
                    <input type="hidden" name="empID" value="${employee.empID}" />
                    <button type="submit" class="edit-button">Edit Information</button>
                </form>
                <a href="/manage-employee/ViewAllUsers" class="back-button">Back to All Employees</a>
            </div>


        </div>
    </body>
</html>



