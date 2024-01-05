package com.voyageclub.servlet;

import com.voyageclub.action.*;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collection;
import java.util.List;


@WebServlet(name = "app", value = "/app")
@MultipartConfig
public class FrontControllerServlet extends HttpServlet {

    @Inject
    private InicializacionDatosAction inicializacionDatosAction;
    @Inject
    private UsuarioActionImpl usuarioAction;

    @Inject
    private HotelActionImpl hotelAction;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Inicializar datos primero
       //inicializacionDatosAction.inicializarDatos();

        // Redirigir a login.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
        dispatcher.forward(request, response);

        // Configurar el servlet para manejar datos de formulario multipartes
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
            processMultipartRequest(request);
        } else {
            // Procesar la solicitud estándar
            processRequest(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Procesar la solicitud
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("esto es lo que llega: "+request.getParameter("ACTION"));
        String action = request.getParameter("ACTION");
        String[] arrayAction = action.split("\\.");
        String pagDestino = "";

        switch (arrayAction[0]) {
            case "USUARIO":
                pagDestino = usuarioAction.execute(request, response);
                break;
            // Agregar más casos según sea necesario
            case "HOTEL":
                pagDestino = hotelAction.execute(request, response);
                break;
            default:
                // Manejar el caso cuando no coincide con ningún caso
                pagDestino = "/error.jsp";
                break;
        }

        // Verificar que pagDestino no esté vacío o nulo antes de forward
        if (pagDestino != null && !pagDestino.isEmpty()) {
            try {
                request.getRequestDispatcher(pagDestino).forward(request, response);
            } catch (Exception e) {
                // Manejar la excepción en caso de un error durante el forward
                e.printStackTrace(); // Puedes cambiar esto según tus necesidades
            }
        } else {
            // Manejar el caso cuando pagDestino es vacío o nulo
            response.sendRedirect("/error.jsp");
        }
    }


    private void handleFacturaRequest(HttpServletRequest request, HttpServletResponse response, String[] pathParts) throws ServletException, IOException {
        // Implementa lógica para manejar solicitudes relacionadas con facturas
    }

    private void handleHotelRequest(HttpServletRequest request, HttpServletResponse response, String[] pathParts) throws ServletException, IOException {
        // Implementa lógica para manejar solicitudes relacionadas con hoteles
    }

    protected void processMultipartRequest(HttpServletRequest request) {
        try {
            // Obtener la lista de partes de la solicitud multipartes
            Collection<Part> fileParts = request.getParts();

            // Iterar sobre las partes de archivo
            for (Part filePart : fileParts) {
                // Es un campo de formulario regular (no archivo)
                if (filePart.getContentType() == null) {
                    String fieldName = filePart.getName();
                    String fieldValue = request.getParameter(fieldName);

                    // Realizar acciones adicionales si es necesario con los campos regulares
                } else {
                    // Es un archivo
                    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

                    // Guardar el archivo en el servidor (ajustar la ruta según sea necesario)
                    try (InputStream fileContent = filePart.getInputStream()) {
                        Files.copy(fileContent, Paths.get("/ruta/donde/guardar/" + fileName), StandardCopyOption.REPLACE_EXISTING);
                    }

                    // Puedes realizar acciones adicionales con el archivo si es necesario
                }
            }
        } catch (IOException | ServletException e) {
            e.printStackTrace();
            // Manejar cualquier excepción al procesar la solicitud multipartes
        }
    }

}
