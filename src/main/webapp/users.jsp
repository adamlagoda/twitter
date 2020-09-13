<%@ page import="org.sda.twitter.database.dao.UsersDao" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Browse Users</title>
</head>
<body>
    <%! UsersDao dao = new UsersDao(); %>
    <%! List<String> users = dao.findAll(); %>
    <h1>Users</h1>
    <ul>
        <% for (String user : users) {
        out.println("<li>" + user + "</li>");
            }
        %>
    </ul>
</body>
</html>