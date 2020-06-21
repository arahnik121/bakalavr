<%@ page import="App.Config" %>
<%@ page import="App.Storage.Storage" %>
<%@ page import="App.Storage.SQLStorage" %>
<%@ page import="App.GroundTerritory.ListGroundTerritory" %>
<%@ page import="App.Model.Aircraft" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="App.Model.GroundObject" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/style.css">
    <title>ВКР</title>
    <style>

        #divTable {
            position: absolute;
            top: 50px;
        }

        #divButtons {
            left: 50px;
            position: absolute;
        }

        #divMatrix {
            position: absolute;
            top: 100px;
            width: 300px;
            left: 50%;
            margin-left: -150px;
        }
        a.button24 {
            display: inline-block;
            color: #000000;
            text-decoration: none;
            padding: .5em 2em;
            outline: none;
            border-width: 2px 0;
            border-style: solid none;
            border-color: #80c4f8 #000000 #80c4f8;
            border-radius: 6px;
            background: linear-gradient(#80c4f8, #94bdeb) #94bdeb;
            transition: 0.2s;
        }
        a.button24:hover { background: linear-gradient(#5872eb, #80c4f8) #5872eb; }
        a.button24:active { background: linear-gradient(#80c4f8, #94bdeb) #5872eb; }
        table {
            font-family: "Lucida Sans Unicode", "Lucida Grande", Sans-Serif, serif;
            font-size: 14px;
            border-radius: 10px;
            border-spacing: 0;
            text-align: center;
        }
        th {
            background: #5872eb;
            color: #000000;
            padding: 10px 20px;
        }
        th, td {
            border-style: solid;
            border-width: 0 1px 1px 0;
            border-color: white;
        }
        th:first-child, td:first-child {
            text-align: left;
        }
        th:first-child {
            border-top-left-radius: 10px;
        }
        th:last-child {
            border-top-right-radius: 10px;
            border-right: none;
        }
        td {
            padding: 10px 20px;
            background: #80c4f8;
        }
        tr:last-child td:first-child {
            border-radius: 0 0 0 10px;
        }
        tr:last-child td:last-child {
            border-radius: 0 0 10px 0;
        }
        tr td:last-child {
            border-right: none;
        }
    </style>
</head>
<div id="divButtons">
<jsp:useBean id="aircrafts" scope="request" type="java.util.List"/>
<c:forEach items="${aircrafts}" var="aircraft">
    <jsp:useBean id="aircraft" type="App.Model.Aircraft"/>
    <a href="UAV?id=${aircraft.id}&action=up" class="button24">MoveUp</a>
    <a href="UAV?id=${aircraft.id}&action=down" class="button24">MoveDown</a>
    <a href="UAV?id=${aircraft.id}&action=left" class="button24">MoveLeft</a>
    <a href="UAV?id=${aircraft.id}&action=right" class="button24">MoveRight</a>
    <a href="UAV?id=${aircraft.id}&action=delete" class="button24">Delete</a>
</c:forEach>
</div>
<p></p>
<p></p>
<div id="divMatrix">
<jsp:useBean id="territory_matrix" scope="request" type="App.GroundTerritory.ListGroundTerritory"/>
<%
    for (int i = 0; i < territory_matrix.getSize(); i++) {
        for (int j = 0; j < territory_matrix.getRow(i).size(); j++) {
%>
<%if (territory_matrix.getRow(i).get(j) == 0)%>
    <img src="">
<%
    }
%>
<p></p>
<%
    }
%>
</div>
<p></p>
<p></p>
<div id="divTable">
    <table>
        <tr>
            <th>Координата по оси: X</th>
            <th>Координата по оси: Y</th>
            <th>Классификация объекта</th>
            <th>Номер БПЛА</th>
        </tr>
        <jsp:useBean id="ground_objects" scope="request" type="java.util.List"/>
        <c:forEach items="${ground_objects}" var="groundObject">
        <jsp:useBean id="groundObject" type="App.Model.GroundObject"/>
        <tr>
            <td>${groundObject.x}</td>
            <td>${groundObject.y}</td>
            <td>${groundObject.classification}</td>
            <td>${groundObject.aircraft_id}</td>
            </c:forEach>
        </tr>
    </table>
</div>
</body>
</html>
