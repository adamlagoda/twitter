<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User profile</title>
</head>
<body>
    <h1>Welcome <%= session.getAttribute("user") %></h1>
    <a href="http://localhost:8080/twitter/users.jsp">Browse Users</a>
    <div>
        <form action="publishTweet" method="post">
            <textarea id="tweetArea" name="tweetArea" rows="5" cols="50">
            Place your message here
            </textarea>
            <br/>
            <input type="submit" value="Publish">
        </form>
    </div>
</body>
</html>