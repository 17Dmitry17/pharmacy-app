<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entities.Medicine" %>
<html>
<head>
    <title>Лекарства</title>
</head>
<body>
    <h1>Список лекарств</h1>
    <a href="medicines?action=new">Добавить лекарство</a>
    <br><br>
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Срок годности</th>
            <th>Болезнь</th>
            <th>Действия</th>
        </tr>
        <%
            List<Medicine> medicines = (List<Medicine>) request.getAttribute("medicines");
            if (medicines != null) {
                for (Medicine medicine : medicines) {
        %>
        <tr>
            <td><%= medicine.getName() %></td>
            <td><%= medicine.getExpirationDate() %></td>
            <td><%= medicine.getDiseaseName() %></td>
            <td>
                <a href="medicines?action=edit&name=<%= medicine.getName() %>">Изменить</a>
                <a href="medicines?action=delete&name=<%= medicine.getName() %>">Удалить</a>
            </td>
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
