<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавить симптом</title>
</head>
<body>
    <h1>Добавить симптом</h1>
    <form method="post" action="symptoms">
        <label>Название:</label><br>
        <input type="text" name="name" required><br><br>

        <input type="submit" value="Сохранить">
    </form>
    <br>
    <a href="symptoms">Назад к списку</a>
</body>
</html>
