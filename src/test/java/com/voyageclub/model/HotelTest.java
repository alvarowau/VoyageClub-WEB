package com.voyageclub.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Clase de prueba para la clase Hotel.
 */
class HotelTest {

    @Test
    void generarCodigoHotel() {
        // Crear un hotel de ejemplo
        Hotel hotel = new Hotel("Hotel Ejemplo", "Dirección de Ejemplo", 4, "info@example.com", "http://www.example.com", "Descripción del hotel", "123456789");

        // Obtener el código generado
        String codigoGenerado = hotel.generarCodigoHotel();

        // Verificar que el código no sea nulo ni vacío
        assertNotNull(codigoGenerado);
        assertFalse(codigoGenerado.isEmpty());

        // Verificar que el código tiene el formato esperado
        assertTrue(codigoGenerado.matches("VOYAGE[A-Z]{1,2}-\\d{1,2}-[a-zA-Z]{2}"));
    }

    @Test
    void constructorConImagenes() {
        // Crear un hotel de ejemplo con imágenes
        byte[] imagenSecundaria = "Imagen secundaria".getBytes();
        byte[] imagenPrincipal = "Imagen principal".getBytes();
        Hotel hotel = new Hotel("Hotel Ejemplo", "Dirección de Ejemplo", 4, "info@example.com", "http://www.example.com", "Descripción del hotel", "123456789", imagenSecundaria, imagenPrincipal);

        // Verificar que las imágenes se han asignado correctamente
        assertArrayEquals(imagenSecundaria, hotel.getImagenSecundaria());
        assertArrayEquals(imagenPrincipal, hotel.getImagenPrincipal());
    }

    // Puedes agregar más métodos de prueba según sea necesario para cubrir otros aspectos de la clase Hotel.
}
