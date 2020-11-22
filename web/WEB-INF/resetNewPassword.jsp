<%-- 
    Document   : resetNewPassword
    Created on : Nov 21, 2020, 3:22:59 PM
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
        <h1>Enter a new Password</h1>
        <form name="reset" method="post">
            <input type="password" name="newpassword" value=""><br>
            <input type="hidden" name="uuid" value="${uuid}">
            <input type="submit" value="Submit">
        </form>
        ${message}
    </body>
</html>
