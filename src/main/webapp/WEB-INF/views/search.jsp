<%@page language="java"%>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/styles/search.css">
    <title>Search Employee</title>
</head>
<body>
    <div class="search-container">
        <form action="/searchForEmp" method="post" class="search-form">
            <input type="text" name="keyword" placeholder="Search by name or Fnumber" class="search-input" />
            <button type="submit" class="search-button">Search</button>
        </form>

        <!-- Back to Home Link -->
        <div class="back-home-wrapper">
            <a href="/dashboard" class="back-home-btn">Back to Dashboard</a>
        </div>
    </div>




</body>
</html>