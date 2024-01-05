package com.voyageclub.dao;

import com.voyageclub.model.Usuario;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;


/**
 * Implementación de la interfaz {@link UsuarioDAO} que proporciona operaciones específicas
 * para la entidad {@link Usuario}.
 *
 * Esta implementación utiliza el mecanismo de inyección de dependencias de CDI
 * con las anotaciones {@link Named}, {@link Default}, y {@link Dependent}.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Named
@Default
@Dependent
public class UsuarioDAOImpl extends BaseDAO<Usuario, Long> implements UsuarioDAO {

    /**
     * Constructor que recibe la clase de la entidad como parámetro.
     */
    public UsuarioDAOImpl() {
        super(Usuario.class);
    }

    /**
     * Obtiene todas los usuarios almacenados en la base de datos.
     *
     * @return Lista que contiene todos los usuarios.
     */
    @Override
    public List<Usuario> getAll() {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }

    /**
     * Recupera una lista de usuarios que coinciden con el nombre de usuario proporcionado.
     *
     * @param nombre Nombre de usuario a buscar.
     * @return Lista de usuarios que coinciden con el nombre de usuario, o una lista vacía si no hay coincidencias.
     */
    @Override
    public Optional<Usuario> findByUsername(String nombre) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.nombre = :nombre", Usuario.class);
        query.setParameter("nombre", nombre);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Recupera un usuario mediante su dirección de correo electrónico.
     *
     * @param correoElectronico Dirección de correo electrónico del usuario a buscar.
     * @return Un objeto {@link Optional} que contiene el usuario si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    @Override
    public Optional<Usuario> findByCorreoElectronico(String correoElectronico) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correo", Usuario.class);
        query.setParameter("correo", correoElectronico);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Recupera un usuario mediante su número de documento de identidad (DNI).
     *
     * @param dni Número de DNI del usuario a buscar.
     * @return Un objeto {@link Optional} que contiene el usuario si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    @Override
    public Optional<Usuario> findByDni(String dni) {
        TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.dni = :dni", Usuario.class);
        query.setParameter("dni", dni);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Autentica a un usuario utilizando su correo electrónico y contraseña.
     *
     * @param correoElectronico Correo electrónico del usuario.
     * @param contrasena        Contraseña del usuario.
     * @return Un Optional que contiene el usuario autenticado, o un Optional vacío si la autenticación falla.
     */
    @Override
    public Optional<Usuario> autenticar(String correoElectronico, String contrasena) {
        try {
            // Crear una consulta JPA para seleccionar un usuario por correo electrónico
            TypedQuery<Usuario> query = entityManager.createQuery("SELECT u FROM Usuario u WHERE u.correoElectronico = :correo", Usuario.class);
            query.setParameter("correo", correoElectronico);
            query.setMaxResults(1);

            // Intentar obtener un usuario único que coincida con el correo electrónico
            Usuario usuario = query.getSingleResult();

            // Verificar las credenciales del usuario
            if (usuario.verificarContrasena(contrasena)) {
                // Si las credenciales son válidas, devolver un Optional que contiene al usuario autenticado
                return Optional.of(usuario);
            } else {
                // Si las credenciales no son válidas, devolver un Optional vacío
                return Optional.empty();
            }
        } catch (NoResultException e) {
            // Si no se encuentra ningún usuario con el correo electrónico proporcionado, devolver un Optional vacío
            return Optional.empty();
        }
    }

    /**
     * Verifica si existe algún usuario registrado con el correo electrónico proporcionado.
     *
     * @param mail Dirección de correo electrónico a verificar.
     * @return true si existe algún usuario con el correo electrónico proporcionado, false de lo contrario.
     */
    @Override
    public boolean existeMail(String mail) {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.correoElectronico = :correo", Long.class);
        query.setParameter("correo", mail);

        // Obtener el resultado como un valor long
        long count = query.getSingleResult();

        // Si count es mayor que cero, significa que hay al menos un usuario con ese correo electrónico
        return count > 0;
    }
}
