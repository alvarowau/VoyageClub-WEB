package com.voyageclub.dao;

import com.voyageclub.model.Hotel;

import java.util.Optional;

/**
 * Interfaz que extiende la interfaz genérica {@link DAO} para la entidad {@link Hotel}.
 * Proporciona métodos adicionales específicos para operaciones relacionadas con hoteles.
 *
 * <p>Esta interfaz define métodos adicionales para el acceso a datos y manipulación de hoteles
 * en la base de datos. Incluye operaciones como buscar hoteles por nombre y código, así como
 * operaciones para guardar y recuperar imágenes principales y secundarias de los hoteles.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface HotelDAO extends DAO<Hotel, Long> {

    /**
     * Busca un hotel por su nombre.
     *
     * @param nombre El nombre del hotel a buscar.
     * @return Un {@link Optional} que contiene el hotel si se encuentra, o vacío si no.
     */
    Optional<Hotel> findByNombre(String nombre);

    /**
     * Busca un hotel por su código.
     *
     * @param codigoHotel El código del hotel a buscar.
     * @return Un {@link Optional} que contiene el hotel si se encuentra, o vacío si no.
     */
    Optional<Hotel> findByCodigoHotel(String codigoHotel);

    /**
     * Guarda la imagen principal de un hotel.
     *
     * @param hotelId          Identificador único del hotel.
     * @param imagenPrincipal  Bytes de la imagen principal.
     */
    void saveImagePrincipal(Long hotelId, byte[] imagenPrincipal);

    /**
     * Guarda la imagen secundaria de un hotel.
     *
     * @param hotelId           Identificador único del hotel.
     * @param imagenSecundaria  Bytes de la imagen secundaria.
     */
    void saveImageSecundaria(Long hotelId, byte[] imagenSecundaria);

    /**
     * Obtiene la imagen principal de un hotel.
     *
     * @param hotelId Identificador único del hotel.
     * @return Un {@link Optional} que contiene los bytes de la imagen principal si se encuentra, o vacío si no.
     */
    Optional<byte[]> getImagePrincipal(Long hotelId);

    /**
     * Obtiene la imagen secundaria de un hotel.
     *
     * @param hotelId Identificador único del hotel.
     * @return Un {@link Optional} que contiene los bytes de la imagen secundaria si se encuentra, o vacío si no.
     */
    Optional<byte[]> getImageSecundaria(Long hotelId);
}
