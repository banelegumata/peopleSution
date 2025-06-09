<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/styles/login.css">
    </head>
    <body>
        <h2>Login</h2>
        <p>${error}</p><br>
        <form action="/loginUserIn" method="post">

            <label for="fNumber">Username:</label>
            <input type="text" id="fNumber" name="fNumber">

            <label for="Password">Password:</label>
            <input type="password" id="password" name="password">

            <input type="submit" value="Submit">
        </form>
    </body>
</html>
