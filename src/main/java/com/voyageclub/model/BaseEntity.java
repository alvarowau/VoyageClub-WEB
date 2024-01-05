package com.voyageclub.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

/**
 * Clase base abstracta que define un identificador único para todas las entidades.
 * Esta clase utiliza anotaciones de Jakarta Persistence para mapear propiedades comunes en la base de datos.
 * Las entidades concretas deben extender esta clase para heredar la funcionalidad común.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
@MappedSuperclass
public abstract class BaseEntity {

    /**
     * Identificador único de la entidad, generado automáticamente por la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Obtiene el identificador único de la entidad.
     *
     * @return El identificador único de la entidad.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el identificador único de la entidad.
     *
     * @param id El nuevo identificador único de la entidad.
     */
    public void setId(Long id) {
        this.id = id;
    }
}
