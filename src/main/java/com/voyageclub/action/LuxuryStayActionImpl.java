package com.voyageclub.action;

import com.voyageclub.dao.LuxuryStayDAO;
import com.voyageclub.model.LuxuryStay;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Implementación de la interfaz {@link LuxuryStayAction} que define las acciones relacionadas con las estancias de lujo.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public class LuxuryStayActionImpl implements LuxuryStayAction {

    /**
     * DAO para acceder a las operaciones de la entidad LuxuryStay en la base de datos.
     */
    @Inject
    private LuxuryStayDAO luxuryStayDAO;

    /**
     * Ejecuta la acción relacionada con las estancias de lujo.
     *
     * @param request  Objeto HttpServletRequest que contiene la solicitud HTTP.
     * @param response Objeto HttpServletResponse que contiene la respuesta HTTP.
     * @return La ruta de la página de destino después de ejecutar la acción.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("ACTION");
        String pagDestino = "";

        switch (action) {
            case "LISTAR":
                pagDestino = listar(request, response);
                break;
            // Agregar más casos según sea necesario
            default:
                pagDestino = "/error.jsp";
                break;
        }

        return pagDestino;
    }

    /**
     * Lista todas las estancias de lujo y muestra la información en una página.
     *
     * @param request  Objeto HttpServletRequest que contiene la solicitud HTTP.
     * @param response Objeto HttpServletResponse que contiene la respuesta HTTP.
     * @return La ruta de la página de destino después de listar las estancias de lujo.
     */
    private String listar(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obtener todas las estancias de lujo desde la base de datos
            List<LuxuryStay> luxuryStays = luxuryStayDAO.getAll();

            // Almacenar la lista en el request para que la JSP pueda acceder a ella
            request.setAttribute("LUXURY_STAYS", luxuryStays);

            // Devolver la página de destino para mostrar la lista de estancias de lujo
            return "/luxury_stays/listar.jsp";
        } catch (Exception e) {
            // Manejar la excepción de manera adecuada (por ejemplo, loggearla)
            e.printStackTrace();

            // Redirigir a una página de error en caso de excepción
            return "/error.jsp";
        }
    }
}
