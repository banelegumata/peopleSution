<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/styles/newSubDepartment.css">
    </head>
    <body>
        <h2>Create Sub Department</h2>        <form action="/addSubDepartment" method="post">
            <label for="subDepName">Sub Department Name:</label>
            <input type="text" id="subDepName" name="subDepName">

            <label for="subDepDesc">Sub Department Description:</label>
            <input type="text" id="subDepDesc" name="subDepDesc">

                <select id="empDepartment" name="empDepartment">
                    <option value="Human Resource">Human Resource</option>
                    <option value="Finance">Finance</option>
                    <option value="IT">IT</option>
                </select>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>