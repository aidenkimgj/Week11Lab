<%-- 
    Document   : reset
    Created on : Nov 21, 2020, 3:19:49 PM
    Author     : 837033
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reset Password</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <p>Please enter your email address to reset your password.</p>
        <form action="reset" method="post">
            Email address: <input type="text" name="email" value=""><br>
            <input type="submit" value="Submit">
        </form>
        ${message}
    </body>
</html>
