package com.voyageclub.model;

import com.voyageclub.model.enumerdos.Amenity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * La clase LuxuryStay representa una estancia de lujo en el sistema.
 * Contiene información detallada sobre la estancia y métodos para generar un código único.
 *
 * Esta clase extiende la clase BaseEntity, que proporciona un identificador único para todas las entidades.
 * Cada estancia de lujo está asociada a un hotel y puede tener amenidades y reservas.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Entity
public class LuxuryStay extends BaseEntity {

    /**
     * Nombre de la estancia de lujo.
     */
    @NotNull
    @Size(min = 5, max = 200)
    private String nombre;

    /**
     * Descripción de la estancia de lujo.
     */
    @NotNull
    @Size(min = 5, max = 200)
    private String descripcion;

    /**
     * Precio por noche de la estancia de lujo.
     */
    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal precioNoche;

    /**
     * Lista de amenidades ofrecidas en la estancia de lujo.
     */
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Amenity> amenidades;

    /**
     * Lista de reservas asociadas a esta estancia de lujo.
     */
    @OneToMany(mappedBy = "luxuryStay")
    private List<Reserva> reservas;

    /**
     * Hotel al que pertenece esta estancia de lujo.
     */
    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    /**
     * Código único de la estancia de lujo.
     */
    @NotNull
    @Column(unique = true)
    private String codigoLuxuryStay; // Código único del LuxuryStay

    /**
     * Fecha de inicio disponible para reservas.
     */
    private LocalDate fechaInicioDisponible;

    /**
     * Fecha de fin disponible para reservas.
     */
    private LocalDate fechaFinDisponible;

    @Lob
    @Column(name = "imagen-principal", columnDefinition = "LONGBLOB")
    private byte[] imagenPrincipal;

    @Lob
    @Column(name = "imagen-derecha", columnDefinition = "LONGBLOB")
    private byte[] imagenDerecha;

    @Lob
    @Column(name = "imagen-izquierda", columnDefinition = "LONGBLOB")
    private byte[] imagenIzquierda;

    /**
     * Constructor vacío necesario para JPA.
     */
    public LuxuryStay() {
        // Constructor vacío necesario para JPA
    }

