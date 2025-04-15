<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href=views/style.css>
    </head>

    <body>
        <h2>Login Page</h2><br>
        <a href= views/uploadNewEmployee.jsp>Upload New Employee </a>
        <a href= views/login.jsp>Login/register </a><br><br>

        <form action ="loginUser" method = "post">
            <label for ="empUserName"> Username </label>
                <input type ="text" id = "empUserName" name="empUserName"><br>
            <label for ="empPassword"> Password </label>
                <input type ="password" id = "empPassword" name="empPassword"><br>
            <input type ="submit" value="Login"><br>
        </form>
     </body>
</html>