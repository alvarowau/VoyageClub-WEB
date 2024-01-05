package com.voyageclub.model;

import com.voyageclub.model.enumerdos.Amenity;
import com.voyageclub.model.enumerdos.EstadoReserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class ReservaTest {

    private EntityManagerFactory emf;
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        // Configuración inicial para las pruebas
        emf = Persistence.createEntityManagerFactory("testPU"); // Utiliza el nombre de tu unidad de persistencia ("testPU")
        em = emf.createEntityManager();
    }

    @AfterEach
    public void tearDown() {
        // Limpiar después de cada prueba
        em.close();
        emf.close();
    }

    @Test
    public void reservaInitializationTest() {
        Usuario usuario = new Usuario("Alvaro", "Bajo", "Tabero", "alvarobajo893@gmail.com", "12345678", "123456");
        Hotel hotel = new Hotel("NombreHotel", "DirecciónHotel", 5, "hotel@example.com", "www.hotel.com", "Descripción del hotel", "123456789");
        LuxuryStay luxuryStay = new LuxuryStay("NombreEstancia", "Descripción de la estancia", BigDecimal.valueOf(200.00), Arrays.asList(Amenity.WIFI, Amenity.WIFI), hotel);

        em.getTransaction().begin();
        em.persist(usuario);
        em.persist(hotel);
        em.persist(luxuryStay);
        em.getTransaction().commit();

        LocalDate fechaInicio = LocalDate.now();
        LocalDate fechaFin = LocalDate.now().plusDays(5);

        Reserva reserva = new Reserva(usuario, luxuryStay, fechaInicio, fechaFin, 2);
        em.getTransaction().begin();
        em.persist(reserva);
        em.getTransaction().commit();

        assertNotNull(reserva);
        assertNotNull(reserva.getId()); // Verifica que se haya asignado un ID después de la persistencia
        assertEquals(usuario, reserva.getUsuario());
        assertEquals(luxuryStay, reserva.getLuxuryStay());
        assertNull(reserva.getFactura());
        assertEquals(fechaInicio, reserva.getFechaInicio());
        assertEquals(fechaFin, reserva.getFechaFin());
        assertEquals(2, reserva.getCantidadPersonas());
        assertEquals(EstadoReserva.PENDIENTE, reserva.getEstado()); // Por defecto pendiente

        // Puedes agregar más aserciones según sea necesario
    }

    // Puedes agregar más pruebas según sea necesario
}
