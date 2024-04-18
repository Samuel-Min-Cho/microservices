<%-- 
    Document   : update
    Created on : Apr 10, 2024, 8:54:29 PM
    Author     : student
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <h1>!Inserted!</h1>
        <form action="FrontEnd" method="post"> 
            <input type="hidden" name="pageName" value="search"/>
            Search in Event Name
            <input type="text"  name="query">       
            <input type="submit">
        </form>
    </body>
</html>
