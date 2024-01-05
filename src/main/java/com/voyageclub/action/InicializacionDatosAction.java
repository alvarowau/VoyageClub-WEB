package com.voyageclub.action;

import com.voyageclub.dao.*;
import com.voyageclub.model.*;

import com.voyageclub.model.enumerdos.Amenity;
import com.voyageclub.model.enumerdos.EstadoReserva;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

@ApplicationScoped
public class InicializacionDatosAction {


    @Inject
    private  HotelDAO hotelDAO;
    @Inject
    private LuxuryStayDAO luxuryStayDAO;
    @Inject
    private UsuarioDAO usuarioDAO;
    @Inject
    private ReservaDAO reservaDAO;
    @Inject
    private FacturaDAO facturaDAO;

    public InicializacionDatosAction() {

    }

    public void inicializarDatos() {
        crearDatosPersistencia();
    }




    private void crearDatosPersistencia(){
        List<Usuario> usuarios = crearUsuarios();
        List<Hotel> hoteles = crearHoteles();
        List<LuxuryStay> luxuryStays = crearLuxuryStay(hoteles);
        List<Reserva> reservas = crearReservas(usuarios, luxuryStays);
        crearFacturas(reservas);
    }

    private void crearFacturas(List<Reserva> reservas) {
        int numFacturas = 6; // Número deseado de facturas

        List<Factura> facturas = new ArrayList<>();

        for (int i = 0; i < numFacturas; i++) {
            // Calcula los índices de inicio y fin para obtener una sublista equitativa
            int startIndex = i * (reservas.size() / numFacturas);
            int endIndex = (i + 1) * (reservas.size() / numFacturas);

            // Obtiene la sublista de reservas
            List<Reserva> subReservas = reservas.subList(startIndex, endIndex);

            // Crea una factura con la sublista de reservas y la agrega a la lista de facturas
            Factura factura = new Factura(new ArrayList<>(subReservas));
            facturas.add(factura);
        }

        // Persiste todas las facturas generadas
        persistirFacturas(facturas);
    }



    private List<Reserva> crearReservas(List<Usuario> usuarios, List<LuxuryStay> luxuryStays) {
        List<Reserva> reservas = new ArrayList<>();

        for (int i = 0; i < usuarios.size(); i++) {
            LocalDate fechaInicio = LocalDate.of(2023, i + 1, 1);
            LocalDate fechaFin = LocalDate.of(2023, i + 1, 5);

            Reserva reserva = new Reserva(
                    usuarios.get(i),
                    luxuryStays.get(i),
                    fechaInicio,
                    fechaFin,

                    i + 2
            );
            reservas.add(reserva);
        }
        persistirReservas(reservas);
        return reservas;
    }

