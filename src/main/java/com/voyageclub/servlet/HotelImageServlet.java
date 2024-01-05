package com.voyageclub.servlet;

import com.voyageclub.dao.HotelDAO;
import com.voyageclub.model.Hotel;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

/**
 * El servlet para obtener imágenes de hoteles.
 * Este servlet maneja solicitudes GET para obtener imágenes de hoteles según el tipo de imagen especificado (principal o secundaria) y el ID del hotel.
 * Requiere la inyección de un HotelDAO para acceder a la base de datos.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@WebServlet(name = "HotelImageServlet", urlPatterns = { "/hotelImage" })
public class HotelImageServlet extends HttpServlet {

    @Inject
    private HotelDAO hotelDAO;

    /**
     * Maneja las solicitudes GET para obtener imágenes de hoteles.
     *
     * @param request  La solicitud HTTP.
     * @param response La respuesta HTTP que contendrá la imagen.
     * @throws ServletException Si ocurre un error de servlet.
     * @throws IOException      Si ocurre un error de entrada/salida.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener parámetros del request
        String hotelIdParam = request.getParameter("hotelId");
        String type = request.getParameter("type");

        if (hotelIdParam != null && type != null) {
            try {
                // Convertir el hotelId a Long
                Long hotelId = Long.parseLong(hotelIdParam);

                // Obtener el hotel desde la base de datos
                Optional<Hotel> hotelOptional = hotelDAO.getById(hotelId);

                if (hotelOptional.isPresent()) {
                    Hotel hotel = hotelOptional.get();

                    // Determinar qué tipo de imagen cargar
                    byte[] imageBytes;
                    if ("principal".equals(type)) {
                        imageBytes = hotel.getImagenPrincipal();
                    } else if ("secundaria".equals(type)) {
                        imageBytes = hotel.getImagenSecundaria();
                    } else {
                        // Tipo de imagen no válido
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                        return;
                    }

                    // Configurar el tipo de contenido de la respuesta
                    response.setContentType("image/jpeg");

                    // Escribir la imagen al ServletOutputStream de la respuesta
                    try (ServletOutputStream outputStream = response.getOutputStream()) {
                        outputStream.write(imageBytes);
                    }
                } else {
                    // Hotel no encontrado
                    response.sendError(HttpServletResponse.SC_NOT_FOUND);
                }
            } catch (NumberFormatException e) {
                // hotelId no es un número válido
                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            }
        } else {
            // Parámetros incompletos
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
