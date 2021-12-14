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
public class Song implements Serializable{

    static Song instanciarCancion(String song, int año, String[] autores) {
            Song nueva = new Song();
            nueva.setTitulo(song);
            nueva.setAño(año);
            
            nueva.interpretes.addAll(Arrays.asList(autores));
            
            return nueva;
    }

    static Song instanciarCancion(String nombre, int anio, String duration, String[] autores) {
            Song nueva = new Song();
            nueva.setTitulo(nombre);
            nueva.setAño(anio);
            nueva.setDuracion(duration);
            nueva.interpretes.addAll(Arrays.asList(autores));
            
            return nueva;
    }
    
    private String titulo;
    private int año;
    private String duracion;
    private ArrayList<String>interpretes;

    public Song(String titulo, int año, String duracion, ArrayList<String> interpretes) {
        this.titulo = titulo;
        this.año = año;
        this.duracion = duracion;
        this.interpretes = interpretes;
    }
    
    public Song() {
        this.titulo = "";
        this.año = 0;
        this.duracion = "";
        this.interpretes = new ArrayList<>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public ArrayList<String> getInterpretes() {
        return interpretes;
    }

    public void setInterpretes(ArrayList<String> interpretes) {
        this.interpretes = interpretes;
    }

    public String[] getSongDataToStringArray() {
        String[] data = new String[4];
        String[] autores=new String[this.interpretes.size()];
        
        int k=0;
        for(String s:this.interpretes){
            autores[k]=s;
            k++;
        }
        
        data[0]=this.getTitulo();
        data[1]=String.valueOf(this.getAño());
        data[2]=this.getDuracion();
        
        
        data[3]=Arrays.toString(autores);
        
        
        
        return data;
    }
    
    
    
}
