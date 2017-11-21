package com.example.ficheros;

import android.content.Context;

import android.os.Environment;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class Memoria {
    private Context contexto;

    public Memoria(Context c) {
        this.contexto = c;
    }

    public boolean escribirInterna(String fichero, String cadena, Boolean anadir, String codigo) {
        File miFichero;
        miFichero = new File(contexto.getFilesDir(), fichero);
        return escribir(miFichero, cadena, anadir, codigo);
    }

    public boolean escribirExterna(String fichero, String cadena, Boolean anadir, String codigo) {
        File miFichero, tarjeta;
        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);
        return escribir(miFichero, cadena, anadir, codigo);
    }

    private boolean escribir(File fichero, String cadena, Boolean anadir, String codigo) {
        FileOutputStream fos = null;
        OutputStreamWriter osw = null;
        BufferedWriter out = null;
        boolean correcto = false;
        try {
            fos = new FileOutputStream(fichero, anadir);
            osw = new OutputStreamWriter(fos, codigo);
            out = new BufferedWriter(osw);
            out.write(cadena);
        } catch (IOException e) {

        } finally {
            try {
                if (out != null) {
                    out.close();
                    correcto = true;
                }
            } catch (IOException e) {

            }
        }
        return correcto;
    }
/*
    public String mostrarPropiedadesInterna(String fichero) {
        File miFichero;
        miFichero = new File(contexto.getFilesDir(), fichero);
        return mostrarPropiedades(miFichero);
    }

    public String mostrarPropiedades(File fichero) {
        SimpleDateFormat formato = null;
        StringBuffer txt = new StringBuffer();
        try {
            if (fichero.exists()) {
                txt.append("Nombre: " + fichero.getName() + '\n');
                txt.append("Ruta: " + fichero.getAbsolutePath() + '\n');
                txt.append("Tamaño (bytes): " + Long.toString(fichero.length()) + '\n');
                formato = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss", Locale.getDefault());
                txt.append("Fecha: " + formato.format(new Date(fichero.lastModified())) + '\n');
            } else
                txt.append("No existe el fichero " + fichero.getName() + '\n');
        } catch (Exception e) {
            Log.e("Error", e.getMessage());
            txt.append(e.getMessage());
        }
        return txt.toString();
    }

    public boolean disponibleEscritura() {
        boolean escritura = false;
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED))
            escritura = true;
        return escritura;
    }

    public boolean disponibleLectura() {
        boolean lectura = false;
        String estado = Environment.getExternalStorageState();
        if (estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY) || estado.equals(Environment.MEDIA_MOUNTED))
            lectura = true;
        return lectura;
    }

    public String mostrarPropiedadesExternas(String fichero) {
        File miFichero, tarjeta;
        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);
        return mostrarPropiedades(miFichero);
    }
    */

    public Resultado leerExterna(String fichero, String codigo) {
        File miFichero, tarjeta;
        tarjeta = Environment.getExternalStorageDirectory();
        miFichero = new File(tarjeta.getAbsolutePath(), fichero);
        return leer(miFichero, codigo);
    }

    public Resultado leerInterna(String fichero, String codigo) {
        File miFichero;
        miFichero = new File(contexto.getFilesDir(), fichero);
        return leer(miFichero, codigo);
    }

    private Resultado leer(File fichero, String codigo) {
        FileInputStream fis = null;
        InputStreamReader isw = null;
        BufferedReader in = null;
        StringBuilder miCadena = new StringBuilder();
        Resultado resultado = new Resultado();
        int n;
        int i=0;
        resultado.setCodigo(true);
        try {
            fis = new FileInputStream(fichero);
            isw = new InputStreamReader(fis, codigo);
            in = new BufferedReader(isw);
            while ((n = in.read()) != -1) {
                miCadena.append((char) n);
            }
        } catch (IOException e) {
            resultado.setCodigo(false);
            resultado.setMensaje(e.getMessage());
        } finally {
            try {
                if (in != null) {
                    in.close();
                    resultado.setContenido(miCadena.toString());
                    resultado.setMensaje(miCadena.toString());
                }
            } catch (IOException e) {
                resultado.setCodigo(false);
                resultado.setMensaje(e.getMessage());
            }
        }
        return resultado;
    }


}