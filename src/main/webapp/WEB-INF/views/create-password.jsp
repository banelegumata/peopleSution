<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/styles/">
    </head>
    <body>
        <h2>Create your new password</h2>
        <p>${error}</p><br>
        <form action="/submit-password" method="post">
            <input type="hidden" name="fNumber" value="${param.fNumber}"/></br></br>

            <label for="Password">Password:</label>
            <input type="password" id="password" name="password" required/></br></br>

            <label for="ConfirmPassword">Confirm Password:</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" required/></br></br>
            <button type="submit">Submit</button>
        </form>
    </body>
</html>
