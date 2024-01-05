package com.voyageclub.dao;

import com.voyageclub.model.Reserva;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link ReservaDAO} que proporciona operaciones específicas
 * para la entidad {@link Reserva}.
 *
 * Esta implementación utiliza el mecanismo de inyección de dependencias de CDI
 * con las anotaciones {@link Named}, {@link Default}, y {@link Dependent}.
 *
 * <p>Esta clase proporciona operaciones de acceso a datos para la entidad {@link Reserva},
 * incluyendo consultas personalizadas para recuperar reservas basadas en diferentes criterios.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Named
@Default
@Dependent
public class ReservaDAOImpl extends BaseDAO<Reserva, Long> implements ReservaDAO {

    /**
     * Constructor que recibe la clase de la entidad como parámetro.
     */
    public ReservaDAOImpl() {
        super(Reserva.class);
    }

    /**
     * Obtiene todas las reservas almacenadas en la base de datos.
     *
     * @return Lista que contiene todas las reservas.
     */
    @Override
    public List<Reserva> getAll() {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r", Reserva.class);
        return query.getResultList();
    }

    /**
     * Recupera una lista de reservas asociadas a un usuario específico.
     *
     * @param usuarioId Identificador único del usuario.
     * @return Lista de reservas asociadas al usuario, o una lista vacía si no hay ninguna reserva.
     */
    @Override
    public List<Reserva> findByUsuario(Long usuarioId) {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.usuario.id = :usuarioId", Reserva.class);
        query.setParameter("usuarioId", usuarioId);
        return query.getResultList();
    }

    /**
     * Recupera una lista de reservas asociadas a una estancia de lujo específica.
     *
     * @param luxuryStayId Identificador único de la estancia de lujo.
     * @return Lista de reservas asociadas a la estancia de lujo, o una lista vacía si no hay ninguna reserva.
     */
    @Override
    public List<Reserva> findByLuxuryStay(Long luxuryStayId) {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.luxuryStay.id = :luxuryStayId", Reserva.class);
        query.setParameter("luxuryStayId", luxuryStayId);
        return query.getResultList();
    }

    /**
     * Recupera una reserva asociada a un usuario y una estancia de lujo específicos.
     *
     * @param usuarioId    Identificador único del usuario.
     * @param luxuryStayId Identificador único de la estancia de lujo.
     * @return Un objeto {@link Optional} que contiene la reserva si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    @Override
    public Optional<Reserva> findByUsuarioAndLuxuryStay(Long usuarioId, Long luxuryStayId) {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.usuario.id = :usuarioId AND r.luxuryStay.id = :luxuryStayId", Reserva.class);
        query.setParameter("usuarioId", usuarioId);
        query.setParameter("luxuryStayId", luxuryStayId);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Recupera una lista de reservas asociadas a un estado específico.
     *
     * @param estado Estado de las reservas a buscar.
     * @return Lista de reservas asociadas al estado, o una lista vacía si no hay ninguna reserva.
     */
    @Override
    public List<Reserva> findByEstado(String estado) {
        TypedQuery<Reserva> query = entityManager.createQuery("SELECT r FROM Reserva r WHERE r.estado = :estado", Reserva.class);
        query.setParameter("estado", estado);
        return query.getResultList();
    }
}
