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
    <title>Crear/Modificar Hotel</title>
</head>
<body>

<div class="container mt-5">
    <!-- Título del formulario (Crear o Modificar Hotel) -->
    <h2 class="mb-4">
        <c:choose>
            <c:when test="${not empty hotel}">Modificar</c:when>
            <c:when test="${empty hotel}">Crear</c:when>
        </c:choose> Hotel
    </h2>

    <!-- Mensajes de error generales -->
    <c:if test="${not empty ERROR_GENERAL}">
        <div class="alert alert-danger">
                ${ERROR_GENERAL}
        </div>
    </c:if>

    <!-- Formulario -->
    <form action="app" method="post" enctype="multipart/form-data">
        <!-- Acción para crear o modificar un hotel -->
        <input type="hidden" name="ACTION" value="${not empty hotel ? 'HOTEL.MODIFICAR' : 'HOTEL.CREAR'}">

        <!-- Campo oculto para el ID del hotel (solo en caso de modificación) -->
        <c:choose>
            <c:when test="${not empty hotel}">
                <input type="hidden" name="HOTELID" value="${hotel.id}">
            </c:when>
        </c:choose>

        <!-- Campos del formulario -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="NOMBRE">Nombre:</label>
                <input type="text" class="form-control" id="NOMBRE" name="NOMBRE" value="<c:if test='${not empty hotel}'>${hotel.nombre}</c:if>" required>
                <c:if test="${not empty ERROR_NOMBRE}">
                    <div class="alert alert-danger mt-2">
                            ${ERROR_NOMBRE}
                    </div>
                </c:if>
            </div>

            <div class="form-group col-md-6">
                <label for="DIRECCION">Dirección:</label>
                <input type="text" class="form-control" id="DIRECCION" name="DIRECCION" value="<c:if test='${not empty hotel}'>${hotel.direccion}</c:if>" required>
                <!-- Agrega mensajes de error según sea necesario -->
            </div>
        </div>

        <!-- Selector de estrellas y correo electrónico -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="ESTRELLAS">Estrellas:</label>
                <select class="form-control" id="ESTRELLAS" name="ESTRELLAS" required>
                    <c:forEach var="i" begin="1" end="5">
                        <option value="${i}" <c:if test="${not empty hotel and hotel.estrellas eq i}">selected</c:if>>${i}</option>
                    </c:forEach>
                </select>
                <!-- Agrega mensajes de error según sea necesario -->
            </div>

            <div class="form-group col-md-6">
                <label for="EMAIL">Correo Electrónico:</label>
                <input type="email" class="form-control" id="EMAIL" name="EMAIL" value="<c:if test='${not empty hotel}'>${hotel.email}</c:if>" required>
                <!-- Agrega mensajes de error según sea necesario -->
            </div>
        </div>

        <!-- Otros campos del formulario... -->

        <!-- Campo de ejemplo: Web -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="WEB">Página Web:</label>
                <input type="text" class="form-control" id="WEB" name="WEB" value="<c:if test='${not empty hotel}'>${hotel.web}</c:if>">
                <!-- Agrega mensajes de error según sea necesario -->
            </div>
        </div>

        <!-- Campo de ejemplo: Descripción -->
        <div class="form-row">
            <div class="form-group col-md-12">
                <label for="DESCRIPCION">Descripción:</label>
                <textarea class="form-control" id="DESCRIPCION" name="DESCRIPCION"><c:if test='${not empty hotel}'>${hotel.descripcion}</c:if></textarea>
                <!-- Agrega mensajes de error según sea necesario -->
            </div>
        </div>

        <!-- Campo de ejemplo: Teléfono -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="TELEFONO">Teléfono:</label>
                <input type="text" class="form-control" id="TELEFONO" name="TELEFONO" value="<c:if test='${not empty hotel}'>${hotel.telefono}</c:if>">
                <!-- Agrega mensajes de error según sea necesario -->
            </div>
        </div>

        <!-- Campos ocultos para la imagen principal y secundaria actual -->
        <input type="hidden" name="IMAGENPRINCIPAL_ACTUAL" value="<c:if test='${not empty hotel and not empty hotel.imagenPrincipal}'>${hotel.imagenPrincipal}</c:if>">
        <input type="hidden" name="IMAGENSECUNDARIA_ACTUAL" value="<c:if test='${not empty hotel and not empty hotel.imagenSecundaria}'>${hotel.imagenSecundaria}</c:if>">

        <!-- Imagen Principal -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="IMAGENPRINCIPAL">Imagen Principal:</label>
                <input type="file" class="form-control-file" id="IMAGENPRINCIPAL" name="IMAGENPRINCIPAL" accept="image/*" onchange="previewImage('imagenPrincipal', 'previewImagenPrincipal')">

                <c:if test="${not empty hotel and not empty hotel.imagenPrincipal}">
                    <img id="previewImagenPrincipal" src="${pageContext.request.contextPath}/hotelImage?hotelId=${hotel.id}&type=principal" alt="Vista previa" style="max-width: 100%; height: auto; display: block; margin-top: 10px;">
                </c:if>
            </div>
        </div>

        <!-- Imagen Secundaria -->
        <div class="form-row">
            <div class="form-group col-md-6">
                <label for="IMAGENSECUNDARIA">Imagen Secundaria:</label>
                <input type="file" class="form-control-file" id="IMAGENSECUNDARIA" name="IMAGENSECUNDARIA" accept="image/*" onchange="previewImage('imagenSecundaria', 'previewImagenSecundaria')">

                <c:if test="${not empty hotel and not empty hotel.imagenSecundaria}">
                    <img id="previewImagenSecundaria" src="${pageContext.request.contextPath}/hotelImage?hotelId=${hotel.id}&type=secundaria" alt="Vista previa" style="max-width: 100%; height: auto; display: block; margin-top: 10px;">
                </c:if>
            </div>
        </div>

        <!-- Botón para enviar el formulario -->
        <button type="submit" class="btn btn-primary" value="${empty hotel ? 'HOTEL.CREAR' : 'HOTEL.MODIFICAR'}">
            ${empty hotel ? 'Crear' : 'Modificar'} Hotel
        </button>
    </form>
</div>

<!-- Script para previsualizar las imágenes -->
<script>
    function previewImage(inputId, previewId) {
        const input = document.getElementById(inputId);
        const preview = document.getElementById(previewId);

        input.addEventListener('change', function () {
            if (input.files && input.files[0]) {
                const reader = new FileReader();

                reader.onload = function (e) {
                    preview.src = e.target.result;
                    preview.style.display = 'block';
                };

                reader.readAsDataURL(input.files[0]);
            } else {
                preview.src = '#';
                preview.style.display = 'none';
            }
        });
    }
</script>

</body>
</html>
