<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href=views/style.css>
    </head>
    <body>

        <h2>Employee upload!</h2>

            <form action ="addEmployee" method = "post">
                <label for ="empID"> Employee ID: </label>
                    <input type ="text" id = "empID" name="empID"><br>
                <label for ="empFName"> Employee first Name: </label>
                    <input type ="text" id = "empFName" name="empFName"><br>
                <label for ="empLName"> Employee Last Name: </label>
                    <input type ="text" id = "empLName" name="empLName"><br>
                <label for ="empDepartment"> Department: </label>
                     <select id = "empDepartment" name="empDepartment">
                        <option value="Human Resource">Human Resource</option>
                        <option value="Finance">Finance</option>
                        <option value="IT">IT</option>
                     </select><br>
                <label for ="empActiveStatus"> Active Status: </label>
                    <select id = "empActiveStatus" name="empActiveStatus">
                        <option value="True">True</option>
                        <option value="False">False</option>
                    </select><br>
                <label for ="empJobTitle"> Job Title: </label>
                    <input type ="text" id = "empJobTitle" name="empJobTitle"><br>
                <label for ="empJobDescription"> Job Description: </label>
                    <input type ="text" id = "empJobDescription" name="empJobDescription"><br>

                <input type ="submit" value="Submit">
            </form>
    </body
</html>
