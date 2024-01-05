package com.voyageclub.dao;

import com.voyageclub.model.Hotel;

import jakarta.enterprise.context.Dependent;
import jakarta.enterprise.inject.Default;
import jakarta.inject.Named;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

/**
 * Implementación de la interfaz {@link HotelDAO} que proporciona operaciones específicas
 * para la entidad {@link Hotel}.
 *
 * Esta implementación utiliza el mecanismo de inyección de dependencias de CDI
 * con las anotaciones {@link Named}, {@link Default}, y {@link Dependent}.
 *
 * <p>Esta clase implementa métodos para acceder y manipular datos relacionados con hoteles
 * en la base de datos. Incluye operaciones como obtener todos los hoteles, buscar hoteles por nombre y código,
 * así como operaciones para guardar y recuperar imágenes principales y secundarias de los hoteles.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Named
@Default
@Dependent
public class HotelDAOImpl extends BaseDAO<Hotel, Long> implements HotelDAO {

    /**
     * Constructor que recibe la clase de la entidad como parámetro.
     */
    public HotelDAOImpl() {
        super(Hotel.class);
    }

    /**
     * Obtiene todas los hoteles almacenados en la base de datos.
     *
     * @return Lista que contiene todos los hoteles.
     */
    @Override
    public List<Hotel> getAll() {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel h", Hotel.class);
        return query.getResultList();
    }

    /**
     * Busca un hotel por su nombre.
     *
     * @param nombre El nombre del hotel a buscar.
     * @return Un {@link Optional} que contiene el hotel si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Hotel> findByNombre(String nombre) {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.nombre = :nombre", Hotel.class);
        query.setParameter("nombre", nombre);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Busca un hotel por su código.
     *
     * @param codigoHotel El código del hotel a buscar.
     * @return Un {@link Optional} que contiene el hotel si se encuentra, o vacío si no.
     */
    @Override
    public Optional<Hotel> findByCodigoHotel(String codigoHotel) {
        TypedQuery<Hotel> query = entityManager.createQuery("SELECT h FROM Hotel h WHERE h.codigoHotel = :codigoHotel", Hotel.class);
        query.setParameter("codigoHotel", codigoHotel);
        return query.getResultList().stream().findFirst();
    }

    /**
     * Guarda la imagen principal de un hotel.
     *
     * @param hotelId          Identificador único del hotel.
     * @param imagenPrincipal  Bytes de la imagen principal.
     */
    @Override
    public void saveImagePrincipal(Long hotelId, byte[] imagenPrincipal) {
        Hotel hotel = getById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado"));
        hotel.setImagenPrincipal(imagenPrincipal);
        update(hotel);
    }

    /**
     * Guarda la imagen secundaria de un hotel.
     *
     * @param hotelId           Identificador único del hotel.
     * @param imagenSecundaria  Bytes de la imagen secundaria.
     */
    @Override
    public void saveImageSecundaria(Long hotelId, byte[] imagenSecundaria) {
        Hotel hotel = getById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado"));
        hotel.setImagenSecundaria(imagenSecundaria);
        update(hotel);
    }

    /**
     * Obtiene la imagen principal de un hotel.
     *
     * @param hotelId Identificador único del hotel.
     * @return Un {@link Optional} que contiene los bytes de la imagen principal si se encuentra, o vacío si no.
     */
    @Override
    public Optional<byte[]> getImagePrincipal(Long hotelId) {
        Hotel hotel = getById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado"));
        return Optional.ofNullable(hotel.getImagenPrincipal());
    }

    /**
     * Obtiene la imagen secundaria de un hotel.
     *
     * @param hotelId Identificador único del hotel.
     * @return Un {@link Optional} que contiene los bytes de la imagen secundaria si se encuentra, o vacío si no.
     */
    @Override
    public Optional<byte[]> getImageSecundaria(Long hotelId) {
        Hotel hotel = getById(hotelId).orElseThrow(() -> new IllegalArgumentException("Hotel no encontrado"));
        return Optional.ofNullable(hotel.getImagenSecundaria());
    }
}
