<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/styles/newDepartment.css">
    </head>
    <body>
        <h2>Create new Department</h2>
        <form action="/addDepartment" method="post">
            <label for="DepName">Department Name:</label>
            <input type="text" id="DepName" name="DepName">

            <label for="DepDesc">Department Description:</label>
            <input type="text" id="DepDesc" name="DepDesc">

            <label for="Company">Company:</label>
            <input type="text" id="Company" name="Company">

            <input type="submit" value="Submit">
        </form>
    </body>
</html>