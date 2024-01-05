package com.voyageclub.model;
import com.voyageclub.model.enumerdos.Amenity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class LuxuryStayTest {

    private LuxuryStay luxuryStay;
    private Hotel hotel;

    @BeforeEach
    public void setUp() {
        // Configuración inicial para las pruebas
        hotel = new Hotel("NombreHotel", "DirecciónHotel", 5, "hotel@example.com", "www.hotel.com", "Descripción del hotel", "123456789");
        List<Amenity> amenidades = Arrays.asList(Amenity.WIFI, Amenity.SPA);
        luxuryStay = new LuxuryStay("NombreEstancia", "Descripción de la estancia", BigDecimal.valueOf(200.00), amenidades, hotel);
    }

    @Test
    public void luxuryStayInitializationTest() {
        assertNotNull(luxuryStay);
        assertNotNull(luxuryStay.getCodigoLuxuryStay());
        assertEquals("NombreEstancia", luxuryStay.getNombre());
        assertEquals("Descripción de la estancia", luxuryStay.getDescripcion());
        assertEquals(BigDecimal.valueOf(200.00), luxuryStay.getPrecioNoche());
        assertEquals(Arrays.asList(Amenity.WIFI, Amenity.SPA), luxuryStay.getAmenidades());
        assertNotNull(luxuryStay.getHotel());
        assertEquals(hotel, luxuryStay.getHotel()); // Verificar que el hotel es el mismo
        assertNull(luxuryStay.getFechaInicioDisponible());
        assertNull(luxuryStay.getFechaFinDisponible());
        assertNull(luxuryStay.getImagenPrincipal());
        assertNull(luxuryStay.getImagenDerecha());
        assertNull(luxuryStay.getImagenIzquierda());
    }

    @Test
    public void luxuryStayCodeGenerationTest() {
        // Verifica que se genere un código único
        LuxuryStay anotherLuxuryStay = new LuxuryStay("OtroNombre", "Otra descripción", BigDecimal.valueOf(250.00), Arrays.asList(Amenity.SPA), hotel);
        assertNotEquals(luxuryStay.getCodigoLuxuryStay(), anotherLuxuryStay.getCodigoLuxuryStay());
    }

    // Puedes agregar más pruebas según sea necesario
}
