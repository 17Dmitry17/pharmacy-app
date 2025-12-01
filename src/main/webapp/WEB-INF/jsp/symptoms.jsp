<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entities.Symptom" %>
<html>
<head>
    <title>Симптомы</title>
</head>
<body>
    <h1>Список симптомов</h1>
    <a href="symptoms?action=new">Добавить симптом</a>
    <br><br>
    <table border="1">
        <tr>
            <th>Название</th>
        </tr>
        <%
            List<Symptom> symptoms = (List<Symptom>) request.getAttribute("symptoms");
            if (symptoms != null) {
                for (Symptom symptom : symptoms) {
        %>
        <tr>
            <td><%= symptom.getName() %></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <br>
    <a href="index.jsp">На главную</a>
</body>
</html>
