package com.voyageclub.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void constructorConParametrosDeberiaInicializarCorrectamente() {
        Usuario usuario = new Usuario("John", "Doe", "Smith", "john.doe@example.com", "123456789", "password");

        assertEquals("John", usuario.getNombre());
        assertEquals("Doe", usuario.getApellido1());
        assertEquals("Smith", usuario.getApellido2());
        assertEquals("john.doe@example.com", usuario.getCorreoElectronico());
        assertTrue(usuario.verificarContrasena("password"));
        assertEquals("123456789", usuario.getDni());

        assertNotNull(usuario.getCodigoUsuario(), "El código de usuario no debería ser nulo");
        assertNotNull(usuario.getReservas(), "La lista de reservas no debería ser nula");
    }

    @Test
    public void verificarContrasenaConContrasenaCorrectaDeberiaRetornarTrue() {
        Usuario usuario = new Usuario();
        usuario.setContrasena("password");

        assertTrue(usuario.verificarContrasena("password"), "La contraseña correcta debería retornar true");
    }

    @Test
    public void verificarContrasenaConContrasenaIncorrectaDeberiaRetornarFalse() {
        Usuario usuario = new Usuario();
        usuario.setContrasena("password");

        assertFalse(usuario.verificarContrasena("wrongpassword"), "La contraseña incorrecta debería retornar false");
    }

    @Test
    public void generarCodigoUsuarioDeberiaRetornarCodigoConFormatoCorrecto() {
        Usuario usuario = new Usuario("John", "Doe", "Smith", "john.doe@example.com", "123456789", "password");
        String codigoUsuario = usuario.generarCodigoUsuario();

        assertTrue(codigoUsuario.matches("[A-Z]{3}-\\d{8}-\\d{2}-[a-z]{2}"),
                "El código de usuario no tiene el formato esperado");
    }

    @Test
    public void generarCodigoUsuarioDeberiaSerUnicoParaCadaUsuario() {
        Usuario usuario1 = new Usuario("John", "Doe", "Smith", "john.doe@example.com", "123456789", "password");
        Usuario usuario2 = new Usuario("Jane", "Doe", "Johnson", "jane.doe@example.com", "987654321", "password");

        assertNotEquals(usuario1.generarCodigoUsuario(), usuario2.generarCodigoUsuario(),
                "Los códigos de usuario deberían ser únicos");
    }

    // Puedes agregar más pruebas según sea necesario
}