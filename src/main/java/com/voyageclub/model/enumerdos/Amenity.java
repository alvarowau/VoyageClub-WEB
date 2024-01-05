package com.voyageclub.model.enumerdos;

/**
 * Enumeración que representa las amenidades disponibles para una estancia de lujo.
 * Cada amenidad tiene una etiqueta y una descripción asociadas para proporcionar información detallada.
 *
 * Las amenidades incluyen servicios como spa, limpieza diaria, Wi-Fi, desayuno, estacionamiento, piscina, gimnasio, etc.
 *
 * @author Álvaro Bajo
 * @version 1.0
 */
public enum Amenity {

    /**
     * Servicio de spa para relajación y rejuvenecimiento.
     */
    SPA("Spa", "Disfruta de nuestros servicios de spa para relajarte y rejuvenecer."),

    /**
     * Jacuzzi para un relajante baño de burbujas.
     */
    JACUZZI("Jacuzzi", "Disfruta de un relajante baño de burbujas en nuestro jacuzzi."),

    // ... Otras amenidades ...

    /**
     * Servicio de limpieza diario para mantener el espacio impecable.
     */
    LIMPIEZA("Servicio de limpieza", "Ofrecemos un servicio de limpieza diario para mantener tu espacio impecable."),

    /**
     * Conexión a Internet de alta velocidad en cualquier momento.
     */
    WIFI("Wi-Fi", "Conéctate a Internet de alta velocidad en cualquier momento."),

    /**
     * Desayuno incluido para comenzar el día con energía.
     */
    DESAYUNO("Desayuno incluido", "Comienza tu día con un delicioso desayuno gratuito."),

    /**
     * Estacionamiento seguro para tu comodidad.
     */
    ESTACIONAMIENTO("Estacionamiento", "Proporcionamos estacionamiento seguro para tu comodidad."),

    /**
     * Amplia piscina para relajarte y refrescarte.
     */
    PISCINA("Piscina", "Relájate y refréscate en nuestra amplia piscina."),

    /**
     * Moderno gimnasio para mantener tu rutina de ejercicios.
     */
    GIMNASIO("Gimnasio", "Mantén tu rutina de ejercicios en nuestro moderno gimnasio."),

    /**
     * Servicio a la habitación para solicitar alimentos y bebidas directamente a tu habitación.
     */
    SERVICIO_HABITACION("Servicio a la habitación", "Solicita alimentos y bebidas directamente a tu habitación."),

    /**
     * Admite mascotas, bienvenidas a huéspedes peludos.
     */
    MASCOTAS("Admite mascotas", "Bienvenidos a huéspedes peludos. Aceptamos mascotas en nuestras instalaciones."),

    /**
     * Amplia selección de canales en tu televisión.
     */
    TELEVISION("Televisión", "Disfruta de una amplia selección de canales en tu televisión."),

    /**
     * Aire acondicionado para mantener la temperatura perfecta en tu habitación.
     */
    AIRE_ACONDICIONADO("Aire acondicionado", "Mantén la temperatura perfecta en tu habitación."),

    /**
     * Minibar con tus bebidas favoritas en tu habitación.
     */
    MINIBAR("Minibar", "Encuentra tus bebidas favoritas en el minibar de tu habitación."),

    /**
     * Impresionantes vistas al mar desde tu habitación.
     */
    VISTAMAR("Vista al mar", "Disfruta de impresionantes vistas al mar desde tu habitación."),

    /**
     * Servicio de concierge listo para ayudarte con cualquier solicitud.
     */
    SERVICIO_CONCIERGE("Servicio de concierge", "Nuestro equipo está listo para ayudarte con cualquier solicitud."),

    /**
     * Zona de relajación para encontrar un espacio tranquilo y desconectar.
     */
    ZONA_RELAJACION("Zona de relajación", "Encuentra un espacio tranquilo para descansar y desconectar."),

    /**
     * Restaurante en el lugar para descubrir deliciosas opciones gastronómicas sin salir del hotel.
     */
    RESTAURANTE("Restaurante en el lugar", "Descubre deliciosas opciones gastronómicas sin salir del hotel."),

    /**
     * Centro de negocios con lo necesario para tu trabajo.
     */
    CENTRO_NEGOCIOS("Centro de negocios", "Si estás aquí por trabajo, nuestro centro de negocios tiene lo que necesitas."),

    /**
     * Personal de seguridad disponible las 24 horas para tu seguridad.
     */
    SEGURIDAD("Seguridad 24/7", "Tu seguridad es nuestra prioridad, con personal disponible las 24 horas."),

    /**
     * Salones para eventos que ofrecen espacios versátiles para tus reuniones y eventos.
     */
    SALONES_EVENTOS("Salones para eventos", "Organiza reuniones y eventos en nuestros espacios versátiles.");

    private final String etiqueta;
    private final String descripcion;

    /**
     * Constructor privado que asigna una etiqueta y una descripción a cada amenidad.
     *
     * @param etiqueta    Etiqueta asociada a la amenidad.
     * @param descripcion Descripción detallada de la amenidad.
     */
    Amenity(String etiqueta, String descripcion) {
        this.etiqueta = etiqueta;
        this.descripcion = descripcion;
    }

    /**
     * Obtiene la etiqueta asociada a la amenidad.
     *
     * @return Etiqueta de la amenidad.
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * Obtiene la descripción detallada de la amenidad.
     *
     * @return Descripción de la amenidad.
     */
    public String getDescripcion() {
        return descripcion;
    }
}

