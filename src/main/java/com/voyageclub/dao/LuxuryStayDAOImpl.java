package com.voyageclub.dao;

import com.voyageclub.model.LuxuryStay;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link LuxuryStayDAO} que proporciona operaciones específicas
 * para la entidad {@link LuxuryStay}.
 *
 * Esta implementación utiliza el mecanismo de inyección de dependencias de CDI
 * con las anotaciones {@link Named}, {@link Default}, y {@link Dependent}.
 *
 * <p>Esta clase implementa las operaciones definidas en la interfaz {@link LuxuryStayDAO},
 * centrándose en el acceso a datos y manipulación de estancias de lujo en la base de datos.
 *
 * @author [Tu Nombre]
 * @version 1.0
 */
@Named
@Default
@Dependent
public class LuxuryStayDAOImpl extends BaseDAO<LuxuryStay, Long> implements LuxuryStayDAO {

    /**
     * Constructor que recibe la clase de la entidad como parámetro.
     */
    public LuxuryStayDAOImpl() {
        super(LuxuryStay.class);
    }

    /**
     * Obtiene todas las estancias de lujo almacenadas en la base de datos.
     *
     * @return Lista que contiene todas las estancias de lujo.
     */
    @Override
    public List<LuxuryStay> getAll() {
        TypedQuery<LuxuryStay> query = entityManager.createQuery("SELECT ls FROM LuxuryStay ls", LuxuryStay.class);
        return query.getResultList();
    }

    /**
     * Busca una estancia de lujo por su nombre.
     *
     * @param nombre El nombre de la estancia de lujo a buscar.
     * @return Un Optional que contiene la estancia de lujo si se encuentra, o vacío si no.
     */
    @Override
    public Optional<LuxuryStay> findByNombre(String nombre) {
        TypedQuery<LuxuryStay> query = entityManager.createQuery("SELECT ls FROM LuxuryStay ls WHERE ls.nombre = :nombre", LuxuryStay.class);
        query.setParameter("nombre", nombre);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Busca una estancia de lujo por su código.
     *
     * @param codigoLuxuryStay El código de la estancia de lujo a buscar.
     * @return Un Optional que contiene la estancia de lujo si se encuentra, o vacío si no.
     */
    @Override
    public Optional<LuxuryStay> findByCodigoLuxuryStay(String codigoLuxuryStay) {
        TypedQuery<LuxuryStay> query = entityManager.createQuery("SELECT ls FROM LuxuryStay ls WHERE ls.codigoLuxuryStay = :codigoLuxuryStay", LuxuryStay.class);
        query.setParameter("codigoLuxuryStay", codigoLuxuryStay);
        query.setMaxResults(1);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Obtiene todas las estancias de lujo ordenadas por precio de forma ascendente.
     *
     * @return Lista que contiene todas las estancias de lujo ordenadas por precio ascendente.
     */
    @Override
    public List<LuxuryStay> findByPrecioAscendente() {
        TypedQuery<LuxuryStay> query = entityManager.createQuery("SELECT ls FROM LuxuryStay ls WHERE ls.precioNoche IS NOT NULL ORDER BY ls.precioNoche ASC", LuxuryStay.class);
        return query.getResultList();
    }

    /**
     * Obtiene todas las estancias de lujo ordenadas por precio de forma descendente.
     *
     * @return Lista que contiene todas las estancias de lujo ordenadas por precio descendente.
     */
    @Override
    public List<LuxuryStay> findByPrecioDescendente() {
        TypedQuery<LuxuryStay> query = entityManager.createQuery("SELECT ls FROM LuxuryStay ls WHERE ls.precioNoche IS NOT NULL ORDER BY ls.precioNoche DESC", LuxuryStay.class);
        return query.getResultList();
    }

    // Puedes añadir implementaciones específicas para LuxuryStay si es necesario
}
