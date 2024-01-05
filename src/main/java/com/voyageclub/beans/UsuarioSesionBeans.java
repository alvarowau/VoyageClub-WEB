package com.voyageclub.beans;

import com.voyageclub.model.Reserva;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

/**
 * Bean de sesión para gestionar la información del usuario durante la sesión.
 *
 * <p>Este bean almacena información como el nombre, apellidos, correo electrónico, DNI, etc.,
 * así como una lista de reservas asociadas al usuario.
 *
 * <p>La anotación {@link SessionScoped} indica que este bean de CDI tiene un ciclo de vida
 * asociado a la sesión del usuario.
 *
 * <p>La anotación {@link Named} se utiliza para referenciar este bean en las páginas XHTML de JSF.
 *
 * @author [Tu nombre]
 * @version 1.0
 */
@SessionScoped
@Named
public class UsuarioSesionBeans implements Serializable {

    private Long ID;

    /**
     * Nombre del usuario.
     */
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    private String apellido1;

    /**
     * Segundo apellido del usuario.
     */
    private String apellido2;

    /**
     * Correo electrónico del usuario.
     */
    private String correoElectronico;

    /**
     * Número de identificación del usuario.
     */
    private String dni;

    /**
     * Código único del usuario.
     */
    private String codigoUsuario;

    /**
     * Lista de reservas asociadas a este usuario.
     */
    private List<Reserva> reservas;

    /**
     * Constructor por defecto.
     */
    public UsuarioSesionBeans() {
    }

    /**
     * Constructor con parámetros para inicializar el bean con valores específicos.
     *
     * @param ID Identificador único del usuario.
     * @param nombre Nombre del usuario.
     * @param apellido1 Primer apellido del usuario.
     * @param apellido2 Segundo apellido del usuario.
     * @param correoElectronico Correo electrónico del usuario.
     * @param dni Número de identificación del usuario.
     * @param codigoUsuario Código único del usuario.
     * @param reservas Lista de reservas asociadas a este usuario.
     */
    public UsuarioSesionBeans(Long ID, String nombre, String apellido1, String apellido2, String correoElectronico,
                              String dni, String codigoUsuario, List<Reserva> reservas) {
        this.ID = ID;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correoElectronico = correoElectronico;
        this.dni = dni;
        this.codigoUsuario = codigoUsuario;
        this.reservas = reservas;
    }

    /**
     * Obtiene el identificador único del usuario.
     *
     * @return Identificador único del usuario.
     */
    public Long getID() {
        return ID;
    }

    /**
     * Establece el identificador único del usuario.
     *
     * @param ID Identificador único del usuario.
     */
    public void setID(Long ID) {
        this.ID = ID;
    }

    /**
     * Obtiene el nombre del usuario.
     *
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     *
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el primer apellido del usuario.
     *
     * @return Primer apellido del usuario.
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Establece el primer apellido del usuario.
     *
     * @param apellido1 Primer apellido del usuario.
     */
    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    /**
     * Obtiene el segundo apellido del usuario.
     *
     * @return Segundo apellido del usuario.
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Establece el segundo apellido del usuario.
     *
     * @param apellido2 Segundo apellido del usuario.
     */
    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     *
     * @return Correo electrónico del usuario.
     */
    public String getCorreoElectronico() {
        return correoElectronico;
    }

    /**
     * Establece el correo electrónico del usuario.
     *
     * @param correoElectronico Correo electrónico del usuario.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene el número de identificación del usuario.
     *
     * @return Número de identificación del usuario.
     */
    public String getDni() {
        return dni;
    }

    /**
     * Establece el número de identificación del usuario.
     *
     * @param dni Número de identificación del usuario.
     */
    public void setDni(String dni) {
        this.dni = dni;
    }

    /**
     * Obtiene el código único del usuario.
     *
     * @return Código único del usuario.
     */
    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    /**
     * Establece el código único del usuario.
     *
     * @param codigoUsuario Código único del usuario.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * Obtiene la lista de reservas asociadas a este usuario.
     *
     * @return Lista de reservas asociadas al usuario.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la lista de reservas asociadas a este usuario.
     *
     * @param reservas Lista de reservas asociadas al usuario.
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }
}
