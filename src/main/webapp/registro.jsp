<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login y Registro con HTML5 y CSS3</title>
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Lato&display=swap" rel="stylesheet">
    <!-- Estilos CSS -->
    <link rel="stylesheet" href="css/style.css">
    <!-- Favicon -->
    <link rel="shortcut icon" href="img/favicon.png" type="image/x-icon">
</head>
<body>
<main>
    <article>
        <section>
            <form action="app" method="post" >
                <h1>Regístrate</h1>
                <c:if test="${not empty requestScope.MENSAJE_USUARIO}">
                    <p class="error-message">${requestScope.MENSAJE_USUARIO}</p>
                </c:if>
                <input type="hidden" name="ACTION" value="USUARIO.REGISTER">
                <input type="text" name="NOMBRE" placeholder="Nombre" required><br/>
                <input type="text" name="APELLIDO1" placeholder="Primer Apellido" required><br/>
                <input type="text" name="APELLIDO2" placeholder="Segundo Apellido" required><br/>
                <input type="text" name="DNI" placeholder="DNI" required><br/>
                <input type="email" name="EMAIL" placeholder="Correo electrónico" required><br/>
                <input type="password" name="PASS" placeholder="Contraseña" required><br/>
                <input type="password" name="PASS_REPEAT" placeholder="Repite la Contraseña" required><br/>
                <button type="submit">Registrarse</button>
                <button type="reset">Limpiar</button>

                <p>¿Ya tienes cuenta?</p>
                <p>
                    <a href="login.jsp">Inicia Sesión</a>
                </p>
            </form>
        </section>
    </article>
</main>
</body>
</html>
