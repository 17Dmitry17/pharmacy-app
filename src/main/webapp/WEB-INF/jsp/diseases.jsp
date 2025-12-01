<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entities.Disease" %>
<html>
<head>
    <title>Болезни</title>
</head>
<body>
    <h1>Список болезней</h1>
    <a href="diseases?action=new">Добавить болезнь</a>
    <br><br>
    <table border="1">
        <tr>
            <th>Название</th>
            <th>Описание</th>
            <th>Действия</th>
        </tr>
        <%
            List<Disease> diseases = (List<Disease>) request.getAttribute("diseases");
            if (diseases != null) {
                for (Disease disease : diseases) {
        %>
        <tr>
            <td><%= disease.getName() %></td>
            <td><%= disease.getDescription() %></td>
            <td>
                <a href="diseases?action=edit&name=<%= disease.getName() %>">Изменить</a>
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
