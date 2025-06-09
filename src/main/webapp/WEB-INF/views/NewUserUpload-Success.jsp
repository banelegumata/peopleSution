<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Submission Successful</title>
           <link rel="stylesheet" type="text/css" href="/styles/results.css">
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="success-container">
            <h2>✅ Submission Successful</h2>
            <h3>${message}</h3>

            <div class="employee-details">
                <p><strong>Full Name:</strong> ${employee.empFName} ${employee.empLName}</p>

                <p><strong>Department:</strong> ${employee.empDepartment}</p>
                <p><strong>Job Title:</strong> ${employee.empJobTitle}</p>
                <p><strong>Description:</strong> ${employee.empJobDescription}</p>
                <p><strong>Active:</strong> ${employee.empActiveStatus}</p>
            </div>

            <div class="back-home-wrapper">
                <a href="/dashboard" class="back-home-link">← Back to Dashboard</a>
            </div>
        </div>
    </body>
</html>

