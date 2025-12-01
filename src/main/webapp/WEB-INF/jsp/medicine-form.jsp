<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.example.entities.Medicine" %>
<html>
<head>
    <title>Форма лекарства</title>
</head>
<body>
    <%
        Medicine medicine = (Medicine) request.getAttribute("medicine");
        boolean isEdit = medicine != null;
    %>
    <h1><%= isEdit ? "Редактировать лекарство" : "Добавить лекарство" %></h1>
    <form method="post" action="medicines">
        <input type="hidden" name="isEdit" value="<%= isEdit %>">

        <label>Название:</label><br>
        <input type="text" name="name" value="<%= isEdit ? medicine.getName() : "" %>" required <%= isEdit ? "readonly" : "" %>><br><br>

        <label>Срок годности:</label><br>
        <input type="date" name="expirationDate" value="<%= isEdit ? medicine.getExpirationDate() : "" %>" required><br><br>

        <label>Болезнь:</label><br>
        <input type="text" name="diseaseName" value="<%= isEdit ? medicine.getDiseaseName() : "" %>" required><br><br>

        <input type="submit" value="Сохранить">
    </form>
    <br>
    <a href="medicines">Назад к списку</a>
</body>
</html>
