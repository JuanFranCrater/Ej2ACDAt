package com.example.ficheros;

/**
 * Created by PcCom on 09/11/2017.
 */

public class Resultado {
    private boolean codigo; //true es correcto y false indica error

    private String mensaje;
    private String[] mensajeArray;

    public String[] getMensajeArray() {
        return mensajeArray;
    }

    public void setMensajeArray(String mensajeArray,int pos) {
        this.mensajeArray[pos] = mensajeArray;
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
