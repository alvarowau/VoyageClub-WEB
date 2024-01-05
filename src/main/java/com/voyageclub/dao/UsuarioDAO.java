package com.voyageclub.dao;

import com.voyageclub.model.Usuario;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que extiende la interfaz genérica {@link DAO} para la entidad {@link Usuario}.
 * Proporciona métodos adicionales específicos para operaciones relacionadas con usuarios.
 *
 * <p>Implementaciones de esta interfaz deben proporcionar operaciones específicas de acceso a datos
 * para la entidad {@link Usuario} y pueden contener consultas personalizadas.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface UsuarioDAO extends DAO<Usuario, Long> {

    /**
     * Recupera una lista de usuarios que coinciden con el nombre de usuario proporcionado.
     *
     * @param nombre Nombre de usuario a buscar.
     * @return Lista de usuarios que coinciden con el nombre de usuario, o una lista vacía si no hay coincidencias.
     */
    Optional<Usuario> findByUsername(String nombre);

    /**
     * Recupera un usuario mediante su dirección de correo electrónico.
     *
     * @param correoElectronico Dirección de correo electrónico del usuario a buscar.
     * @return Un objeto {@link Optional} que contiene el usuario si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    Optional<Usuario> findByCorreoElectronico(String correoElectronico);

    /**
     * Recupera un usuario mediante su número de documento de identidad (DNI).
     *
     * @param dni Número de DNI del usuario a buscar.
     * @return Un objeto {@link Optional} que contiene el usuario si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    Optional<Usuario> findByDni(String dni);

    /**
     * Autentica a un usuario mediante su dirección de correo electrónico y contraseña.
     *
     * @param correoElectronico Dirección de correo electrónico del usuario.
     * @param contrasena        Contraseña del usuario.
     * @return Un objeto {@link Optional} que contiene el usuario autenticado si las credenciales son válidas, o un objeto {@code Optional} vacío si no son válidas.
     */
    Optional<Usuario> autenticar(String correoElectronico, String contrasena);

    /**
     * Verifica la existencia de un correo electrónico en la base de datos.
     *
     * @param mail Correo electrónico a verificar.
     * @return {@code true} si existe un usuario con el correo electrónico proporcionado, {@code false} en caso contrario.
     */
    boolean existeMail(String mail);

    /**
     * Obtiene todas los usuarios almacenados en la base de datos.
     *
     * @return Lista que contiene todos los usuarios.
     */
    List<Usuario> getAll();
}
