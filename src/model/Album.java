/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Borja Lorenzo
 */
public class Album implements Serializable{

    static Album instanciarAlbum(String titulo, String[] interpretes, int año, String duracion, int numCanciones, String tipo, String[] canciones) {
        Album nuevo = new Album();
        nuevo.setTitulo(titulo);
        for(String autor:interpretes){
            nuevo.interpretes.add(autor);
        }
        nuevo.setAño(año);
        nuevo.setDuracion(duracion);
        nuevo.setNumeroCanciones(numCanciones);
        nuevo.setTipo(tipo);
        
        for(String song:canciones){
            nuevo.canciones.add(Song.instanciarCancion(song,año,interpretes));
        }
        return nuevo;
        
    }

    static Album instanciarAlbum(String titulo, String[] autores, int anio, String duration, int numCanc, String tipo) {
            Album nuevo = new Album();
        nuevo.setTitulo(titulo);
        for(String autor:autores){
            nuevo.interpretes.add(autor);
        }
        nuevo.setAño(anio);
        nuevo.setDuracion(duration);
        nuevo.setNumeroCanciones(numCanc);
        nuevo.setTipo(tipo);
        
        
        return nuevo;
    }

    static String showEncabezado() {
        
        
        String resultado;
        resultado=String.format("<TR>"
                + "<TD>TITULO</TD>"
                + "<TD>INTERPRETES</TD>"
                + "<TD>AÑO</TD>"
                + "<TD>DURACION</TD>"
                + "<TD>NUMERO CANCIONES</TD>"
                + "<TD>TIPO</TD>"
                + "</TR>");
        return resultado;
    
        }
    
    private String titulo;
    private ArrayList<String>interpretes;
    private int año;
    private String duracion;
    private int numeroCanciones;
    private String tipo;
    private ArrayList<Song>canciones;

    public Album(String titulo, ArrayList<String> interpretes, int año, String duracion, int numeroCanciones, String tipo, ArrayList<Song> canciones) {
        this.titulo = titulo;
        this.interpretes = interpretes;
        this.año = año;
        this.duracion = duracion;
        this.numeroCanciones = numeroCanciones;
        this.tipo = tipo;
        this.canciones = canciones;
    }
    
    public Album() {
        this.titulo = "";
        this.interpretes = new ArrayList<>();
        this.año = 0;
        this.duracion = "";
        this.numeroCanciones = 0;
        this.tipo = "";
        this.canciones = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public ArrayList<String> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(ArrayList<String> interpretes) {
        this.interpretes = interpretes;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public int getNumeroCanciones() {
        return numeroCanciones;
    }

    public void setNumeroCanciones(int numeroCanciones) {
        this.numeroCanciones = numeroCanciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public String[] getAlbumDataToStringArray() {
        String[]autores=new String[this.interpretes.size()];
        String[]canciones=new String[this.canciones.size()];
        int k=0;
        for(String s:this.interpretes){
            autores[k]=s;
            k++;
        }
        String[] data = new String[7];
        data[0]=this.getTitulo();
        data[1]=Arrays.toString(autores);
        data[2]=String.valueOf(this.getAño());
        data[3]=this.getDuracion();
        data[4]=String.valueOf(this.getNumeroCanciones());
        data[5]=this.getTipo();
        
        k=0;
        for(Song s:this.canciones){
            canciones[k]=s.getTitulo();
            k++;
        }
        data[6]=Arrays.toString(canciones);
        
        
        return data;
    }
    
    public String toTableRow(){
        String resultado;
        String[] autores=new String[this.interpretes.size()];
        int k=0;
        for(String s:this.interpretes){
            autores[k]=s;
            k++;
        }
        
        resultado=String.format("<TR>"
                + "<TD>%s</TD>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>"
                + "<TD>%d</TD>"
                + "<TD>%s</TD>"
                + "</TR>", this.getTitulo(), Arrays.toString(autores),
                this.getAño(), this.getDuracion(), this.getNumeroCanciones(),
                this.getTipo());
        return resultado;
    }
    
    
}
