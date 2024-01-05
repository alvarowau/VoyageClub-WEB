package com.voyageclub.dao;

import com.voyageclub.model.BaseEntity;

import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * Esta interfaz proporciona métodos para realizar operaciones CRUD (Create, Read, Update, Delete)
 * en la base de datos para entidades que extienden {@link BaseEntity}.
 *
 * @param <T> Tipo de entidad que extiende {@link BaseEntity}.
 * @param <ID> Tipo de identificador único serializable asociado a la entidad.
 *
 * <p>La interfaz define métodos para recuperar, guardar, actualizar y eliminar entidades en la base de datos.
 * Además, incluye un método para establecer el {@link EntityManager} que se utilizará para realizar operaciones
 * en la base de datos.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public interface DAO<T extends BaseEntity, ID extends Serializable> {

    /**
     * Recupera una entidad específica mediante su identificador único.
     *
     * @param id Identificador único de la entidad.
     * @return La entidad correspondiente al identificador proporcionado,
     *         o {@code null} si no se encuentra ninguna entidad asociada al identificador.
     */
    Optional<T> getById(ID id);

    /**
     * Recupera todas las entidades del tipo especificado almacenadas en la base de datos.
     *
     * @return Una lista que contiene todas las entidades del tipo {@code T}.
     */
    List<T> getAll();

    /**
     * Guarda una nueva entidad en la base de datos.
     *
     * @param entity Entidad que se va a guardar en la base de datos.
     */
    void save(T entity);

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param entity Entidad que se va a actualizar en la base de datos.
     */
    void update(T entity);

    /**
     * Elimina una entidad de la base de datos mediante su identificador único.
     *
     * @param id Identificador único de la entidad que se va a eliminar.
     */
    void delete(ID id);

    /**
     * Establece el {@link EntityManager} que se utilizará para realizar operaciones en la base de datos.
     *
     * @param entityManager {@link EntityManager} que se va a establecer.
     */
    void setEntityManager(EntityManager entityManager);
}
