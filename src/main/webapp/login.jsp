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
            <form action="app" method="post">
                <h1>Inicia Sesión</h1>
                <img src="icons-social/facebook.svg" title="Inicia Sesión con Facebook" alt="Inicia Sesión con Facebook">
                <img src="icons-social/google.svg" title="Inicia Sesión con Google" alt="Inicia Sesión con Google">
                <img src="icons-social/twitter.svg" title="Inicia Sesión con Twitter" alt="Inicia Sesión con Twitter">

                <c:if test="${not empty requestScope.MENSAJE_USUARIO}">
                    <p class="error-message">${requestScope.MENSAJE_USUARIO}</p>
                </c:if>

                <input type="hidden" name="ACTION" value="USUARIO.LOGIN">
                <input type="email" name="EMAIL" placeholder="Correo electrónico" required><br/>
                <input type="password" name="PASS" placeholder="Contraseña" required><br/>

                <!-- Botones centrados -->
                <button type="submit">Entrar</button>
                <button type="reset">Limpiar</button>

                <p>¿Aún no tienes cuenta?</p>
                <p>
                    <a href="registro.jsp">Regístrate</a>
                </p>
                <p>
                    <a href="#">¿Olvidó la contraseña?</a>
                </p>
            </form>
        </section>
    </article>
</main>
</body>

</html>
