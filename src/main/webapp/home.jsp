<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agencia de Viajes - Página de inicio</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css">
    <!-- jQuery y jQuery UI -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
    <script type="text/javascript" src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <link rel="stylesheet" type="text/css" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- JavaScript personalizado -->
    <script type="text/javascript" src="js/account-dropdown.js"></script>

</head>
<body>
<header>
    <!-- Tu código HTML para la barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container"> <!-- Contenedor para centrar los elementos -->
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="hotel.jsp">Hoteles</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="pedidos.jsp">Pedidos</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="contacto.jsp">Contacto</a>
                    </li>
                </ul>
            </div>
            <!-- Controlador de Cuenta con Menú Desplegable -->
            <div class="account-dropdown" id="accountDropdown">
                <img src="img/usuario.png" alt="cuenta" width="30">
                <div class="account-dropdown-content">
                    <a href="#">Perfil</a>
                    <a href="#">Configuración</a>
                    <a href="#">Cerrar Sesión</a>
                </div>
            </div>
        </div>
    </nav>
</header>

<!-- Sección de bienvenida y búsqueda con Bootstrap -->
<section class="container mt-4">
    <div class="jumbotron">
        <h1 class="display-4">¡Bienvenido a nuestra Agencia de Viajes!</h1>
        <p class="lead">Encuentra tu próximo destino y vive la mejor experiencia de viaje.</p>
    </div>

    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="destination-addon">
                        <i class="fas fa-map-marker-alt"></i>
                    </span>
                </div>
                <input type="text" class="form-control" placeholder="¿A dónde te gustaría ir?" aria-label="Destination" aria-describedby="destination-addon">
            </div>
        </div>
        <div class="col-md-3">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="start-date-addon">
                        <i class="far fa-calendar-alt"></i>
                    </span>
                </div>
                <input type="date" class="form-control" placeholder="Fecha de inicio" aria-label="Start date" aria-describedby="start-date-addon">
            </div>
        </div>
        <div class="col-md-3">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <span class="input-group-text" id="end-date-addon">
                        <i class="far fa-calendar-alt"></i>
                    </span>
                </div>
                <input type="date" class="form-control" placeholder="Fecha de fin" aria-label="End date" aria-describedby="end-date-addon">
            </div>
        </div>
        <div class="col-md-6">
            <button class="btn btn-outline-primary btn-lg btn-block" onclick="filterTrips()">Filtrar</button>
        </div>
        <div class="col-md-6">
            <button class="btn btn-outline-success btn-lg btn-block" onclick="accept()">Aceptar</button>
        </div>
    </div>
</section>

<!-- Lista de viajes como tarjetas fuera de la sección -->
<div class="trip-cards container mt-4">
    <div class="row">
        <!-- Tarjeta 1 -->
        <div class="col-md-4 mb-4">
            <div class="card text-center">
                <img src="ruta/a/tu/foto-viaje1.jpg" class="card-img-top mx-auto" alt="Viaje 1" style="width: 50%;">
                <div class="card-body">
                    <h5 class="card-title">Nombre del Hotel</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Nombre del Viaje</h6>
                    <p class="card-text">Descripción del Viaje. Puedes agregar más detalles aquí.</p>
                    <a href="#" class="btn btn-primary">Ver detalles</a>
                    <a href="#" class="btn btn-success">Reservar</a>
                </div>
            </div>
        </div>

        <!-- Tarjeta 2 -->
        <div class="col-md-4 mb-4">
            <div class="card text-center">
                <img src="ruta/a/tu/foto-viaje2.jpg" class="card-img-top mx-auto" alt="Viaje 2" style="width: 50%;">
                <div class="card-body">
                    <h5 class="card-title">Nombre del Hotel</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Nombre del Viaje</h6>
                    <p class="card-text">Descripción del Viaje. Puedes agregar más detalles aquí.</p>
                    <a href="#" class="btn btn-primary">Ver detalles</a>
                    <a href="#" class="btn btn-success">Reservar</a>
                </div>
            </div>
        </div>

        <!-- Tarjeta 3 -->
        <div class="col-md-4 mb-4">
            <div class="card text-center">
                <img src="ruta/a/tu/foto-viaje3.jpg" class="card-img-top mx-auto" alt="Viaje 3" style="width: 50%;">
                <div class="card-body">
                    <h5 class="card-title">Nombre del Hotel</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Nombre del Viaje</h6>
                    <p class="card-text">Descripción del Viaje. Puedes agregar más detalles aquí.</p>
                    <a href="#" class="btn btn-primary">Ver detalles</a>
                    <a href="#" class="btn btn-success">Reservar</a>
                </div>
            </div>
        </div>

        <!-- Tarjeta 4 -->
        <div class="col-md-4 mb-4">
            <div class="card text-center">
                <img src="ruta/a/tu/foto-viaje4.jpg" class="card-img-top mx-auto" alt="Viaje 4" style="width: 50%;">
                <div class="card-body">
                    <h5 class="card-title">Nombre del Hotel</h5>
                    <h6 class="card-subtitle mb-2 text-muted">Nombre del Viaje</h6>
                    <p class="card-text">Descripción del Viaje. Puedes agregar más detalles aquí.</p>
                    <a href="#" class="btn btn-primary">Ver detalles</a>
                    <a href="#" class="btn btn-success">Reservar</a>
                </div>
            </div>
        </div>
        <!-- Agrega más tarjetas según sea necesario -->
    </div>
</div>

<footer class="mt-4">
    <!-- Puedes agregar información de contacto u otros detalles de pie de página aquí -->
    <p>Contacto: info@agenciadeviajes.com</p>
</footer>

<!-- Bootstrap JavaScript y Popper.js -->
<script src="https://code.jquery.com/jquery-3.6.4.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
