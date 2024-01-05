package com.voyageclub.model;

import com.voyageclub.model.enumerdos.EstadoReserva;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * La clase Reserva representa una reserva en el sistema.
 * Contiene información detallada sobre la reserva y su estado.
 *
 * Esta clase extiende la clase BaseEntity, que proporciona un identificador único para todas las entidades.
 * Una reserva está asociada a un usuario y a una estancia de lujo (LuxuryStay).
 * Puede tener una factura asociada y se encuentra en un estado específico (pendiente, confirmada, cancelada, etc.).
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Entity
public class Reserva extends BaseEntity {

    /**
     * Usuario que realiza la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    /**
     * Estancia de lujo asociada a la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "luxury_stay_id", nullable = false)
    private LuxuryStay luxuryStay;

    /**
     * Factura asociada a la reserva.
     */
    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    /**
     * Fecha de inicio de la reserva.
     */
    @Column(nullable = false)
    private LocalDate fechaInicio;

    /**
     * Fecha de fin de la reserva.
     */
    @Column(nullable = false)
    private LocalDate fechaFin;

    /**
     * Cantidad de personas para la reserva.
     */
    @Column(nullable = false)
    private int cantidadPersonas;

    /**
     * Estado de la reserva (pendiente, confirmada, cancelada, etc.).
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoReserva estado;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Reserva() {
    }

    /**
     * Constructor que inicializa los atributos básicos de la reserva.
     *
     * @param usuario          Usuario que realiza la reserva.
     * @param luxuryStay       Estancia de lujo asociada a la reserva.
     * @param fechaInicio      Fecha de inicio de la reserva.
     * @param fechaFin         Fecha de fin de la reserva.
     * @param cantidadPersonas Cantidad de personas para la reserva.
     */
    public Reserva(Usuario usuario, LuxuryStay luxuryStay, LocalDate fechaInicio, LocalDate fechaFin, int cantidadPersonas) {
        this.usuario = usuario;
        this.luxuryStay = luxuryStay;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.cantidadPersonas = cantidadPersonas;
        this.estado = EstadoReserva.PENDIENTE; //Por defecto confirmada
    }

    /**
     * Obtiene el usuario que realizó la reserva.
     *
     * @return El usuario de la reserva.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realiza la reserva.
     *
     * @param usuario El nuevo usuario de la reserva.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la estancia de lujo asociada a la reserva.
     *
     * @return La estancia de lujo de la reserva.
     */
    public LuxuryStay getLuxuryStay() {
        return luxuryStay;
    }

    /**
     * Establece la estancia de lujo asociada a la reserva.
     *
     * @param luxuryStay La nueva estancia de lujo de la reserva.
     */
    public void setLuxuryStay(LuxuryStay luxuryStay) {
        this.luxuryStay = luxuryStay;
    }

    /**
     * Obtiene la factura asociada a la reserva.
     *
     * @return La factura de la reserva.
     */
    public Factura getFactura() {
        return factura;
    }

    /**
     * Establece la factura asociada a la reserva.
     *
     * @param factura La nueva factura de la reserva.
     */
    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    /**
     * Obtiene la fecha de inicio de la reserva.
     *
     * @return La fecha de inicio de la reserva.
     */
    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Establece la fecha de inicio de la reserva.
     *
     * @param fechaInicio La nueva fecha de inicio de la reserva.
     */
    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    /**
     * Obtiene la fecha de fin de la reserva.
     *
     * @return La fecha de fin de la reserva.
     */
    public LocalDate getFechaFin() {
        return fechaFin;
    }

    /**
     * Establece la fecha de fin de la reserva.
     *
     * @param fechaFin La nueva fecha de fin de la reserva.
     */
    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    /**
     * Obtiene la cantidad de personas para la reserva.
     *
     * @return La cantidad de personas para la reserva.
     */
    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    /**
     * Establece la cantidad de personas para la reserva.
     *
     * @param cantidadPersonas La nueva cantidad de personas para la reserva.
     */
    public void setCantidadPersonas(int cantidadPersonas) {
        this.cantidadPersonas = cantidadPersonas;
    }

    /**
     * Obtiene el estado de la reserva.
     *
     * @return El estado de la reserva.
     */
    public EstadoReserva getEstado() {
        return estado;
    }

    /**
     * Establece el estado de la reserva.
     *
     * @param estado El nuevo estado de la reserva.
     */
    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    /**
     * Devuelve una representación de cadena de esta reserva.
     *
     * @return Una cadena que representa esta reserva.
     */
    @Override
    public String toString() {
        return "Reserva{" +
                "usuario=" + usuario +
                ", luxuryStay=" + luxuryStay +
                ", factura=" + factura +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", cantidadPersonas=" + cantidadPersonas +
                ", estado=" + estado +
                '}';
    }
}
