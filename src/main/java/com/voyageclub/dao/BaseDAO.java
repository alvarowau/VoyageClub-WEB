package com.voyageclub.dao;

import com.voyageclub.model.BaseEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

/**
 * Implementación base de la interfaz {@link DAO} que proporciona operaciones CRUD
 * para entidades que extienden {@link BaseEntity}.
 *
 * @param <T> Tipo de entidad que extiende {@link BaseEntity}.
 * @param <ID> Tipo de identificador único serializable asociado a la entidad.
 *
 * <p>Esta implementación utiliza el mecanismo de inyección de dependencias de CDI con las anotaciones {@link PersistenceContext},
 * {@link Transactional}, y {@link EntityManager}. Proporciona operaciones CRUD estándar para entidades que extienden
 * {@link BaseEntity}, permitiendo recuperar, guardar, actualizar y eliminar entidades en la base de datos. Además,
 * proporciona la capacidad de establecer el {@link EntityManager} que se utilizará para realizar operaciones en la base
 * de datos.
 *
 * <p>La clase utiliza el nombre de unidad de persistencia "voyageclub-persistence-unit". Si este nombre no es adecuado para
 * tu configuración, asegúrate de ajustarlo en la anotación {@link PersistenceContext}.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@Transactional
public class BaseDAO<T extends BaseEntity, ID extends Serializable> implements DAO<T, ID> {

    /**
     * El {@link EntityManager} utilizado para realizar operaciones en la base de datos.
     */
    @PersistenceContext(unitName = "voyageclub-persistence-unit")
    protected EntityManager entityManager;

    /**
     * Clase de la entidad gestionada por este DAO.
     */
    private final Class<T> entityClass;

    /**
     * Constructor que recibe la clase de la entidad como parámetro.
     *
     * @param entityClass Clase de la entidad gestionada por este DAO.
     */
    public BaseDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Constructor por defecto que inicializa la clase de la entidad mediante reflexión.
     */
    @SuppressWarnings("unchecked")
    public BaseDAO() {
        this.entityClass = extractEntityClass();
    }

    private Class<T> extractEntityClass() {
        Type genericSuperclass = findGenericSuperclass(getClass());
        ParameterizedType paramType = (ParameterizedType) genericSuperclass;
        Type[] typeArguments = paramType.getActualTypeArguments();

        if (typeArguments.length > 0 && typeArguments[0] instanceof Class) {
            return (Class<T>) typeArguments[0];
        } else {
            throw new IllegalStateException("Unable to extract entity class. Please provide it explicitly.");
        }
    }

    private static Type findGenericSuperclass(Class<?> clazz) {
        Type genericSuperclass = clazz.getGenericSuperclass();
        while (!(genericSuperclass instanceof ParameterizedType)) {
            if (genericSuperclass instanceof Class) {
                // In case a type is not parameterized (e.g., raw type), move up the hierarchy
                genericSuperclass = ((Class<?>) genericSuperclass).getGenericSuperclass();
            } else {
                throw new IllegalStateException("Unable to extract entity class. Please provide it explicitly.");
            }
        }
        return genericSuperclass;
    }

    /**
     * Recupera una entidad específica mediante su identificador único.
     *
     * @param id Identificador único de la entidad.
     * @return La entidad correspondiente al identificador proporcionado,
     *         o {@code null} si no se encuentra ninguna entidad asociada al identificador.
     */
    @Override
    public Optional<T> getById(ID id) {
        return Optional.ofNullable(entityManager.find(entityClass, id));
    }

    /**
     * Recupera todas las entidades del tipo especificado almacenadas en la base de datos.
     *
     * @return Una lista que contiene todas las entidades del tipo {@code T}.
     */
    @Override
    public List<T> getAll() {
        return entityManager.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }

    /**
     * Guarda una nueva entidad en la base de datos.
     *
     * @param entity Entidad que se va a guardar en la base de datos.
     */
    @Override
    public void save(T entity) {
        entityManager.persist(entity);
    }

    /**
     * Actualiza una entidad existente en la base de datos.
     *
     * @param entity Entidad que se va a actualizar en la base de datos.
     */
    @Override
    public void update(T entity) {
        entityManager.merge(entity);
    }

    /**
     * Elimina una entidad de la base de datos mediante su identificador único.
     *
     * @param id Identificador único de la entidad que se va a eliminar.
     */
    @Override
    public void delete(ID id) {
        T entity = entityManager.getReference(entityClass, id);
        entityManager.remove(entity);
    }

    /**
     * Establece el {@link EntityManager} que se utilizará para realizar operaciones en la base de datos.
     *
     * @param entityManager {@link EntityManager} que se va a establecer.
     */
    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
