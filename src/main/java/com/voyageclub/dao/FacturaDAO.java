package com.voyageclub.dao;

import com.voyageclub.model.Factura;

import java.util.Optional;

/**
 * Interfaz que extiende la interfaz genérica {@link DAO} para la entidad {@link Factura}.
 * Proporciona métodos adicionales específicos para operaciones relacionadas con las facturas.
 *
 * <p>Esta interfaz define métodos adicionales para el acceso a datos y manipulación de facturas
 * en la base de datos. En particular, incluye un método para recuperar una factura asociada a un
 * usuario específico.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface FacturaDAO extends DAO<Factura, Long> {

    /**
     * Recupera una factura asociada a un usuario específico.
     *
     * @param usuarioId Identificador único del usuario.
     * @return Factura asociada al usuario, o un {@link Optional#empty()} si no hay ninguna factura.
     */
    Optional<Factura> findByUsuario(Long usuarioId);
}
