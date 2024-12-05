package com.example.ejemplos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * MetodosUtiles.java
 * Clase con métodos comunes y útiles para facilitar el desarrollo en Android.
 */
public class MetodosUtiles {

    // ---------------------- Métodos de Cadenas ----------------------

    /**
     * Verifica si una cadena es nula o está vacía.
     *
     * @param cadena La cadena a verificar.
     * @return true si es nula o está vacía, false en caso contrario.
     */
    public static boolean esNulaOVacia(String cadena) {
        return cadena == null || cadena.trim().isEmpty();
    }

    /**
     * Capitaliza la primera letra de una cadena.
     *
     * @param cadena La cadena a capitalizar.
     * @return La cadena con la primera letra en mayúscula.
     */
    public static String capitalizarPrimeraLetra(String cadena) {
        if (esNulaOVacia(cadena)) return cadena;
        return cadena.substring(0, 1).toUpperCase() + cadena.substring(1);
    }

    /**
     * Invierte una cadena de texto.
     *
     * @param cadena La cadena a invertir.
     * @return La cadena invertida.
     */
    public static String invertirCadena(String cadena) {
        if (esNulaOVacia(cadena)) return cadena;
        return new StringBuilder(cadena).reverse().toString();
    }

    /**
     * Cuenta cuántas veces aparece un carácter en una cadena.
     *
     * @param cadena La cadena donde buscar.
     * @param caracter El carácter a contar.
     * @return El número de veces que aparece el carácter.
     */
    public static int contarCaracter(String cadena, char caracter) {
        if (esNulaOVacia(cadena)) return 0;
        int contador = 0;
        for (char c : cadena.toCharArray()) {
            if (c == caracter) contador++;
        }
        return contador;
    }

    // ---------------------- Métodos de Fecha ----------------------

    /**
     * Devuelve la fecha actual en un formato específico.
     *
     * @param formato El formato deseado, por ejemplo "yyyy-MM-dd".
     * @return La fecha actual como cadena en el formato indicado.
     */
    public static String obtenerFechaActual(String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        return sdf.format(new Date());
    }

    /**
     * Convierte una cadena de texto a un objeto Date.
     *
     * @param fecha La fecha como cadena.
     * @param formato El formato en el que está escrita la fecha.
     * @return Un objeto Date correspondiente a la cadena dada.
     */
    public static Date convertirCadenaAFecha(String fecha, String formato) {
        SimpleDateFormat sdf = new SimpleDateFormat(formato, Locale.getDefault());
        try {
            return sdf.parse(fecha);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Agrega días a una fecha específica.
     *
     * @param fecha La fecha inicial.
     * @param dias El número de días a agregar.
     * @return La nueva fecha con los días agregados.
     */
    public static Date agregarDiasAFecha(Date fecha, int dias) {
        Calendar calendario = Calendar.getInstance();
        calendario.setTime(fecha);
        calendario.add(Calendar.DAY_OF_YEAR, dias);
        return calendario.getTime();
    }

    // ---------------------- Métodos de Validación ----------------------

    /**
     * Valida si un correo electrónico tiene el formato correcto.
     *
     * @param correo El correo electrónico a validar.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean esCorreoValido(String correo) {
        return correo != null && android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches();
    }

    /**
     * Valida si un número de teléfono tiene el formato correcto.
     *
     * @param numero El número de teléfono a validar.
     * @return true si es válido, false en caso contrario.
     */
    public static boolean esNumeroTelefonicoValido(String numero) {
        return numero != null && android.util.Patterns.PHONE.matcher(numero).matches();
    }

    /**
     * Valida si una contraseña cumple con los requisitos básicos.
     *
     * @param contrasena La contraseña a validar.
     * @return true si es válida, false en caso contrario.
     */
    public static boolean esContrasenaValida(String contrasena) {
        return contrasena != null && contrasena.length() >= 8;
    }

    // ---------------------- Métodos de Red ----------------------

    /**
     * Verifica si el dispositivo tiene conexión a Internet.
     *
     * @param contexto El contexto de la aplicación.
     * @return true si hay conexión, false en caso contrario.
     */
//    public static boolean hayConexionInternet(Context contexto) {
//        ConnectivityManager cm = (ConnectivityManager) contexto.getSystemService(Context.CONNECTIVITY_SERVICE);
//       // NetworkInfo infoRed = cm.getActiveNetworkInfo();
//        return infoRed != null && infoRed.isConnected();
//    }

    // ---------------------- Métodos de Archivos ----------------------

    /**
     * Lee el contenido de un archivo y lo devuelve como cadena de texto.
     *
     * @param archivo El archivo a leer.
     * @return El contenido del archivo como cadena.
     * @throws IOException Si ocurre un error de lectura.
     */
    public static String leerArchivo(File archivo) throws IOException {
        StringBuilder contenido = new StringBuilder();
        BufferedReader lector = new BufferedReader(new FileReader(archivo));
        String linea;
        while ((linea = lector.readLine()) != null) {
            contenido.append(linea).append("\n");
        }
        lector.close();
        return contenido.toString();
    }

    /**
     * Escribe contenido en un archivo.
     *
     * @param archivo El archivo donde escribir.
     * @param contenido El contenido a escribir.
     * @throws IOException Si ocurre un error de escritura.
     */
    public static void escribirArchivo(File archivo, String contenido) throws IOException {
        FileWriter escritor = new FileWriter(archivo);
        escritor.write(contenido);
        escritor.close();
    }

    // ---------------------- Métodos de Números Aleatorios ----------------------

    /**
     * Genera un número aleatorio entre dos valores long (incluyendo los extremos).
     *
     * @param minimo El valor mínimo del rango.
     * @param maximo El valor máximo del rango.
     * @return Un número aleatorio entre minimo y maximo.
     * @throws IllegalArgumentException Si minimo es mayor que maximo.
     */
    public static long generarNumeroAleatorio(long minimo, long maximo) {
        if (minimo > maximo) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el máximo.");
        }
        return minimo + (long) (Math.random() * ((maximo - minimo) + 1));
    }

    // ---------------------- Métodos de Android ----------------------

    /**
     * Muestra un Toast corto.
     *
     * @param contexto El contexto de la aplicación.
     * @param mensaje El mensaje a mostrar.
     */
    public static void mostrarMensaje(Context contexto, String mensaje) {
        Toast.makeText(contexto, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Oculta el teclado en una actividad.
     *
     * @param actividad La actividad actual.
     */
    public static void ocultarTeclado(Activity actividad) {
        View vista = actividad.getCurrentFocus();
        if (vista != null) {
            InputMethodManager imm = (InputMethodManager) actividad.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(vista.getWindowToken(), 0);
        }
    }
    /**
     * Genera un número aleatorio entre dos valores int (incluyendo los extremos).
     *
     * @param minimo El valor mínimo del rango.
     * @param maximo El valor máximo del rango.
     * @return Un número aleatorio entre minimo y maximo.
     * @throws IllegalArgumentException Si minimo es mayor que maximo.
     */
    public static int generarNumeroAleatorioInt(int minimo, int maximo) {
        if (minimo > maximo) {
            throw new IllegalArgumentException("El valor mínimo no puede ser mayor que el máximo.");
        }
        return minimo + (int) (Math.random() * ((maximo - minimo) + 1));
    }

}