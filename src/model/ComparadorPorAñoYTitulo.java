/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Comparator;

/**
 *
 * @author Borja Lorenzo
 */
public class ComparadorPorAñoYTitulo implements Comparator<Song>{

    @Override
    public int compare(Song o1, Song o2) {
        if(o1.getTitulo().equalsIgnoreCase(o2.getTitulo())){
            return Integer.compare(o1.getAño(), o2.getAño());
        }else{
            return o1.getTitulo().compareToIgnoreCase(o2.getTitulo());
        }
    }
    
    
}
