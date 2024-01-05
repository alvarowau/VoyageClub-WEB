package com.voyageclub.action;

import com.voyageclub.model.Hotel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interfaz que define las operaciones relacionadas con la entidad Hotel.
 * Extiende la interfaz genérica {@code Action} utilizando la clase {@code Hotel} como entidad
 * y {@code Long} como tipo de identificador.
 *
 * @author [Tu Nombre]
 * @version 1.0
 */
public interface HotelAction extends Action<Hotel, Long> {

    String listarTodosHoteles(HttpServletRequest request, HttpServletResponse response);
    String eliminarHotel(HttpServletRequest request, HttpServletResponse response);
    String dirigeModifcar(HttpServletRequest request, HttpServletResponse response);
    String crearHotel(HttpServletRequest request, HttpServletResponse response);

    String modificarHotel(HttpServletRequest request, HttpServletResponse response);
}
