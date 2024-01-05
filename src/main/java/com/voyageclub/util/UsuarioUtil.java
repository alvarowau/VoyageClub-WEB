package com.voyageclub.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioUtil {

    public static boolean validarDNI(String dni) {
        // Patrón para el formato de DNI español: 8 dígitos seguidos de una letra
        String patronDNI = "^[0-9]{8}[A-Za-z]$";

        // Compilar la expresión regular
        Pattern patron = Pattern.compile(patronDNI);

        // Crear un objeto Matcher
        Matcher matcher = patron.matcher(dni);

        // Verificar si el DNI coincide con el patrón y devolver el resultado
        return matcher.matches();
    }
}
