package com.voyageclub.dao;

import com.voyageclub.model.Reserva;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que extiende la interfaz genérica {@link DAO} para la entidad {@link Reserva}.
 * Proporciona métodos adicionales específicos para operaciones relacionadas con reservas.
 *
 * <p>Esta interfaz define operaciones de acceso a datos específicas para la entidad {@link Reserva},
 * centradas en recuperar y manipular reservas basadas en diferentes criterios.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface ReservaDAO extends DAO<Reserva, Long> {

    /**
     * Recupera una lista de reservas asociadas a un usuario específico.
     *
     * @param usuarioId Identificador único del usuario.
     * @return Lista de reservas asociadas al usuario, o una lista vacía si no hay ninguna reserva.
     */
    List<Reserva> findByUsuario(Long usuarioId);

    /**
     * Recupera una lista de reservas asociadas a una estancia de lujo específica.
     *
     * @param luxuryStayId Identificador único de la estancia de lujo.
     * @return Lista de reservas asociadas a la estancia de lujo, o una lista vacía si no hay ninguna reserva.
     */
    List<Reserva> findByLuxuryStay(Long luxuryStayId);

    /**
     * Recupera una reserva asociada a un usuario y una estancia de lujo específicos.
     *
     * @param usuarioId    Identificador único del usuario.
     * @param luxuryStayId Identificador único de la estancia de lujo.
     * @return Un objeto {@link Optional} que contiene la reserva si se encuentra, o un objeto {@code Optional} vacío si no hay coincidencias.
     */
    Optional<Reserva> findByUsuarioAndLuxuryStay(Long usuarioId, Long luxuryStayId);

    /**
     * Recupera una lista de reservas asociadas a un estado específico.
     *
     * @param estado Estado de las reservas a buscar.
     * @return Lista de reservas asociadas al estado, o una lista vacía si no hay ninguna reserva.
     */
    List<Reserva> findByEstado(String estado);
}
