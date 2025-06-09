<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/dashboard.css">
</head>
<body>
    <div class="dashboard-container">
        <h3>DASHBOARD</h3>

        <div class="button-container">
            <a href="${pageContext.request.contextPath}/ManageUsers" class="btn">
                👥 Manage Users
            </a>

            <a href="${pageContext.request.contextPath}/ManageDepartments" class="btn">
                🏢 Manage Departments
            </a>

            <a href="${pageContext.request.contextPath}/logout" class="btn logout-btn">
                🔒 Logout
            </a>
        </div>
    </div>
</body>
</html>