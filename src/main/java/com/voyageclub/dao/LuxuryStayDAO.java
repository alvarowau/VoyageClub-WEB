package com.voyageclub.dao;

import com.voyageclub.model.LuxuryStay;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que extiende la interfaz genérica {@link DAO} para la entidad {@link LuxuryStay}.
 * Proporciona métodos adicionales específicos para operaciones relacionadas con estancias de lujo.
 *
 * <p>Esta interfaz define métodos adicionales para el acceso a datos y manipulación de estancias de lujo
 * en la base de datos. Estos métodos se centran en buscar estancias de lujo por nombre y código, así como
 * obtener listas de estancias de lujo ordenadas por precio de forma ascendente y descendente.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface LuxuryStayDAO extends DAO<LuxuryStay, Long> {

    /**
     * Busca una estancia de lujo por su nombre.
     *
     * @param nombre El nombre de la estancia de lujo a buscar.
     * @return Un Optional que contiene la estancia de lujo si se encuentra, o vacío si no.
     */
    Optional<LuxuryStay> findByNombre(String nombre);

    /**
     * Busca una estancia de lujo por su código.
     *
     * @param codigoLuxuryStay El código de la estancia de lujo a buscar.
     * @return Un Optional que contiene la estancia de lujo si se encuentra, o vacío si no.
     */
    Optional<LuxuryStay> findByCodigoLuxuryStay(String codigoLuxuryStay);

    /**
     * Obtiene todas las estancias de lujo ordenadas por precio de forma ascendente.
     *
     * @return Lista que contiene todas las estancias de lujo ordenadas por precio ascendente.
     */
    List<LuxuryStay> findByPrecioAscendente();

    /**
     * Obtiene todas las estancias de lujo ordenadas por precio de forma descendente.
     *
     * @return Lista que contiene todas las estancias de lujo ordenadas por precio descendente.
     */
    List<LuxuryStay> findByPrecioDescendente();
}
