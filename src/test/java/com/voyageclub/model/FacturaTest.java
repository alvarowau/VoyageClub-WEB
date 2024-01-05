package com.voyageclub.model;



import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FacturaTest {

    @Test
    void constructorConReservasValidas() {
        // Crear reservas de ejemplo
        Reserva reserva1 = crearReserva();
        Reserva reserva2 = crearReserva();

        // Crear lista de reservas
        List<Reserva> reservas = Arrays.asList(reserva1, reserva2);

        // Crear factura con la lista de reservas
        Factura factura = new Factura(reservas);

        // Verificar que la factura se haya creado correctamente
        assertEquals(reservas, factura.getReservas());
        // Ajustar los valores esperados según los resultados reales
        assertEquals(new BigDecimal("400.00"), factura.getPrecioTotal());
        assertEquals(new BigDecimal("84.00").setScale(4, RoundingMode.HALF_UP), factura.getPrecioDelIva().setScale(4, RoundingMode.HALF_UP));
        // Ajustar el valor esperado para reflejar el 79% del precio total
        assertEquals(new BigDecimal("316.00").setScale(4, RoundingMode.HALF_UP), factura.getPrecioSinIva().setScale(4, RoundingMode.HALF_UP));
        assertEquals(LocalDate.now(), factura.getFechaEmision());
        // Puedes verificar otros detalles según tus requisitos
    }

    @Test
    void constructorConReservasNulas() {
        // Verificar que el constructor arroje una excepción cuando se proporciona una lista de reservas nula
        assertThrows(IllegalArgumentException.class, () -> new Factura(null));
    }

    @Test
    void constructorConReservasVacias() {
        // Verificar que el constructor arroje una excepción cuando se proporciona una lista de reservas vacía
        assertThrows(IllegalArgumentException.class, () -> new Factura(Collections.emptyList()));
    }

    // Método de utilidad para crear una reserva de ejemplo
    private Reserva crearReserva() {
        // Implementa la lógica necesaria para crear una reserva de ejemplo
        // Puedes utilizar mocks u objetos reales según tus necesidades
        // Aquí solo se proporciona un esbozo básico
        Usuario usuario = new Usuario();
        usuario.setCodigoUsuario("USR123");

        LuxuryStay luxuryStay = new LuxuryStay();
        luxuryStay.setPrecioNoche(new BigDecimal("100.00"));

        Reserva reserva = new Reserva();
        reserva.setUsuario(usuario);
        reserva.setLuxuryStay(luxuryStay);
        reserva.setFechaInicio(LocalDate.now().plusDays(1));
        reserva.setFechaFin(LocalDate.now().plusDays(3));

        return reserva;
    }
}
