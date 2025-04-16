<%@page language="java"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="/styles/upNewEmp.css">
    </head>
    <body>
        <div class="form-container">
            <h2>Employee Upload</h2>

            <form action="addEmployee" method="post">
                <label for="empID">Employee ID:</label>
                <input type="text" id="empID" name="empID">

                <label for="empFName">First Name:</label>
                <input type="text" id="empFName" name="empFName">

                <label for="empLName">Last Name:</label>
                <input type="text" id="empLName" name="empLName">

                <label for="empDepartment">Department:</label>
                <select id="empDepartment" name="empDepartment">
                    <option value="Human Resource">Human Resource</option>
                    <option value="Finance">Finance</option>
                    <option value="IT">IT</option>
                </select>

                <label for="empActiveStatus">Active Status:</label>
                <select id="empActiveStatus" name="empActiveStatus">
                    <option value="True">True</option>
                    <option value="False">False</option>
                </select>

                <label for="empJobTitle">Job Title:</label>
                <input type="text" id="empJobTitle" name="empJobTitle">

                <label for="empJobDescription">Job Description:</label>
                <input type="text" id="empJobDescription" name="empJobDescription">

                <input type="submit" value="Submit">
            </form>

            <!-- Back to Home Link -->
            <div class="back-home-wrapper">
                <a href="/views/index.jsp" class="back-home-btn">Back to Home</a>
            </div>
        </div>
    </body>
</html>
z