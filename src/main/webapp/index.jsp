<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>
<h1>Index Page</h1>

<%-- Botón para inicializar datos al hacer clic --%>
<form action="${pageContext.request.contextPath}/app" method="get">
    <button type="submit">Inicializar Datos</button>
</form>

<%-- Enlace para ir a la página de configuración de hoteles --%>
<!-- Botón para invocar la acción FIND_ALL -->
<form action="app" method="post">
    <input type="hidden" name="ACTION" value="HOTEL.FIND_ALL">
    <button type="submit" class="btn btn-primary">Listar Hoteles</button>
</form>


</body>
</html>
