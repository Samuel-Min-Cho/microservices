<%-- 
    Document   : frontpageWithoutLogin
    Created on : Mar 22, 2024, 10:25:18 AM
    Author     : student
--%>

<%-- 
    Document   : frontpageWithoutLogin.jsp
    Created on : Mar 14, 2021, 11:15:12 PM
    Author     : student
--%>

<%@page import= "frontEnd.helper.Event"%>
<%@page import= "frontEnd.helper.EventsXML"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            * {box-sizing: border-box;}

            body {
                margin: 0;
                font-family: Arial, Helvetica, sans-serif;
            }

            .topnav {
                overflow: hidden;
                background-color: #e9e9e9;
            }

            .topnav a {
                float: left;
                display: block;
                color: black;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 17px;
            }

            .topnav a:hover {
                background-color: #ddd;
                color: black;
            }

            .topnav a.active {
                background-color: #2196F3;
                color: white;
            }

            .topnav .login-container {
                float: right;
            }

            .topnav input[type=text] {
                padding: 6px;
                margin-top: 8px;
                font-size: 17px;
                border: none;
                width:120px;
            }

            .topnav .login-container button {
                float: right;
                padding: 6px 10px;
                margin-top: 8px;
                margin-right: 16px;
                background-color: #555;
                color: white;
                font-size: 17px;
                border: none;
                cursor: pointer;
            }

            .topnav .login-container button:hover {
                background-color: green;
            }

            @media screen and (max-width: 600px) {
                .topnav .login-container {
                    float: none;
                }
                .topnav a, .topnav input[type=text], .topnav .login-container button {
                    float: none;
                    display: block;
                    text-align: left;
                    width: 100%;
                    margin: 0;
                    padding: 14px;
                }
                .topnav input[type=text] {
                    border: 1px solid #ccc;  
                }
            }
            table {
                font-family: arial, sans-serif;
                border-collapse: collapse;
                width: 60%;
            }

            td, th {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 15px;
            }

            tr:nth-child(even) {
                background-color: #dddddd;
            }
        </style>
    </head>
    <body>

        <div class="topnav">
            Login into the system to see full information
            <div class="login-container">
                <form action="FrontEnd" method="post">
                    <input type="hidden" name="pageName" value="login"/>
                    <input type="text" placeholder="Username" name="username">
                    <input type="password" placeholder="Password" name="psw">
                    <button type="submit">Login</button>
                </form>

            </div>
        </div>

        <div style="padding-left:16px">
            <h2>Search Events</h2> 
            <form action="FrontEnd" method="post"> 
                <input type="hidden" name="pageName" value="search"/>
                Search in event Name
                <input type="text"  name="query">       
                <input type="submit">
            </form>
        </div>
        <div style="padding-left:16px">
            <p></p>
            <table>
                <tr><th></th><th>code</th><th>Event Name</th></tr>
                        <% EventsXML events = (EventsXML) request.getAttribute("eventResults");
                            if (events != null) {
                                int i = 0;
                                for (Event event : events.getEvents()) {
                                    i++;%><tr><td><%=i%></td><td>
                        <%=event.getCode()%>
                    </td><td><%=event.getEventName()%></td>

                    <%  }
                        }

                    %></tr>

            </table>
        </div>
    </body>
