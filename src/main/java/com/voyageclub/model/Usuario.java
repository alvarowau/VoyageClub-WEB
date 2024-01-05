package com.voyageclub.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * La clase Usuario representa a un usuario en el sistema.
 * Contiene información básica del usuario y métodos para manejar contraseñas de manera segura.
 */
@Entity
public class Usuario extends BaseEntity {

    /**
     * Nombre del usuario.
     */
    @NotNull
    @Size(min = 3, max = 50)
    private String nombre;

    /**
     * Primer apellido del usuario.
     */
    @NotNull
    @Size(min = 3, max = 50)
    private String apellido1;

    /**
     * Segundo apellido del usuario.
     */
    @NotNull
    @Size(min = 3, max = 50)
    private String apellido2;

    /**
     * Correo electrónico del usuario.
     */
    @NotNull
    @Email
    private String correoElectronico;

    /**
     * Contraseña hasheada del usuario.
     */
    private String contrasenaHash;

    /**
     * Número de identificación del usuario.
     */
    @NotNull
    @Column(unique = true)
    private String dni;

    /**
     * Código único del usuario.
     */
    @NotNull
    @Column(unique = true)
    private String codigoUsuario;

    /**
     * Lista de reservas asociadas a este usuario.
     */
    @OneToMany(mappedBy = "usuario")
    private List<Reserva> reservas;

    /**
     * Constructor vacío necesario para JPA.
     */
    public Usuario() {
        // Constructor vacío necesario para JPA
    }

    /**
     * Constructor que inicializa los atributos básicos del usuario.
     *
     * @param nombre            Nombre del usuario.
     * @param apellido1         Primer apellido del usuario.
     * @param apellido2         Segundo apellido del usuario.
     * @param correoElectronico Correo electrónico del usuario.
     * @param dni               Número de identificación del usuario.
     * @param contrasena        Contraseña en texto plano.
     */
    public Usuario(String nombre, String apellido1, String apellido2, String correoElectronico, String dni, String contrasena) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.correoElectronico = correoElectronico;
        // Hashear la contraseña proporcionada y almacenarla
        this.setContrasena(contrasena);
        this.dni = dni;
        this.codigoUsuario = generarCodigoUsuario();
        // Inicializar la lista de reservas
        this.reservas = new ArrayList<>();
    }

    // Métodos getter y setter

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
     * @param nombre Nuevo nombre del usuario.
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
     * @param apellido1 Nuevo primer apellido del usuario.
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
     * @param apellido2 Nuevo segundo apellido del usuario.
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
     * @param correoElectronico Nuevo correo electrónico del usuario.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * Obtiene la contraseña hasheada del usuario.
     *
     * @return Contraseña hasheada del usuario.
     */
    public String getContrasenaHash() {
        return contrasenaHash;
    }

    /**
     * Establece la contraseña del usuario de manera segura.
     *
     * @param contrasena Contraseña en texto plano.
     */
    public void setContrasena(String contrasena) {
        this.contrasenaHash = hashContrasena(contrasena);
    }

    /**
     * Verifica si la contraseña proporcionada coincide con la almacenada.
     *
     * @param contrasena Contraseña en texto plano para verificar.
     * @return true si la contraseña coincide, false en caso contrario.
     */
    public boolean verificarContrasena(String contrasena) {
        return contrasenaHash.equals(hashContrasena(contrasena));
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
     * @param dni Nuevo número de identificación del usuario.
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
     * @param codigoUsuario Nuevo código único del usuario.
     */
    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    /**
     * Obtiene la lista de reservas asociadas a este usuario.
     *
     * @return Lista de reservas asociadas a este usuario.
     */
    public List<Reserva> getReservas() {
        return reservas;
    }

    /**
     * Establece la lista de reservas asociadas a este usuario.
     *
     * @param reservas Nueva lista de reservas asociadas a este usuario.
     */
    public void setReservas(List<Reserva> reservas) {
        this.reservas = reservas;
    }

    /**
     * Método para generar el código de usuario automáticamente.
     *
     * @return Código de usuario generado automáticamente.
     */
    public String generarCodigoUsuario() {
        // Utilizar las iniciales del nombre y apellidos para el código
        String iniciales = nombre.substring(0, 1) + apellido1.substring(0, 1) + apellido2.substring(0, 1);

        // Obtener la fecha actual en formato "yyyyMMdd"
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
        String fechaActual = sdf.format(new Date());

        // Agregar los últimos dígitos del DNI y los primeros dígitos del correo electrónico al código
        String dniParte = dni.substring(dni.length() - 2);
        String correoParte = correoElectronico.split("@")[0].substring(0, 2);

        // Combinar las iniciales, la fecha actual, el DNI y el correo electrónico para el código final
        return iniciales.toUpperCase() + "-" + fechaActual + "-" + dniParte + "-" + correoParte;
    }

    /**
     * Hashea la contraseña utilizando el algoritmo SHA-256.
     *
     * @param contrasena Contraseña en texto plano.
     * @return Contraseña hasheada.
     */
    private String hashContrasena(String contrasena) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    contrasena.getBytes(StandardCharsets.UTF_8));

            // Convertir el hash a formato hexadecimal
            StringBuilder hexString = new StringBuilder(2 * encodedHash.length);
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // Manejar la excepción apropiadamente en tu aplicación
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }

    /**
     * Devuelve una representación de cadena de este objeto Usuario.
     *
     * @return Una cadena que contiene la información detallada del usuario, incluyendo nombre, apellidos,
     *         correo electrónico, contraseña (hash), DNI, código de usuario y reservas asociadas.
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", correoElectronico='" + correoElectronico + '\'' +
                ", contrasenaHash='" + contrasenaHash + '\'' +
                ", dni='" + dni + '\'' +
                ", codigoUsuario='" + codigoUsuario + '\'' +
                ", reservas=" + reservas +
                '}';
    }

}
