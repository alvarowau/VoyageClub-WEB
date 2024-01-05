<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="css/hotelconfiguracion.css">
    <title>Configuración de Hoteles</title>
</head>
<body>

<div class="container mt-5">
    <h2>Configuración de Hoteles</h2>

    <!-- Nuevo Hotel -->
    <form action="app" method="POST">
        <input type="hidden" name="ACTION" value="HOTEL.DIRIGENEW">
        <button type="submit" class="btn btn-primary mb-3">Nuevo Hotel</button>
    </form>

    <table class="table table-bordered">
        <thead>
        <tr>
            <th>Nombre</th>
            <th>Dirección</th>
            <th>Estrellas</th>
            <th>Email</th>
            <th>Web</th>
            <th>Descripción</th>
            <th>Teléfono</th>
            <th>Código Hotel</th>
            <th>Imagen Principal</th>
            <th>Imagen Secundaria</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <!-- Utiliza la directiva c:forEach para iterar sobre la lista de hoteles -->
        <c:forEach var="hotel" items="${LISTA_HOTELES}">
            <tr>
                <td>${hotel.nombre}</td>
                <td>${hotel.direccion}</td>
                <td>${hotel.estrellas}</td>
                <td>${hotel.email}</td>
                <td>${hotel.web}</td>
                <td>${hotel.descripcion}</td>
                <td>${hotel.telefono}</td>
                <td>${hotel.codigoHotel}</td>
                <td>
                    <img src="/hotelImage?hotelId=${hotel.id}&type=principal" alt="Imagen Principal" style="max-width: 100px; max-height: 100px;">
                </td>
                <td>
                    <img src="/HotelImageServlet?hotelId=${hotel.id}&type=secundaria" alt="Imagen Secundaria" style="max-width: 100px; max-height: 100px;">
                </td>
                <td class="actions">
                    <form action="app" method="POST" style="display:inline;">
                        <input type="hidden" name="ACTION" value="HOTEL.DIRIGEMODIFICAR">
                        <input type="hidden" name="HOTELID" value="${hotel.id}">
                        <button type="submit" class="btn btn-warning">Modificar</button>
                    </form>
                    <form action="app" method="POST" style="display:inline;">
                        <input type="hidden" name="ACTION" value="HOTEL.ELIMINAR">
                        <input type="hidden" name="HOTELID" value="${hotel.id}">
                        <button type="submit" class="btn btn-danger">Eliminar</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
