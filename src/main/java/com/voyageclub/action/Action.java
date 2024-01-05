package com.voyageclub.action;

import com.voyageclub.model.BaseEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Interfaz genérica para definir acciones comunes en la aplicación.
 *
 * @param <T> Tipo de entidad asociada a la acción.
 * @param <ID> Tipo del identificador único de la entidad.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface Action<T extends BaseEntity, ID extends Serializable> {
    /**
     * Ejecuta la acción correspondiente.
     *
     * @param request  La solicitud HTTP recibida.
     * @param response La respuesta HTTP que se enviará.
     * @return La página de destino a la que se redirigirá después de ejecutar la acción.
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