    /**
     * Constructor que inicializa los atributos básicos de la estancia de lujo.
     *
     * @param nombre            Nombre de la estancia de lujo.
     * @param descripcion       Descripción de la estancia de lujo.
     * @param precioNoche       Precio por noche de la estancia de lujo.
     * @param amenidades        Lista de amenidades ofrecidas en la estancia de lujo.
     * @param hotel             Hotel al que pertenece esta estancia de lujo.
     */
    public LuxuryStay(String nombre, String descripcion, BigDecimal precioNoche, List<Amenity> amenidades, Hotel hotel) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioNoche = precioNoche;
        this.amenidades = amenidades;
        this.hotel = hotel;
        this.codigoLuxuryStay = generarCodigoLuxuryStay();
    }

    /**
     * Constructor que inicializa los atributos de la estancia de lujo con imágenes.
     *
     * @param nombre                    Nombre de la estancia de lujo.
     * @param descripcion               Descripción de la estancia de lujo.
     * @param precioNoche               Precio por noche de la estancia de lujo.
     * @param amenidades                Lista de amenidades ofrecidas en la estancia de lujo.
     * @param hotel                     Hotel al que pertenece esta estancia de lujo.
     * @param fechaInicioDisponible     Fecha de inicio disponible para reservas.
     * @param fechaFinDisponible        Fecha de fin disponible para reservas.
     * @param imagenPrincipal           Imagen principal de la estancia de lujo.
     * @param imagenDerecha             Imagen derecha de la estancia de lujo.
     * @param imagenIzquierda           Imagen izquierda de la estancia de lujo.
     */
    public LuxuryStay(String nombre, String descripcion, BigDecimal precioNoche, List<Amenity> amenidades, Hotel hotel,
                      LocalDate fechaInicioDisponible, LocalDate fechaFinDisponible,
                      byte[] imagenPrincipal, byte[] imagenDerecha, byte[] imagenIzquierda) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioNoche = precioNoche;
        this.amenidades = amenidades;
        this.hotel = hotel;
        this.codigoLuxuryStay = generarCodigoLuxuryStay();
        this.fechaInicioDisponible = fechaInicioDisponible;
        this.fechaFinDisponible = fechaFinDisponible;
        this.imagenPrincipal = imagenPrincipal;
        this.imagenDerecha = imagenDerecha;
        this.imagenIzquierda = imagenIzquierda;
    }

    /**
     * Constructor que inicializa los atributos de la estancia de lujo sin imágenes.
     *
     * @param nombre                    Nombre de la estancia de lujo.
     * @param descripcion               Descripción de la estancia de lujo.
     * @param precioNoche               Precio por noche de la estancia de lujo.
     * @param amenidades                Lista de amenidades ofrecidas en la estancia de lujo.
     * @param hotel                     Hotel al que pertenece esta estancia de lujo.
     * @param fechaInicioDisponible     Fecha de inicio disponible para reservas.
     * @param fechaFinDisponible        Fecha de fin disponible para reservas.
     */
    public LuxuryStay(String nombre, String descripcion, BigDecimal precioNoche, List<Amenity> amenidades, Hotel hotel,
                      LocalDate fechaInicioDisponible, LocalDate fechaFinDisponible) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioNoche = precioNoche;
        this.amenidades = amenidades;
        this.hotel = hotel;
        this.fechaInicioDisponible = fechaInicioDisponible;
        this.fechaFinDisponible = fechaFinDisponible;
        this.codigoLuxuryStay = generarCodigoLuxuryStay();
    }

    /**
     * Genera un código único para la estancia de lujo utilizando UUID.
     * El código resultante es globalmente único y no depende de un contador estático.
     *
     * @return Código único de la estancia de lujo.
     */
    protected String generarCodigoLuxuryStay() {
        // Utilizar las iniciales del nombre del hotel
        String inicialesHotel = hotel.getNombre().substring(0, 2).toUpperCase();

        // Obtener el primer y segundo carácter del nombre de la LuxuryStay
        String nombreLuxuryStay = nombre.substring(0, 2);

        // Generar un UUID aleatorio
        UUID uuid = UUID.randomUUID();

        // Combinar las partes para formar el código final
        return "Voyage" + inicialesHotel + hotel.getCodigoHotel() + "-" + nombreLuxuryStay + "-" + uuid.toString();
    }

    /**
     * Obtiene el nombre de la estancia de lujo.
     *
     * @return Nombre de la estancia de lujo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la estancia de lujo.
     *
     * @param nombre Nuevo nombre de la estancia de lujo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción de la estancia de lujo.
     *
     * @return Descripción de la estancia de lujo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción de la estancia de lujo.
     *
     * @param descripcion Nueva descripción de la estancia de lujo.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio por noche de la estancia de lujo.
     *
     * @return Precio por noche de la estancia de lujo.
     */
    public BigDecimal getPrecioNoche() {
        return precioNoche;
    }

    /**
     * Establece el precio por noche de la estancia de lujo.
     *
     * @param precioNoche Nuevo precio por noche de la estancia de lujo.
     */
    public void setPrecioNoche(BigDecimal precioNoche) {
        this.precioNoche = precioNoche;
    }

    /**
     * Obtiene la lista de amenidades ofrecidas en la estancia de lujo.
     *
     * @return Lista de amenidades ofrecidas en la estancia de lujo.
     */
    public List<Amenity> getAmenidades() {
        return amenidades;
    }

    /**
     * Establece la lista de amenidades ofrecidas en la estancia de lujo.
     *
     * @param amenidades Nueva lista de amenidades ofrecidas en la estancia de lujo.
     */
    public void setAmenidades(List<Amenity> amenidades) {
        this.amenidades = amenidades;
    }

    /**
     * Obtiene la lista de reservas asociadas a esta estancia de lujo.
     *
     * @return Lista de reservas asociadas a esta estancia de lujo.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la lista de reservas asociadas a esta estancia de lujo.
     *
     * @param reservas Nueva lista de reservas asociadas a esta estancia de lujo.
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Obtiene el hotel al que pertenece esta estancia de lujo.
     *
     * @return Hotel al que pertenece esta estancia de lujo.
     */
    public Hotel getHotel() {
        return hotel;
    }

    /**
     * Establece el hotel al que pertenece esta estancia de lujo.
     *
     * @param hotel Nuevo hotel al que pertenece esta estancia de lujo.
     */
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    /**
     * Obtiene el código único de la estancia de lujo.
     *
     * @return Código único de la estancia de lujo.
     */
    public String getCodigoLuxuryStay() {
        return codigoLuxuryStay;
    }

    /**
     * Establece el código único de la estancia de lujo.
     *
     * @param codigoLuxuryStay Nuevo código único de la estancia de lujo.
     */
    public void setCodigoLuxuryStay(String codigoLuxuryStay) {
        this.codigoLuxuryStay = codigoLuxuryStay;
    }

    /**
     * Obtiene la fecha de inicio disponible para reservas.
     *
     * @return Fecha de inicio disponible para reservas.
     */
    public LocalDate getFechaInicioDisponible() {
        return fechaInicioDisponible;
    }

    /**
     * Establece la fecha de inicio disponible para reservas.
     *
     * @param fechaInicioDisponible Nueva fecha de inicio disponible para reservas.
     */
    public void setFechaInicioDisponible(LocalDate fechaInicioDisponible) {
        this.fechaInicioDisponible = fechaInicioDisponible;
    }

    /**
     * Obtiene la fecha de fin disponible para reservas.
     *
     * @return Fecha de fin disponible para reservas.
     */
    public LocalDate getFechaFinDisponible() {
        return fechaFinDisponible;
    }

    /**
     * Establece la fecha de fin disponible para reservas.
     *
     * @param fechaFinDisponible Nueva fecha de fin disponible para reservas.
     */
    public void setFechaFinDisponible(LocalDate fechaFinDisponible) {
        this.fechaFinDisponible = fechaFinDisponible;
    }

    /**
     * Obtiene la imagen principal de la estancia de lujo.
     *
     * @return Imagen principal de la estancia de lujo.
     */
    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    /**
     * Establece la imagen principal de la estancia de lujo.
     *
     * @param imagenPrincipal Nueva imagen principal de la estancia de lujo.
     */
    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    /**
     * Obtiene la imagen derecha de la estancia de lujo.
     *
     * @return Imagen derecha de la estancia de lujo.
     */
    public byte[] getImagenDerecha() {
        return imagenDerecha;
    }

    /**
     * Establece la imagen derecha de la estancia de lujo.
     *
     * @param imagenDerecha Nueva imagen derecha de la estancia de lujo.
     */
    public void setImagenDerecha(byte[] imagenDerecha) {
        this.imagenDerecha = imagenDerecha;
    }

    /**
     * Obtiene la imagen izquierda de la estancia de lujo.
     *
     * @return Imagen izquierda de la estancia de lujo.
     */
    public byte[] getImagenIzquierda() {
        return imagenIzquierda;
    }

    /**
     * Establece la imagen izquierda de la estancia de lujo.
     *
     * @param imagenIzquierda Nueva imagen izquierda de la estancia de lujo.
     */
    public void setImagenIzquierda(byte[] imagenIzquierda) {
        this.imagenIzquierda = imagenIzquierda;
    }

    /**
     * Devuelve una representación en cadena de la estancia de lujo.
     *
     * @return Cadena que representa la estancia de lujo.
     */
    @Override
    public String toString() {
        return "LuxuryStay{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precioNoche=" + precioNoche +
                ", amenidades=" + amenidades +
                ", reservas=" + reservas +
                ", hotel=" + hotel +
                ", codigoLuxuryStay='" + codigoLuxuryStay + '\'' +
                ", fechaInicioDisponible=" + fechaInicioDisponible +
                ", fechaFinDisponible=" + fechaFinDisponible +
                ", imagenPrincipal=" + Arrays.toString(imagenPrincipal) +
                ", imagenDerecha=" + Arrays.toString(imagenDerecha) +
                ", imagenIzquierda=" + Arrays.toString(imagenIzquierda) +
                '}';
    }


}
