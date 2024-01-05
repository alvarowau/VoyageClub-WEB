package com.voyageclub.model.enumerdos;

/**
 * Enumeración que representa los posibles estados de una reserva.
 * Cada estado tiene una etiqueta asociada que puede ser utilizada para mostrar información legible.
 *
 * Los estados posibles son: PENDIENTE, CONFIRMADA y CANCELADA.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public enum EstadoReserva {

    /**
     * Estado de reserva: Pendiente.
     */
    PENDIENTE("Pendiente"),

    /**
     * Estado de reserva: Confirmada.
     */
    CONFIRMADA("Confirmada"),

    /**
     * Estado de reserva: Cancelada.
     */
    CANCELADA("Cancelada");

    private final String etiqueta;

    /**
     * Constructor privado que asigna una etiqueta a cada estado.
     *
     * @param etiqueta Etiqueta asociada al estado.
     */
    EstadoReserva(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * Obtiene la etiqueta asociada al estado.
     *
     * @return Etiqueta del estado.
     */
    public String getEtiqueta() {
        return etiqueta;
    }
}
