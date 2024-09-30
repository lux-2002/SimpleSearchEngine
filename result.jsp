<html>
<head>
    <title>Search Results</title>
</head>
<body>
    <h1>Search Results</h1>
    <%
        String query = request.getParameter("query");
        // Assuming 'results' is a List of website URLs retrieved from the database
        List<String> results = (List<String>) request.getAttribute("results");
        if (results != null && !results.isEmpty()) {
            for (String result : results) {
                out.println("<a href='" + result + "'>" + result + "</a><br>");
            }
        } else {
            out.println("No results found.");
        }
    %>
    <a href="index.jsp">Back to Search</a>
</body>
</html>