<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="org.example.entities.Disease" %>
<%@ page import="org.example.entities.Medicine" %>
<html>
<head>
    <title>Поиск</title>
</head>
<body>
    <h1>Поиск</h1>

    <h2>Поиск болезней по симптому</h2>
    <form method="get" action="search">
        <input type="hidden" name="type" value="diseasesBySymptom">
        <label>Название симптома:</label><br>
        <input type="text" name="symptomName" required><br><br>
        <input type="submit" value="Найти болезни">
    </form>

    <%
        List<Disease> diseases = (List<Disease>) request.getAttribute("diseases");
        String symptomName = (String) request.getAttribute("symptomName");
        if (diseases != null) {
    %>
        <h3>Результаты поиска по симптому "<%= symptomName %>":</h3>
        <% if (diseases.isEmpty()) { %>
            <p>Болезни не найдены</p>
        <% } else { %>
            <table border="1">
                <tr>
                    <th>Название</th>
                    <th>Описание</th>
                </tr>
                <% for (Disease disease : diseases) { %>
                <tr>
                    <td><%= disease.getName() %></td>
                    <td><%= disease.getDescription() %></td>
                </tr>
                <% } %>
            </table>
        <% } %>
    <% } %>

    <hr>

    <h2>Поиск лекарств по болезни</h2>
    <form method="get" action="search">
        <input type="hidden" name="type" value="medicinesByDisease">
        <label>Название болезни:</label><br>
        <input type="text" name="diseaseName" required><br><br>
        <input type="submit" value="Найти лекарства">
    </form>

    <%
        List<Medicine> medicines = (List<Medicine>) request.getAttribute("medicines");
        String diseaseName = (String) request.getAttribute("diseaseName");
        if (medicines != null) {
    %>
        <h3>Результаты поиска по болезни "<%= diseaseName %>":</h3>
        <% if (medicines.isEmpty()) { %>
            <p>Лекарства не найдены</p>
        <% } else { %>
            <table border="1">
                <tr>
                    <th>Название</th>
                    <th>Срок годности</th>
                    <th>Болезнь</th>
                </tr>
                <% for (Medicine medicine : medicines) { %>
                <tr>
                    <td><%= medicine.getName() %></td>
                    <td><%= medicine.getExpirationDate() %></td>
                    <td><%= medicine.getDiseaseName() %></td>
                </tr>
                <% } %>
            </table>
        <% } %>
    <% } %>

    <br>
    <a href="index.jsp">На главную</a>
</body>
</html>
