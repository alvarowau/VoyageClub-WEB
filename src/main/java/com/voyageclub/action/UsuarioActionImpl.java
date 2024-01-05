package com.voyageclub.action;

import com.voyageclub.beans.UsuarioSesionBeans;
import com.voyageclub.dao.ReservaDAO;
import com.voyageclub.dao.UsuarioDAO;
import com.voyageclub.model.Reserva;
import com.voyageclub.model.Usuario;
import com.voyageclub.util.UsuarioUtil;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link UsuarioAction} que proporciona operaciones relacionadas con los usuarios.
 *
 * <p>Este componente utiliza inyección de dependencias para obtener instancias de {@link UsuarioDAO} y {@link ReservaDAO}.
 *
 * <p>La anotación {@link ApplicationScoped} indica que este bean de CDI tiene un ciclo de vida asociado al contexto de la aplicación.
 *
 * @author [Tu nombre]
 * @version 1.0
 */
@ApplicationScoped
public class UsuarioActionImpl implements UsuarioAction {

    @Inject
    private UsuarioDAO usuarioDAO;

    @Inject
    private ReservaDAO reservaDAO;

    /**
     * Ejecuta la acción correspondiente según el parámetro "ACTION" proporcionado en la solicitud.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @return La página de destino a la que se redirigirá después de ejecutar la acción.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagDestino ="";
        String action = request.getParameter("ACTION");
        //Objeto "USUARIO.LISTAR"
        String[] arrayAction = action.split("\\.");
        switch(arrayAction[1]){
            case "FIND_ALL":
                pagDestino = findAll(request, response);
                break;
            case "REGISTER":
                pagDestino = register(request, response);
                break;
            case "LOGIN":
                pagDestino = login(request, response);
                break;
        }
        return pagDestino;
    }

    /**
     * Recupera todos los usuarios de la base de datos y los almacena en la solicitud para su visualización.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @return La página de destino que mostrará la lista de usuarios.
     */
    private String findAll(HttpServletRequest request,
                           HttpServletResponse response){
        // 1º) Leer variables que vengan desde la pantalla
        // 2º) Gestionar DAO--> Data Access Object - ORM

        List<Usuario> lstUsuarios = usuarioDAO.getAll();
        // 3º) Devolver variables a la pantalla
        request.setAttribute("LISTA_USUARIOS",
                lstUsuarios);
        // 4º) Definir página de retorno
        return "/index.jsp";
    }

    /**
     * Maneja la acción de inicio de sesión de un usuario.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @return La página de destino después de intentar iniciar sesión.
     */
    private String login(HttpServletRequest request, HttpServletResponse response) {
        // Obtener los parámetros de la solicitud (correo electrónico y contraseña)
        String correoElectronico = request.getParameter("EMAIL");
        String contrasena = request.getParameter("PASS");

        try {
            // Autenticar al usuario y obtener un Optional<Usuario>
            Optional<Usuario> usuarioOptional = usuarioDAO.autenticar(correoElectronico, contrasena);

            if (usuarioOptional.isPresent()) {
                // Usuario autenticado
                Usuario userLogin = usuarioOptional.get();

                // Obtener reservas del usuario
                List<Reserva> reservasUserBeans = reservaDAO.findByUsuario(userLogin.getId());

                // Crear una instancia de UsuarioSesionBeans y transferir la información
                UsuarioSesionBeans usuarioSesionBeans = new UsuarioSesionBeans(
                        userLogin.getId(), userLogin.getNombre(), userLogin.getApellido1(),
                        userLogin.getApellido2(), userLogin.getCorreoElectronico(),
                        userLogin.getDni(), userLogin.getCodigoUsuario(), reservasUserBeans
                );

                // Almacenar el UsuarioSesionBeans en la sesión
                request.getSession().setAttribute("usuarioSesion", usuarioSesionBeans);

                // Devolver la ruta de la página de destino (importante, ya que la redirección ya ocurrió)
                return "/home.jsp";

            } else {
                // Usuario no autenticado, establecer mensaje de error
                request.setAttribute("USUARIO", null);
                request.setAttribute("MENSAJE_USUARIO", "Inicio de sesión incorrecto. Inténtelo de nuevo.");

                // Redirigir de nuevo a la página de inicio de sesión (por ejemplo, login.jsp)
                return "/login.jsp";
            }
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada (por ejemplo, loggearla)
            e.printStackTrace();
            System.out.println(e.toString());

            // Redirigir a una página de error
            return "error.jsp";
        }
    }

    /**
     * Maneja la acción de registro de un nuevo usuario.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @return La página de destino después de intentar registrar un nuevo usuario.
     */
    private String register(HttpServletRequest request, HttpServletResponse response) {
        String nombre = request.getParameter("NOMBRE");
        String apellido1 = request.getParameter("APELLIDO1");
        String apellido2 = request.getParameter("APELLIDO2");
        String dni = request.getParameter("DNI");
        String email = request.getParameter("EMAIL");
        String pass = request.getParameter("PASS");
        String passRepetida = request.getParameter("PASS_REPEAT");

        // Validar que las contraseñas sean iguales
        if (pass.equals(passRepetida)) {
            // Verificar si el correo electrónico ya está en uso
            if (!usuarioDAO.existeMail(email)) {
                // Validar el formato del DNI
                if (UsuarioUtil.validarDNI(dni)) {
                    // Crear y guardar el nuevo usuario
                    Usuario usuarioRegistro = new Usuario(nombre, apellido1, apellido2, email, dni, pass);
                    usuarioDAO.save(usuarioRegistro);
                    // Limpiar atributos del usuario en la sesión
                    request.setAttribute("USUARIO", null);
                    // Mensaje de registro exitoso
                    request.setAttribute("MENSAJE_USUARIO", "¡Se registró correctamente! Inicie sesión.");
                    return "login.jsp";
                } else {
                    // Mensaje de error para el formato incorrecto del DNI
                    request.setAttribute("MENSAJE_USUARIO", "El DNI no tiene el formato esperado.");
                    return "registro.jsp";
                }
            } else {
                // Mensaje de error para correo electrónico ya en uso
                request.setAttribute("MENSAJE_USUARIO", "El correo electrónico ya está en uso.");
                return "login.jsp";
            }
        } else {
            // Mensaje de error para contraseñas diferentes
            request.setAttribute("MENSAJE_USUARIO", "Las contraseñas no son iguales, inténtelo de nuevo.");
            return "registro.jsp";
        }
    }
}
