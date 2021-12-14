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
public class ComparadorPorAño implements Comparator<Album>{

    @Override
    public int compare(Album o1, Album o2) {
        return Integer.compare(o1.getAño(), o2.getAño());
    }
    
}
