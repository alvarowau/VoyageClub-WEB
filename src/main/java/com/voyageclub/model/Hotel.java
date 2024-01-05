package com.voyageclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Arrays;
import java.util.List;

/**
 * La clase Hotel representa a un hotel en el sistema.
 * Contiene información detallada sobre el hotel y métodos para generar un código único y vistoso.
 *
 * Esta clase extiende la clase BaseEntity, que proporciona un identificador único para todas las entidades.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Entity
public class Hotel extends BaseEntity {

    /**
     * Nombre del hotel.
     */
    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    /**
     * Dirección del hotel.
     */
    @NotNull
    @Size(min = 3, max = 200)
    private String direccion;

    /**
     * Número de estrellas del hotel.
     */
    @NotNull
    private Integer estrellas;

    /**
     * Correo electrónico de contacto del hotel.
     */
    @NotNull
    @Email
    private String email;

    /**
     * Página web del hotel.
     */
    @Size(max = 100)
    private String web;

    /**
     * Descripción del hotel.
     */
    private String descripcion;

    /**
     * Número de teléfono del hotel.
     */
    private String telefono;

    /**
     * Código único y vistoso del hotel.
     */
    private String codigoHotel;

    @Lob
    @Column(name = "imagen-secundario", columnDefinition = "LONGBLOB")
    private byte[] imagenSecundaria;

    @Lob
    @Column(name = "imagen-principal", columnDefinition = "LONGBLOB")
    private byte[] imagenPrincipal;

    /**
     * Lista de estancias de lujo asociadas a este hotel.
     */
    @OneToMany(mappedBy = "hotel")
    private List<LuxuryStay> luxuryStays;

    /**
     * Constructor vacío necesario para JPA. Este constructor debe ser utilizado por el proveedor de persistencia.
     */
    public Hotel() {
        // Constructor vacío necesario para JPA
    }

    /**
     * Constructor que inicializa los atributos básicos del hotel.
     *
     * @param nombre     Nombre del hotel.
     * @param direccion  Dirección del hotel.
     * @param estrellas  Número de estrellas del hotel.
     * @param email      Correo electrónico de contacto del hotel.
     * @param web        Página web del hotel.
     * @param descripcion Descripción del hotel.
     * @param telefono   Número de teléfono del hotel.
     */
    public Hotel(String nombre, String direccion, Integer estrellas, String email, String web, String descripcion, String telefono) {
        if (nombre == null || direccion == null || estrellas == null || email == null || telefono == null) {
            throw new IllegalArgumentException("Ningún parámetro puede ser nulo en el constructor de Hotel.");
        }

        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
        this.email = email;
        this.web = web;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.codigoHotel = generarCodigoHotel();
    }

    /**
     * Constructor adicional que incluye imágenes del hotel.
     *
     * @param nombre            Nombre del hotel.
     * @param direccion         Dirección del hotel.
     * @param estrellas         Número de estrellas del hotel.
     * @param email             Correo electrónico de contacto del hotel.
     * @param web               Página web del hotel.
     * @param descripcion       Descripción del hotel.
     * @param telefono          Número de teléfono del hotel.
     * @param imagenSecundaria  Imagen secundaria del hotel.
     * @param imagenPrincipal   Imagen principal del hotel.
     */
    public Hotel(String nombre, String direccion, Integer estrellas, String email, String web, String descripcion,
                 String telefono, byte[] imagenSecundaria, byte[] imagenPrincipal) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.estrellas = estrellas;
        this.email = email;
        this.web = web;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.codigoHotel = generarCodigoHotel();
        this.imagenSecundaria = imagenSecundaria;
        this.imagenPrincipal = imagenPrincipal;
    }

    /**
     * Genera un código único y vistoso para el hotel.
     *
     * @return Código único y vistoso del hotel.
     */
    protected String generarCodigoHotel() {
        // Utilizar las iniciales del nombre del hotel
        String iniciales = nombre.substring(0, Math.min(2, nombre.length())).toUpperCase();

        // Obtener la cantidad de estrellas como parte del código
        String estrellasParte = estrellas.toString();

        // Obtener el primer y último carácter del correo electrónico de contacto
        String emailPrimero = email.substring(0, 1);
        String emailUltimo = email.length() > 1 ? email.substring(email.length() - 1) : "";

        // Combinar las partes para formar el código final
        return "VOYAGE" + iniciales + "-" + estrellasParte + "-" + emailPrimero + emailUltimo;
    }

    /**
     * Obtiene el nombre del hotel.
     *
     * @return El nombre del hotel.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del hotel.
     *
     * @param nombre El nuevo nombre del hotel.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la dirección del hotel.
     *
     * @return La dirección del hotel.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección del hotel.
     *
     * @param direccion La nueva dirección del hotel.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene el número de estrellas del hotel.
     *
     * @return El número de estrellas del hotel.
     */
    public Integer getEstrellas() {
        return estrellas;
    }

    /**
     * Establece el número de estrellas del hotel.
     *
     * @param estrellas El nuevo número de estrellas del hotel.
     */
    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }

    /**
     * Obtiene el correo electrónico de contacto del hotel.
     *
     * @return El correo electrónico de contacto del hotel.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico de contacto del hotel.
     *
     * @param email El nuevo correo electrónico de contacto del hotel.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la página web del hotel.
     *
     * @return La página web del hotel.
     */
    public String getWeb() {
        return web;
    }

    /**
     * Establece la página web del hotel.
     *
     * @param web La nueva página web del hotel.
     */
    public void setWeb(String web) {
        this.web = web;
    }

    /**
     * Obtiene la descripción del hotel.
     *
     * @return La descripción del hotel.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del hotel.
     *
     * @param descripcion La nueva descripción del hotel.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el número de teléfono del hotel.
     *
     * @return El número de teléfono del hotel.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Establece el número de teléfono del hotel.
     *
     * @param telefono El nuevo número de teléfono del hotel.
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * Obtiene el código único y vistoso del hotel.
     *
     * @return El código único y vistoso del hotel.
     */
    public String getCodigoHotel() {
        return codigoHotel;
    }

    /**
     * Establece el código único y vistoso del hotel.
     *
     * @param codigoHotel El nuevo código único y vistoso del hotel.
     */
    public void setCodigoHotel(String codigoHotel) {
        this.codigoHotel = codigoHotel;
    }

    /**
     * Obtiene la imagen secundaria del hotel.
     *
     * @return La imagen secundaria del hotel.
     */
    public byte[] getImagenSecundaria() {
        return imagenSecundaria;
    }

    /**
     * Establece la imagen secundaria del hotel.
     *
     * @param imagenSecundaria La nueva imagen secundaria del hotel.
     */
    public void setImagenSecundaria(byte[] imagenSecundaria) {
        this.imagenSecundaria = imagenSecundaria;
    }

    /**
     * Obtiene la imagen principal del hotel.
     *
     * @return La imagen principal del hotel.
     */
    public byte[] getImagenPrincipal() {
        return imagenPrincipal;
    }

    /**
     * Establece la imagen principal del hotel.
     *
     * @param imagenPrincipal La nueva imagen principal del hotel.
     */
    public void setImagenPrincipal(byte[] imagenPrincipal) {
        this.imagenPrincipal = imagenPrincipal;
    }

    /**
     * Obtiene la lista de estancias de lujo asociadas a este hotel.
     *
     * @return La lista de estancias de lujo asociadas a este hotel.
     */
    public List<LuxuryStay> getLuxuryStays() {
        return luxuryStays;
    }

    /**
     * Establece la lista de estancias de lujo asociadas a este hotel.
     *
     * @param luxuryStays La nueva lista de estancias de lujo asociadas a este hotel.
     */
    public void setLuxuryStays(List<LuxuryStay> luxuryStays) {
        this.luxuryStays = luxuryStays;
    }

    /**
     * Devuelve una representación de cadena de este hotel.
     *
     * @return Una cadena que representa este hotel.
     */
    @Override
    public String toString() {
        return "Hotel{" +
                "nombre='" + nombre + '\'' +
                ", direccion='" + direccion + '\'' +
                ", estrellas=" + estrellas +
                ", email='" + email + '\'' +
                ", web='" + web + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", codigoHotel='" + codigoHotel + '\'' +
                ", imagenSecundaria=" + Arrays.toString(imagenSecundaria) +
                ", imagenPrincipal=" + Arrays.toString(imagenPrincipal) +
                ", luxuryStays=" + luxuryStays +
                '}';
    }
}
