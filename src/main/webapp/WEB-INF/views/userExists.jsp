<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Submission Successful</title>
       <link rel="stylesheet" type="text/css" href="/styles/userExists.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="success-container">
        <h2>❌ Submission Unsuccessfull</h2>
        <h3>${message}</h3>

        <div class="employee-details">
            <p>
                Please either <a href="/login">Login/Register</a> (if user is active) or reactive their account and update their details on the system.
            </p>
       </div>

        <div class="back-home-wrapper">
            <a href="/dashboard" class="back-home-link">← Back to Home</a>
        </div>
    </div>
</body>
</html>

