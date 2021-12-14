/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Borja Lorenzo
 */
public class Artist implements Serializable{

    static Artist instanciar(String artista, String titulo) {
       Artist nuevo = new Artist();
       
       nuevo.setNombre(artista);
       nuevo.albumes.add(titulo);
       
       return nuevo;
    }

    static Artist instanciarArtista(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, String[] albumes) {
        
        Artist nuevo = new Artist();
        nuevo.setNombre(nombre);
        nuevo.setBiografia(biografia);
        nuevo.setInstagram(instagram);
        nuevo.setTwitter(twitter);
        nuevo.setFacebook(facebook);
        nuevo.setWikipedia(wikipedia);
        
        for(String s:albumes){
            nuevo.albumes.add(s);
        }
        return nuevo;
        
    }

    static Artist instanciarArtista(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia) {
        Artist nuevo = new Artist();
        nuevo.setNombre(nombre);
        nuevo.setBiografia(biografia);
        nuevo.setInstagram(instagram);
        nuevo.setTwitter(twitter);
        nuevo.setFacebook(facebook);
        nuevo.setWikipedia(wikipedia);
        
        
        return nuevo;
    }
    
    
    private String nombre;
    private String biografia;
    private String instagram;
    private String twitter;
    private String facebook;
    private String wikipedia;
    private ArrayList<String>albumes;

    public Artist(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, ArrayList<String> albumes) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.instagram = instagram;
        this.twitter = twitter;
        this.facebook = facebook;
        this.wikipedia = wikipedia;
        this.albumes = albumes;
    }
    
     public Artist() {
        this.nombre = "";
        this.biografia = "";
        this.instagram = "";
        this.twitter = "";
        this.facebook = "";
        this.wikipedia = "";
        this.albumes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getWikipedia() {
        return wikipedia;
    }

    public void setWikipedia(String wikipedia) {
        this.wikipedia = wikipedia;
    }

    public ArrayList<String> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<String> albumes) {
        this.albumes = albumes;
    }

    void añadirAlbum(String titulo) {
        this.albumes.add(titulo);
    }

    String[] getArtistsDataToStringArray() {
        String[] data= new String[7];
        String[] albums= new String[this.albumes.size()];
        
        int k=0;
        for(String s:this.albumes){
            albums[k]=s;
            k++;
        }
        
        data[0]=this.getNombre();
        data[1]=this.getBiografia();
        data[2]=this.getInstagram();
        data[3]=this.getTwitter();
        data[4]=this.getFacebook();
        data[5]=this.getWikipedia();
        data[6]=Arrays.toString(albums);
        
        return data;
    }
     
     
    
}
