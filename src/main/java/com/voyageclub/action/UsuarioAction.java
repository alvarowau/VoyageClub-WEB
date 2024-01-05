package com.voyageclub.action;

import com.voyageclub.model.Usuario;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.Optional;

/**
 * Interfaz que define las operaciones relacionadas con la entidad Usuario.
 * Extiende la interfaz genérica {@code Action} utilizando la clase {@code Usuario} como entidad
 * y {@code Long} como tipo de identificador.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface UsuarioAction extends Action<Usuario, Long> {




}
