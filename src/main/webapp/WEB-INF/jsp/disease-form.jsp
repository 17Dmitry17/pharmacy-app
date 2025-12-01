<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.entities.Disease" %>
<html>
<head>
    <title>Форма болезни</title>
</head>
<body>
    <%
        Disease disease = (Disease) request.getAttribute("disease");
        boolean isEdit = disease != null;
    %>
    <h1><%= isEdit ? "Редактировать болезнь" : "Добавить болезнь" %></h1>
    <form method="post" action="diseases">
        <label>Название:</label><br>
        <input type="text" name="name" value="<%= isEdit ? disease.getName() : "" %>" required><br><br>

        <label>Описание:</label><br>
        <textarea name="description" required><%= isEdit ? disease.getDescription() : "" %></textarea><br><br>

        <input type="submit" value="Сохранить">
    </form>
    <br>
    <a href="diseases">Назад к списку</a>
</body>
</html>
