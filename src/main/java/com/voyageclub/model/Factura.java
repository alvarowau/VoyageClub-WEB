package com.voyageclub.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;

/**
 * La clase Factura representa una factura generada a partir de reservas en el sistema.
 * Contiene información detallada sobre las reservas asociadas, el precio total, el IVA, y otros detalles.
 *
 * Esta clase extiende la clase BaseEntity, que proporciona un identificador único para todas las entidades.
 * Una factura se genera a partir de una lista de reservas y calcula su precio total, el IVA, y otros detalles automáticamente.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Entity
public class Factura extends BaseEntity {

    /**
     * Lista de reservas asociadas a la factura.
     */
    @OneToMany(mappedBy = "factura")
    private List<Reserva> reservas;

    /**
     * Precio total de la factura.
     */
    @Column
    private BigDecimal precioTotal;

    /**
     * Fecha de emisión de la factura.
     */
    @Column(nullable = false)
    private LocalDate fechaEmision;

    /**
     * Precio total sin IVA.
     */
    @Transient
    private BigDecimal precioSinIva;

    /**
     * Precio total del IVA.
     */
    @Transient
    private BigDecimal precioDelIva;

    /**
     * Número único de la factura.
     */
    @Column
    private String numeroFactura;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Factura() {
        // Constructor vacío necesario para JPA
    }

    /**
     * Constructor que inicializa la factura a partir de una lista de reservas.
     *
     * @param reservas Lista de reservas asociadas a la factura.
     */
    public Factura(List<Reserva> reservas) {
        if (reservas == null || reservas.isEmpty()) {
            throw new IllegalArgumentException("La lista de reservas no puede ser nula o vacía.");
        }

        this.reservas = reservas;
        calcularFactura();
    }

    /**
     * Calcula automáticamente el precio total, el IVA y otros detalles de la factura.
     */
    private void calcularFactura() {
        this.fechaEmision = LocalDate.now();

        // Calcular precioTotal, precioSinIva, precioDelIva (el IVA es 21%)
        this.precioTotal = BigDecimal.ZERO;

        for (Reserva reserva : reservas) {
            // Calcular el precio por noche
            BigDecimal precioPorNoche = reserva.getLuxuryStay().getPrecioNoche();

            // Calcular el número de noches
            long numeroDeNoches = ChronoUnit.DAYS.between(reserva.getFechaInicio(), reserva.getFechaFin());

            // Calcular el precio total para esta reserva
            BigDecimal precioReserva = precioPorNoche.multiply(BigDecimal.valueOf(numeroDeNoches));

            // Agregar el precio de esta reserva al precio total
            this.precioTotal = this.precioTotal.add(precioReserva);
        }

        // Calcular precios con IVA
        BigDecimal porcentajeIva = BigDecimal.valueOf(0.21);
        this.precioDelIva = this.precioTotal.multiply(porcentajeIva).setScale(2, RoundingMode.HALF_UP);
        this.precioSinIva = this.precioTotal.subtract(this.precioDelIva).setScale(2, RoundingMode.HALF_UP);

        System.out.println("Precio total antes del IVA: " + this.precioTotal.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Precio del IVA: " + this.precioDelIva);
        System.out.println("Precio sin IVA: " + this.precioSinIva);

        // Generar un número de factura (puedes implementar tu lógica aquí)
        this.numeroFactura = generarNumeroFactura();
    }



    /**
     * Genera un número único para la factura.
     *
     * @return Número único de la factura.
     */
    private String generarNumeroFactura() {
        // Utilizar el formato "FACTURA + año + mes + dia + codigoUsuario + dosNumerosAlAzar"
        Random random = new Random();
        int primerNumero = random.nextInt(10);
        int segundoNumero = random.nextInt(10);

        return "FACTURA-" +
                LocalDate.now().getYear() +
                LocalDate.now().getMonthValue() +
                LocalDate.now().getDayOfMonth() +
                reservas.get(0).getUsuario().getCodigoUsuario() +
                String.format("%02d", primerNumero) +
                String.format("%02d", segundoNumero);
    }

    /**
     * Obtiene la lista de reservas asociadas a la factura.
     *
     * @return Lista de reservas asociadas a la factura.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la lista de reservas asociadas a la factura.
     *
     * @param reservas Nueva lista de reservas asociadas a la factura.
     */
    public void setReservas(List<Reserva> reservas) {
        if (reservas == null || reservas.isEmpty()) {
            throw new IllegalArgumentException("La lista de reservas no puede ser nula o vacía.");
        }

        this.reservas = reservas;
        calcularFactura();
    }

    /**
     * Obtiene el precio total de la factura.
     *
     * @return Precio total de la factura.
     */
    public BigDecimal getPrecioTotal() {
        return precioTotal;
    }

    /**
     * Obtiene el precio total sin IVA.
     *
     * @return Precio total sin IVA.
     */
    public BigDecimal getPrecioSinIva() {
        return precioSinIva;
    }

    /**
     * Obtiene el precio total del IVA.
     *
     * @return Precio total del IVA.
     */
    public BigDecimal getPrecioDelIva() {
        return precioDelIva;
    }

    /**
     * Obtiene el número único de la factura.
     *
     * @return Número único de la factura.
     */
    public String getNumeroFactura() {
        return numeroFactura;
    }

    /**
     * Obtiene la fecha de emisión de la factura.
     *
     * @return Fecha de emisión de la factura.
     */
    public LocalDate getFechaEmision() {
        return fechaEmision;
    }

    /**
     * Devuelve una representación de cadena de esta factura.
     *
     * @return Una cadena que representa esta factura.
     */
    @Override
    public String toString() {
        return "Factura{" +
                "reservas=" + reservas +
                ", precioTotal=" + precioTotal +
                ", fechaEmision=" + fechaEmision +
                ", precioSinIva=" + precioSinIva +
                ", precioDelIva=" + precioDelIva +
                ", numeroFactura='" + numeroFactura + '\'' +
                '}';
    }

}
