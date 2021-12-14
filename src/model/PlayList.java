/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Borja Lorenzo
 */
public class PlayList implements Serializable{
    
    private String nombre;
    private ArrayList<Song>canciones;

    public PlayList(String nombre, ArrayList<Song> canciones) {
        this.nombre = nombre;
        this.canciones = canciones;
    }
    
    public PlayList() {
        this.nombre = "";
        this.canciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    String[] getPlayListDataToStringArray() {
        String[]data = new String[2];
        String[]canciones=new String[this.getCanciones().size()];
        data[0]= this.getNombre();
        
        int k=0;
        for(Song s:this.getCanciones()){
            canciones[k]=s.getTitulo();
            k++;
        }
        data[1]=Arrays.toString(canciones);
        
        return data;
    }

    void setCancion(Song s) {
         this.canciones.add(s);
    }
    
    
    
}
