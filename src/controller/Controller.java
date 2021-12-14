/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.coti.tools.OpMat.join;
import java.io.FileNotFoundException;
import model.Musicfy;
import view.View;

/**
 *
 * @author Borja Lorenzo
 */

public class Controller {

    Musicfy m = new Musicfy();
    
    public void añadirAlbum(String titulo, String[] interpretes, int año, String duracion, int numCanciones, String tipo, String[] canciones) {
        m.añadirAlbum(titulo,interpretes,año,duracion,numCanciones,tipo,canciones);
    }

    public String[][] getAlbumsData() {
        String[][]datos=m.getAlbumesDataToStringMatriz();
        String[][]resul=new String[m.getNumAlbumes()+1][];
        resul[0]=m.getEncabezadoAlbumesData();
        int k=0;
        for(int i=1;i<resul.length;i++){
            resul[i]=datos[k];
            k++;
        }
        
        return resul;
    }

    public int getNumAlbumes() {
        return m.getNumAlbumes();
    }

    public String[][] getSongsData() {
        String[][]datos=m.getCancionesDataToStringMatriz();
        String[][]resul=new String[m.getNumCanciones()+1][];
        resul[0]=m.getEncabezadoCancionesData();
        int k=0;
        for(int i=1;i<resul.length;i++){
            resul[i]=datos[k];
            k++;
        }
        
        return resul;

    }

    public int getNumCanciones() {
        return m.getNumCanciones();
    }

    

    public int getNumArtists() {
        return m.getNumArtists();
    }

    public String[][] getArtistsData() {
        String[][]datos=m.getArtistsDataToStringMatriz();
        String[][]resul=new String[m.getNumArtists()+1][];
        resul[0]=m.getEncabezadoArtistsData();
        int k=0;
        for(int i=1;i<resul.length;i++){
            resul[i]=datos[k];
            k++;
        }
        
        return resul;
    }

    public boolean existeAlbum(String titulo) {
        return m.existeAlbum(titulo);
    }

    public void borrarAlbum(String titulo) {
        m.borrarAlbum(titulo);
    }

    public String[] getEncabezadoAlbumesData(){
        return m.getEncabezadoAlbumesData();
    }
    
    public String[][] getAlbumData(String titulo) {
        return m.getAlbumData(titulo);
    }

    public void modificarAlbum(String titulo, int anio, String duracion) {
        m.modificarAlbum(titulo,anio,duracion);
    }

    public void generacionAleatoria() throws Exception {
        m.generacionAleatoria();
    }

    public void loadBinaryData() {
        m.loadBinaryData();
    }

    public void saveBinaryData() {
        m.saveBinaryData();
    }

    public void loadTextData() throws Exception {
        m.loadTextData();
    }

    public void exportarArtistas() throws Exception {
        m.exportarArtistas();
    }

    public void exportarHTMLalbumes() throws FileNotFoundException {
        m.exportarHTMLalbumes();
    }

    public void añadirArtista(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, String[] albumes) {
        m.añadirArtista(nombre,biografia,instagram,twitter,facebook,wikipedia,albumes);
    }

    public boolean existeArtista(String nombre) {
        return m.existeArtist(nombre);
    }

    public boolean tieneAlbumes(String nombre) {
        return m.tieneAlbumes(nombre);
    }

    public String[] getAlbum(String nombre) {
        return m.getAlbum(nombre);
    }

    public void borrarArtista(String nombre) {
        m.borrarArtista(nombre);
    }

    public void modificarArtista(String nombre, int opcion, String nuevo) {
        m.modificarArtista(nombre,opcion,nuevo);
    }

    public String[][] getArtistData(String nombre) {
        String[][]resul=new String[2][];
        resul[0]=m.getEncabezadoArtistsData();
        resul[1]=m.getArtistData(nombre);
        
        
        return resul;
    }

    public String[][] getAlbumesOrderByYear(String nombre) {
        String[][]datos=m.getAlbumesOrderByYear(nombre);
        String[][]resul=new String[datos.length + 1][6];
        
        resul[0][0]="TITULO"; resul[0][1]="INTERPRETES"; resul[0][2]="AÑO";
        resul[0][3]="DURACION"; resul[0][4]="NUMERO CANCIONES"; 
        resul[0][5]="TIPO";
        
        int k=0;
        for(int i=1;i<resul.length;i++){
            for(int j=0;j<6;j++){
                resul[i][j]=datos[k][j];
            }
            k++;
        }
        
        return resul;
    }

    public void ordenarAlbumesAño() {
        m.ordenarAlbumesAño();
    }

    public void añadirPlayList(String nombre, int numCanc) {
        m.añadirPlayList(nombre,numCanc);
    }

    public boolean existePlayList(String nombre) {
        return m.existePlayList(nombre);
    }

    public String[][] getDataPlayList(String nombre) {
        String[][]datos=m.getSongsPlayListData(nombre);
        String[][]resul=new String[datos.length + 1][];
        resul[0]=m.getEncabezadoCancionesData();
        int k=0;
        for(int i=1;i<resul.length;i++){
            resul[i]=datos[k];
            k++;
        }
        
        return resul;
    }

    public boolean existeCancionInPlayList(String nombre, String nomCanc) {
        return m.existeCancionInPlayList(nombre,nomCanc);
    }

    public void borrarCancionInPlayList(String nombre, String nomCanc) {
        m.borrarCancionInPlayList(nombre,nomCanc);
    }

    public int getNumPlayLists() {
        return m.getNumPlayLists();
    }

    public String[][] getPlayListsData() {
        String[][]playlistsData = m.getPlayListsDataToStringMatriz();
        String[][]resul=new String[playlistsData.length + 1][2];
        resul[0][0]="NOMBRE"; resul[0][1]="CANCIONES";
        int k=0;
        for(int i=1;i<resul.length;i++){
            resul[i]=playlistsData[k];
            k++;
        }
        
        return resul;
    }

    public boolean existeSong(String nomCanc) {
        return m.existeSong(nomCanc);
    }

    public void añadirCancionPlayList(String nomPlay, String nomCanc) {
        m.añadirCancionPlayList(nomPlay,nomCanc);
    }

    public boolean existeArchivoBinario() {
        return m.existeArchivoBinario();
    }

    public boolean existeArchivosDatos() {
        return m.existeArchivoDatos();
    }
}
