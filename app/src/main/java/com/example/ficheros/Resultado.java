package com.example.ficheros;

import java.util.ArrayList;

/**
 * Created by PcCom on 09/11/2017.
 */

public class Resultado {
    private boolean codigo; //true es correcto y false indica error

    private String mensaje;
    private ArrayList<String> mensajeArray;

    Resultado()
    {
        mensajeArray=new ArrayList<String>() ;
    }
    public ArrayList<String> getMensajeArray() {
        return mensajeArray;
    }

    public void setMensajeArray(String mensajeArray,int pos) {
        this.mensajeArray.add(mensajeArray);
    }

    private String contenido;

    public boolean getCodigo() {
        return codigo;
    }

    public void setCodigo(boolean codigo) {
        this.codigo = codigo;
    }
    public String getMensaje() {
        return mensaje;
    }
    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    public String getContenido() {
        return contenido;
    }
    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
