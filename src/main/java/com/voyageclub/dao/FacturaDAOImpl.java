package com.voyageclub.dao;

import com.voyageclub.model.Factura;
import com.voyageclub.model.Reserva;
import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;

import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link FacturaDAO} que proporciona operaciones específicas
 * para la entidad {@link Factura}.
 *
 * Esta implementación utiliza el mecanismo de inyección de dependencias de CDI
 * con las anotaciones {@link Named}, {@link Default}, y {@link Dependent}.
 *
 * <p>Esta implementación de `FacturaDAO` incluye métodos para guardar facturas, obtener todas
 * las facturas almacenadas en la base de datos y recuperar una factura asociada a un usuario específico.
 * Además, al guardar una factura, también se actualizan las reservas asociadas a esa factura.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Named
@Default
@Dependent
public class FacturaDAOImpl extends BaseDAO<Factura, Long> implements FacturaDAO {

    /**
     * Constructor que inicializa la clase de la entidad.
     */
    public FacturaDAOImpl() {
        super(Factura.class);
    }

    /**
     * Guarda una factura y actualiza las reservas asociadas.
     *
     * @param factura La factura a guardar.
     */
    @Override
    public void save(Factura factura) {
        // Guardar la factura
        super.save(factura);

        // Obtener las reservas asociadas a la factura
        List<Reserva> reservas = factura.getReservas();

        // Verificar si hay reservas asociadas
        if (reservas != null && !reservas.isEmpty()) {
            // Iterar sobre las reservas y establecer el id de la factura
            for (Reserva reserva : reservas) {
                reserva.setFactura(factura);
                // Actualizar la reserva en la base de datos
                entityManager.merge(reserva);
            }
        }
    }

    /**
     * Obtiene todas las facturas almacenadas en la base de datos.
     *
     * @return Lista que contiene todas las facturas.
     */
    @Override
    public List<Factura> getAll() {
        TypedQuery<Factura> query = entityManager.createQuery("SELECT f FROM Factura f", Factura.class);
        return query.getResultList();
    }

    /**
     * Recupera una factura asociada a un usuario específico.
     *
     * @param idUsuario Identificador único del usuario.
     * @return Factura asociada al usuario, o un {@link Optional#empty()} si no hay ninguna factura.
     */
    @Override
    public Optional<Factura> findByUsuario(Long idUsuario) {
        TypedQuery<Factura> query = entityManager.createQuery(
                "SELECT f FROM Factura f JOIN FETCH f.reservas r WHERE r.usuario.id = :idUsuario",
                Factura.class
        );
        query.setParameter("idUsuario", idUsuario);
        return query.getResultList().stream().findFirst();
    }
}
