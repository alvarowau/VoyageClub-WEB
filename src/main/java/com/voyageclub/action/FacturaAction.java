package com.voyageclub.action;

import com.voyageclub.model.Factura;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz que define las operaciones relacionadas con la entidad Factura.
 * Extiende la interfaz genérica {@code Action} utilizando la clase {@code Factura} como entidad
 * y {@code Long} como tipo de identificador.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface FacturaAction extends Action<Factura, Long> {

}