    private List<LuxuryStay> crearLuxuryStay(List<Hotel> hoteles) {
        // Establecer fechas de disponibilidad para las LuxuryStays
        LocalDate fechaInicio1 = LocalDate.of(2023, 1, 1);
        LocalDate fechaFin1 = LocalDate.of(2023, 12, 31);

        LocalDate fechaInicio2 = LocalDate.of(2023, 4, 1);
        LocalDate fechaFin2 = LocalDate.of(2023, 9, 30);

        LocalDate fechaInicio3 = LocalDate.of(2023, 2, 15);
        LocalDate fechaFin3 = LocalDate.of(2023, 8, 15);

        // Crear LuxuryStays
        List<LuxuryStay> luxuryStays = Arrays.asList(
                crearLuxuryStayIndividual("Suite Presidencial", "Amplia suite con vista al mar", new BigDecimal("500.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.SPA, Amenity.MINIBAR), hoteles.get(0), fechaInicio1, fechaFin1),
                crearLuxuryStayIndividual("Villa Montaña", "Villa privada en la montaña con piscina", new BigDecimal("800.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.PISCINA, Amenity.SPA), hoteles.get(1), fechaInicio2, fechaFin2),
                crearLuxuryStayIndividual("Habitación Ejecutiva", "Perfecta para viajes de negocios", new BigDecimal("200.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.CENTRO_NEGOCIOS), hoteles.get(2), fechaInicio3, fechaFin3),
                crearLuxuryStayIndividual("Bungalow Oasis", "Bungalow con acceso directo a la playa", new BigDecimal("600.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.RESTAURANTE, Amenity.VISTAMAR), hoteles.get(3), fechaInicio1, fechaFin1),
                crearLuxuryStayIndividual("Habitación Histórica", "Experimenta la historia en cada rincón", new BigDecimal("300.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.SEGURIDAD), hoteles.get(4), fechaInicio2, fechaFin2),
                crearLuxuryStayIndividual("Suite Relax", "Ideal para una escapada relajante", new BigDecimal("700.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.SPA), hoteles.get(5), fechaInicio3, fechaFin3),
                crearLuxuryStayIndividual("Villa Romántica", "Ambiente romántico con jacuzzi", new BigDecimal("550.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.JACUZZI), hoteles.get(0), fechaInicio1, fechaFin1),
                crearLuxuryStayIndividual("Loft Urbano", "Estilo moderno en el corazón de la ciudad", new BigDecimal("400.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.VISTAMAR), hoteles.get(1), fechaInicio2, fechaFin2),
                crearLuxuryStayIndividual("Cabaña Tranquila", "Rodeada de naturaleza y tranquilidad", new BigDecimal("750.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.AIRE_ACONDICIONADO), hoteles.get(2), fechaInicio3, fechaFin3),
                crearLuxuryStayIndividual("Habitación Familiar", "Espacio ideal para toda la familia", new BigDecimal("250.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.LIMPIEZA), hoteles.get(3), fechaInicio1, fechaFin1),
                crearLuxuryStayIndividual("Ático Elegante", "Lujo y elegancia en las alturas", new BigDecimal("900.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.ZONA_RELAJACION), hoteles.get(4), fechaInicio2, fechaFin2),
                crearLuxuryStayIndividual("Suite de Aventuras", "Diseñada para los amantes de la adrenalina", new BigDecimal("650.00"),
                        Arrays.asList(Amenity.WIFI, Amenity.DESAYUNO), hoteles.get(5), fechaInicio3, fechaFin3)
        );

        // Verificar y persistir cada LuxuryStay en la base de datos
        luxuryStays.forEach(verificarYPersistirLuxuryStay);

        return luxuryStays;
    }

    private LuxuryStay crearLuxuryStayIndividual(String nombre, String descripcion, BigDecimal precio,
                                                 List<Amenity> comodidades, Hotel hotel, LocalDate fechaInicio, LocalDate fechaFin) {
        return new LuxuryStay(nombre, descripcion, precio, comodidades, hotel, fechaInicio, fechaFin);
    }


    private List<Hotel> crearHoteles() {
        List<Hotel> listaHoteles = List.of(
                new Hotel("Hotel Playa Dorada", "Calle de la Playa, 123", 4, "info@playadorada.com", "www.playadorada.com", "Un hermoso hotel frente al mar", "123456789"),
                new Hotel("Gran Hotel Montaña", "Avenida de las Cumbres, 456", 5, "reservas@granhotelmontana.com", "www.granhotelmontana.com", "Una experiencia de lujo en la montaña", "987654321"),
                new Hotel("Hotel Urbano Plaza", "Plaza Principal, 789", 3, "contacto@urbanoplaza.com", "www.urbanoplaza.com", "Convenientemente ubicado en el corazón de la ciudad", "555555555"),
                new Hotel("Resort Oasis Tropical", "Carretera del Paraíso, Km 10", 4, "reservas@oasistropical.com", "www.oasistropical.com", "Un lugar paradisíaco rodeado de naturaleza", "666666666"),
                new Hotel("Hotel Histórico Antiguo", "Calle de la Historia, 234", 3, "info@historicoantiguo.com", "www.historicoantiguo.com", "Una joya arquitectónica con encanto histórico", "444444444"),
                new Hotel("Hotel Spa Relax", "Camino del Descanso, 567", 5, "reservas@sparelax.com", "www.sparelax.com", "Especializado en ofrecer experiencias relajantes y rejuvenecedoras", "999999999"),
                new Hotel("Hotel para borrar", "Camino del Descanso, 567", 5, "reservas@sparelax.com", "www.sparelax.com", "Especializado en ofrecer experiencias relajantes y rejuvenecedoras", "999999999"),
                new Hotel("Hotel para editar", "Camino del Descanso, 567", 5, "reservas@sparelax.com", "www.sparelax.com", "Especializado en ofrecer experiencias relajantes y rejuvenecedoras", "999999999")
        );
        listaHoteles.forEach(verificarYPersistirHotel);
        return listaHoteles;
    }

    private List<Usuario> crearUsuarios() {
        //CREACCION DE USUARIOS
        List<Usuario> usuarios = List.of(
                new Usuario("Alvaro", "Bajo", "Tabero", "alvarobajo893@gmail.com", "12345678", "123456"),
                new Usuario("María", "Martínez", "Fernández", "maria.martinez@example.com", "23456789", "password2"),
                new Usuario("José", "Rodríguez", "Gómez", "jose.rodriguez@example.com", "34567890", "password3"),
                new Usuario("Ana", "López", "Sánchez", "ana.lopez@example.com", "45678901", "password4"),
                new Usuario("Carlos", "Pérez", "Ramírez", "carlos.perez@example.com", "56789012", "password5"),
                new Usuario("Laura", "Sánchez", "Jiménez", "laura.sanchez@example.com", "67890123", "password6"),
                new Usuario("Pedro", "Fernández", "Martínez", "pedro.fernandez@example.com", "78901234", "password7"),
                new Usuario("Isabel", "Gómez", "López", "isabel.gomez@example.com", "89012345", "password8"),
                new Usuario("Miguel", "Ramírez", "Sánchez", "miguel.ramirez@example.com", "90123456", "password9"),
                new Usuario("Elena", "Jiménez", "Martínez", "elena.jimenez@example.com", "01234567", "password10"),
                new Usuario("Francisco", "Sánchez", "Gómez", "francisco.sanchez@example.com", "11223344", "password11"),
                new Usuario("Sara", "López", "Fernández", "sara.lopez@example.com", "22334455", "password12")
        );
        usuarios.forEach(verificarYPersistirUsuario);
        return usuarios;
    }

    private Consumer<Usuario> verificarYPersistirUsuario = usuario ->
            usuarioDAO.findByUsername(usuario.getNombre())
                    .ifPresentOrElse(
                            existingUsuario -> {/* No hacer nada si ya existe */},
                            () -> usuarioDAO.save(usuario)
                    );

    private Consumer<Hotel> verificarYPersistirHotel = hotel ->
            hotelDAO.findByNombre(hotel.getNombre())
                    .ifPresentOrElse(
                            existingHotel -> {/* No hacer nada si ya existe */},
                            () -> hotelDAO.save(hotel)
                    );

    private Consumer<LuxuryStay> verificarYPersistirLuxuryStay = luxuryStay ->
            luxuryStayDAO.findByNombre(luxuryStay.getNombre())
                    .ifPresentOrElse(
                            existingLuxuryStay -> {/* No hacer nada si ya existe */},
                            () -> luxuryStayDAO.save(luxuryStay)
                    );
    private void persistirReservas(List<Reserva> reservas) {
        reservas.forEach(reservaDAO::save);
    }
    private void persistirFacturas(List<Factura> facturas) {
        facturas.forEach(facturaDAO::save);
    }
}

