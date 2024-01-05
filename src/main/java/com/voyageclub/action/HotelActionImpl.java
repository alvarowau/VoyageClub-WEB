package com.voyageclub.action;

import com.voyageclub.dao.HotelDAO;
import com.voyageclub.model.Hotel;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class HotelActionImpl implements HotelAction {

    @Inject
    private HotelDAO hotelDAO;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String pagDestino ="";
        String action = request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        switch(arrayAction[1]){
            case "FIND_ALL":
                pagDestino = listarTodosHoteles(request, response);
                break;
            // Agregar más casos según sea necesario
            case "DIRIGENEW":
                pagDestino = "/crearhotel.jsp";
                break;
            case "ELIMINAR":
                pagDestino = eliminarHotel(request,response);
                break;
            case "DIRIGEMODIFICAR":
                pagDestino = dirigeModifcar(request, response);
                break;
            case "MODIFICAR":
                pagDestino = modificarHotel(request,response);
                break;
            case "CREAR":
                pagDestino = crearHotel(request, response);
                break;
            default:
                // Manejar el caso cuando no coincide con ningún caso
                pagDestino = "/error.jsp";
                break;
        }
        return pagDestino;
    }
    @Override
    public String dirigeModifcar(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("HOTELID"));
        System.out.println("el id es: " + id);
       try{
           Optional<Hotel> hotelOptional = hotelDAO.getById(id);
           if(hotelOptional.isPresent()){
               Hotel hotel = hotelOptional.get();
               request.setAttribute("hotel", hotel);
               return "/crearhotel.jsp";
           }else{
               //recargo configuracion hotel con todas lasllistas
               List<Hotel> hotel = hotelDAO.getAll();
               request.setAttribute("MENSAJE_HOTEL", "Estamos teniendo problemas, intentelo de nuevo");
               request.setAttribute("LISTA_HOTELES", hotel);
               return "/configuracionhotel.jsp";
           }
       }catch (Exception e){
           e.printStackTrace();
           System.out.println(e.toString());

           // Redirigir a una página de error
           return "error.jsp";

       }

    }
    @Override
    public String eliminarHotel(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("HOTELID"));
        System.out.println("estoy en nuevo y el id para eliminar es:" +id);
        List<Hotel> hotel = hotelDAO.getAll();
        request.setAttribute("LISTA_HOTELES", hotel);
        return "/configuracionhotel.jsp";
    }

    @Override
    public String crearHotel(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obtener los datos del formulario
            String nombre = request.getParameter("NOMBRE").trim();
            String direccion = request.getParameter("DIRECCION").trim();
            String estrellasTxt = request.getParameter("ESTRELLAS");
            String email = request.getParameter("EMAIL").trim();
            String web = request.getParameter("WEB").trim();
            String descripcion = request.getParameter("DESCRIPCION").trim();
            String telefono = request.getParameter("TELEFONO").trim();
            Part imagenPrincipalPart = request.getPart("IMAGENPRINCIPAL");
            Part imagenSecundariaPart = request.getPart("IMAGENSECUNDARIA");

            // Validar que el nombre tenga al menos 3 caracteres
            if (nombre.length() < 3) {
                request.setAttribute("ERROR_NOMBRE", "El nombre debe tener al menos 3 caracteres");
                return "/crearhotel.jsp";
            }

            // Validar que la descripcion tenga al menos 3 caracteres
            if (descripcion.length() < 3) {
                request.setAttribute("ERROR_DESCRIPCION", "La descripción debe tener al menos 3 caracteres");
                return "/crearhotel.jsp";
            }

            // Validar que la dirección tenga al menos 3 caracteres
            if (direccion.length() < 3) {
                request.setAttribute("ERROR_DIRECCION", "La dirección debe tener al menos 3 caracteres");
                return "/crearhotel.jsp";
            }

            // Validar que el teléfono no esté vacío
            if (telefono.isEmpty()) {
                request.setAttribute("ERROR_TELEFONO", "El teléfono no puede estar vacío");
                return "/crearhotel.jsp";
            }

            // Validar que la web no esté vacía
            if (web.isEmpty()) {
                request.setAttribute("ERROR_WEB", "La web no puede estar vacía");
                return "/crearhotel.jsp";
            }

            // Validar que el email no esté vacío y sea un email válido
            if (email.isEmpty() || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                request.setAttribute("ERROR_EMAIL", "Ingrese un correo electrónico válido");
                return "/crearhotel.jsp";
            }

            // Validar que al menos exista una imagen principal y una secundaria
            if ((imagenPrincipalPart == null || imagenPrincipalPart.getSize() <= 0) ||
                    (imagenSecundariaPart == null || imagenSecundariaPart.getSize() <= 0)) {
                request.setAttribute("ERROR_IMAGENES", "Debe adjuntar al menos una imagen principal y una imagen secundaria");
                return "/crearhotel.jsp";
            }

            // Convertir las imágenes a bytes
            byte[] imagenPrincipalBytes = obtenerBytesDesdePart(imagenPrincipalPart);
            byte[] imagenSecundariaBytes = obtenerBytesDesdePart(imagenSecundariaPart);

            // Convertir el valor de estrellas a un tipo numérico (int)
            int estrellas = 0;
            try {
                estrellas = Integer.parseInt(estrellasTxt);
            } catch (NumberFormatException e) {
                request.setAttribute("ERROR_ESTRELLAS", "Ingrese un valor numérico para las estrellas");
                return "/crearhotel.jsp";
            }

            // Si llegamos aquí, es porque todas las validaciones han pasado
            Hotel hoGuardar = new Hotel(nombre, direccion, estrellas, email, web, descripcion,
                    telefono, imagenSecundariaBytes, imagenPrincipalBytes);
            hotelDAO.save(hoGuardar);
            return listarTodosHoteles(request, response);

        } catch (IOException | ServletException e) {
            e.printStackTrace();
            // Manejar cualquier excepción al procesar la solicitud
            request.setAttribute("ERROR_GENERAL", "Error al procesar la solicitud");
            return "/error.jsp";
        }
    }



    @Override
    public String modificarHotel(HttpServletRequest request, HttpServletResponse response) {
        try {
            // Obtener el ID del hotel a modificar
            Long id = Long.parseLong(request.getParameter("HOTELID"));

            // Obtener el hotel desde la base de datos
            Optional<Hotel> hotelOptional = hotelDAO.getById(id);

            if (hotelOptional.isPresent()) {
                Hotel hotelModificado = hotelOptional.get();

                // Actualizar los campos del hotel con los nuevos datos del formulario
                hotelModificado.setNombre(request.getParameter("NOMBRE"));
                hotelModificado.setDireccion(request.getParameter("DIRECCION"));
                hotelModificado.setEstrellas(Integer.parseInt(request.getParameter("ESTRELLAS")));
                hotelModificado.setEmail(request.getParameter("EMAIL"));
                hotelModificado.setWeb(request.getParameter("WEB"));
                hotelModificado.setDescripcion(request.getParameter("DESCRIPCION"));
                hotelModificado.setTelefono(request.getParameter("TELEFONO"));

                // Obtener las URL para las imágenes desde el servlet HotelImageServlet
                String imagenPrincipalUrl = request.getContextPath() + "/hotelImage?hotelId=" + hotelModificado.getId() + "&type=principal";
                String imagenSecundariaUrl = request.getContextPath() + "/hotelImage?hotelId=" + hotelModificado.getId() + "&type=secundaria";

                // Establecer las URL en el request para usar en el JSP
                request.setAttribute("imagenPrincipalUrl", imagenPrincipalUrl);
                request.setAttribute("imagenSecundariaUrl", imagenSecundariaUrl);

                // Verificar si se adjuntaron nuevas imágenes
                Part imagenPrincipalPart = request.getPart("IMAGENPRINCIPAL");
                Part imagenSecundariaPart = request.getPart("IMAGENSECUNDARIA");

                String imagenPrincipalActual = request.getParameter("IMAGENPRINCIPAL_ACTUAL");
                String imagenSecundariaActual = request.getParameter("IMAGENSECUNDARIA_ACTUAL");

                if (imagenPrincipalPart != null && imagenPrincipalPart.getSize() > 0 &&
                        imagenSecundariaPart != null && imagenSecundariaPart.getSize() > 0) {
                    // El usuario ha seleccionado nuevas imágenes
                    byte[] nuevaImagenPrincipalBytes = obtenerBytesDesdePart(imagenPrincipalPart);
                    byte[] nuevaImagenSecundariaBytes = obtenerBytesDesdePart(imagenSecundariaPart);

                    hotelModificado.setImagenPrincipal(nuevaImagenPrincipalBytes);
                    hotelModificado.setImagenSecundaria(nuevaImagenSecundariaBytes);
                    // Guardar las nuevas imágenes
                } else if (imagenPrincipalActual != null && !imagenPrincipalActual.isEmpty() &&
                        imagenSecundariaActual != null && !imagenSecundariaActual.isEmpty()) {
                    // El usuario no ha seleccionado nuevas imágenes, pero hay imágenes actuales
                    // Mantener las imágenes actuales (imagenPrincipalActual e imagenSecundariaActual)
                    hotelModificado.setImagenPrincipal(Base64.getDecoder().decode(imagenPrincipalActual));
                    hotelModificado.setImagenSecundaria(Base64.getDecoder().decode(imagenSecundariaActual));
                } else {
                    // No hay imágenes actuales ni nuevas imágenes
                    request.setAttribute("ERROR_IMAGENES", "Debe adjuntar al menos una imagen principal y una imagen secundaria");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que el nombre tenga al menos 3 caracteres
                if (hotelModificado.getNombre() == null || hotelModificado.getNombre().trim().length() < 3) {
                    request.setAttribute("ERROR_NOMBRE", "El nombre debe tener al menos 3 caracteres");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que la descripción tenga al menos 3 caracteres
                if (hotelModificado.getDescripcion() == null || hotelModificado.getDescripcion().trim().length() < 3) {
                    request.setAttribute("ERROR_DESCRIPCION", "La descripción debe tener al menos 3 caracteres");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que la dirección tenga al menos 3 caracteres
                if (hotelModificado.getDireccion() == null || hotelModificado.getDireccion().trim().length() < 3) {
                    request.setAttribute("ERROR_DIRECCION", "La dirección debe tener al menos 3 caracteres");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que el teléfono no esté vacío
                if (hotelModificado.getTelefono() == null || hotelModificado.getTelefono().trim().isEmpty()) {
                    request.setAttribute("ERROR_TELEFONO", "El teléfono no puede estar vacío");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que la web no esté vacía
                if (hotelModificado.getWeb() == null || hotelModificado.getWeb().trim().isEmpty()) {
                    request.setAttribute("ERROR_WEB", "La web no puede estar vacía");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Validar que el email no esté vacío y sea un email válido (puedes agregar una validación más robusta)
                if (hotelModificado.getEmail() == null || hotelModificado.getEmail().trim().isEmpty() || !hotelModificado.getEmail().contains("@")) {
                    request.setAttribute("ERROR_EMAIL", "Ingrese un correo electrónico válido");
                    request.setAttribute("hotel", hotelModificado);
                    return "/crearhotel.jsp";
                }

                // Resto de validaciones aquí
                hotelDAO.update(hotelModificado);
                request.setAttribute("hotel", hotelModificado);

                // Redirigir al formulario de modificación
                return "/crearhotel.jsp";
            } else {
                request.setAttribute("ERROR", "Estamos teniendo problemas con la base de datos intentelo mas tarde");
                return "/error.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("ERROR", "No hemos podido procesar su solicitud");
            return "/error.jsp";
        }
    }



    // Método para obtener bytes desde un objeto Part
    private byte[] obtenerBytesDesdePart(Part part) throws IOException {
        InputStream input = part.getInputStream();
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;

        while ((length = input.read(buffer)) != -1) {
            output.write(buffer, 0, length);
        }

        return output.toByteArray();
    }




    @Override
    public String listarTodosHoteles(HttpServletRequest request, HttpServletResponse response) {
        List<Hotel> hotel = hotelDAO.getAll();
        request.setAttribute("LISTA_HOTELES", hotel);
        return "/configuracionhotel.jsp";
    }
}
