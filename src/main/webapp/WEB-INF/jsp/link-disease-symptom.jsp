<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Связать болезнь с симптомом</title>
</head>
<body>
    <h1>Связать болезнь с симптомом</h1>
    <form method="post" action="link">
        <label>Название болезни:</label><br>
        <input type="text" name="diseaseName" required><br><br>

        <label>Название симптома:</label><br>
        <input type="text" name="symptomName" required><br><br>

        <input type="submit" value="Связать">
    </form>
    <br>
    <a href="index.jsp">На главную</a>
</body>
</html>
