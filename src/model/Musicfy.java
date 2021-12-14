/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static com.coti.tools.OpMat.exportToDisk;
import static com.coti.tools.OpMat.importFromDisk;
import com.coti.tools.Rutas;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Borja Lorenzo
 */
public class Musicfy implements Serializable{
    
    //private String file
    private String nameOfFolder="musicfy";
    private String nameOfBinaryFolder="binarios";
    private String nameOfBinaryFile="musicfy.bin";
    private String nameOfDataFolder="datos";
    private String nameOfAlbumDataFile="albumes.txt";
    private String nameOfArtistDataFile="artistas.txt";
    private String nameOfExitFolder="salida";
    private String nameOfExitArtistsData="artistas.col";
    private String nameOfExitHTMLalbumFile="albumes.html";
    private String nameOfRandomFolder="aleatorios";
    private String randomAlbumsNames="nombresAlbumes.txt";
    private String randomArtistsNames="nombresArtistas.txt";
    private String randomPlaylistsNames="nombresPlaylists.txt";
    private String randomSongNames="titulosCanciones.txt";
    
    
    
    private ArrayList<Song>canciones;
    private ArrayList<Album>albumes;
    private ArrayList<Artist>artistas;
    private ArrayList<PlayList>playlists;

    public Musicfy(ArrayList<Song> canciones, ArrayList<Album> albumes, ArrayList<Artist> artistas, ArrayList<PlayList> playlists) {
        this.canciones = canciones;
        this.albumes = albumes;
        this.artistas = artistas;
        this.playlists = playlists;
    }
    
    public Musicfy() {
        this.canciones = new ArrayList<>();
        this.albumes = new ArrayList<>();
        this.artistas = new ArrayList<>();
        this.playlists = new ArrayList<>();
    }

    public ArrayList<Song> getCanciones() {
        return canciones;
    }

    public void setCanciones(ArrayList<Song> canciones) {
        this.canciones = canciones;
    }

    public ArrayList<Album> getAlbumes() {
        return albumes;
    }

    public void setAlbumes(ArrayList<Album> albumes) {
        this.albumes = albumes;
    }

    public ArrayList<Artist> getArtistas() {
        return artistas;
    }

    public void setArtistas(ArrayList<Artist> artistas) {
        this.artistas = artistas;
    }

    public ArrayList<PlayList> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<PlayList> playlists) {
        this.playlists = playlists;
    }

    public String getNameOfFolder() {
        return nameOfFolder;
    }

    public void setNameOfFolder(String nameOfFolder) {
        this.nameOfFolder = nameOfFolder;
    }

    public String getNameOfBinaryFolder() {
        return nameOfBinaryFolder;
    }

    public void setNameOfBinaryFolder(String nameOfBinaryFolder) {
        this.nameOfBinaryFolder = nameOfBinaryFolder;
    }

    public String getNameOfBinaryFile() {
        return nameOfBinaryFile;
    }

    public void setNameOfBinaryFile(String nameOfBinaryFile) {
        this.nameOfBinaryFile = nameOfBinaryFile;
    }

    public String getNameOfDataFolder() {
        return nameOfDataFolder;
    }

    public void setNameOfDataFolder(String nameOfDataFolder) {
        this.nameOfDataFolder = nameOfDataFolder;
    }

    public String getNameOfAlbumDataFile() {
        return nameOfAlbumDataFile;
    }

    public void setNameOfAlbumDataFile(String nameOfAlbumDataFile) {
        this.nameOfAlbumDataFile = nameOfAlbumDataFile;
    }

    public String getNameOfArtistDataFile() {
        return nameOfArtistDataFile;
    }

    public void setNameOfArtistDataFile(String nameOfArtistDataFile) {
        this.nameOfArtistDataFile = nameOfArtistDataFile;
    }

    public String getNameOfExitFolder() {
        return nameOfExitFolder;
    }

    public void setNameOfExitFolder(String nameOfExitFolder) {
        this.nameOfExitFolder = nameOfExitFolder;
    }

    public String getNameOfExitArtistsData() {
        return nameOfExitArtistsData;
    }

    public void setNameOfExitArtistsData(String nameOfExitArtistsData) {
        this.nameOfExitArtistsData = nameOfExitArtistsData;
    }

    public String getNameOfExitHTMLalbumFile() {
        return nameOfExitHTMLalbumFile;
    }

    public void setNameOfExitHTMLalbumFile(String nameOfExitHTMLalbumFile) {
        this.nameOfExitHTMLalbumFile = nameOfExitHTMLalbumFile;
    }

    public String getNameOfRandomFolder() {
        return nameOfRandomFolder;
    }

    public void setNameOfRandomFolder(String nameOfRandomFolder) {
        this.nameOfRandomFolder = nameOfRandomFolder;
    }

    public String getRandomAlbumsNames() {
        return randomAlbumsNames;
    }

    public void setRandomAlbumsNames(String randomAlbumsNames) {
        this.randomAlbumsNames = randomAlbumsNames;
    }

    public String getRandomArtistsNames() {
        return randomArtistsNames;
    }

    public void setRandomArtistsNames(String randomArtistsNames) {
        this.randomArtistsNames = randomArtistsNames;
    }

    public String getRandomPlaylistsNames() {
        return randomPlaylistsNames;
    }

    public void setRandomPlaylistsNames(String randomPlaylistsNames) {
        this.randomPlaylistsNames = randomPlaylistsNames;
    }

    public String getRandomSongNames() {
        return randomSongNames;
    }

    public void setRandomSongNames(String randomSongNames) {
        this.randomSongNames = randomSongNames;
    }
    
    
    
    

    
    
    public void añadirAlbum(String titulo, String[] interpretes, int año, String duracion, int numCanciones, String tipo, String[] canciones) {
        this.albumes.add(Album.instanciarAlbum(titulo,interpretes,año,duracion,numCanciones,tipo,canciones));
        
        for(String song:canciones){
            this.canciones.add(Song.instanciarCancion(song, año, interpretes));
        }
        
        for(String artista:interpretes){
            if(this.existeArtista(artista)){
                this.añadirAlbumArtista(artista,titulo);
            }else{
                this.añadirArtista(artista,titulo);
            }
        }
        
    }

    public String[] getEncabezadoAlbumesData(){
        String[]encabezado=new String[7];
        
        encabezado[0]="TITULO"; encabezado[1]="INTERPRETES"; encabezado[2]="AÑO";
        encabezado[3]="DURACION"; encabezado[4]="NUMERO CANCIONES"; 
        encabezado[5]="TIPO";
        encabezado[6]="CANCIONES";
        
        return encabezado;
    }
    
    public String[][] getAlbumesDataToStringMatriz() {
        String[][] albumsData = new String[this.albumes.size()][7];
    
        int i=0;
        for(Album a : this.albumes){
            albumsData[i]=a.getAlbumDataToStringArray();
            i++;
        }
        
        return albumsData;
        
    }

    public int getNumAlbumes() {
        return this.albumes.size();
    }

    public int getNumCanciones() {
        return this.canciones.size();
    }

    public String[] getEncabezadoCancionesData(){
        String[]encabezado=new String[4];
        
        encabezado[0]="TITULO"; encabezado[1]="AÑO";
        encabezado[2]="DURACION"; encabezado[3]="INTERPRETES";
        
        return encabezado;
    }
    
    public String[][] getCancionesDataToStringMatriz() {
        String[][] songsData = new String[this.canciones.size()][4];
        
        Comparator<Song> cmp;
        cmp=new ComparadorPorAñoYTitulo();
        Collections.sort(this.canciones,cmp);
        
        int k=0;       
        for(Song s : this.canciones){
            songsData[k]=s.getSongDataToStringArray();
            k++;
        }
        return songsData;
    }

    private boolean existeArtista(String artista) {
        boolean existe=false;
        for(Artist artist: this.artistas){
            if(artist.getNombre().equals(artista)){
                existe=true;
            }
        }
        
        return existe;
        
    }

    private void añadirAlbumArtista(String artista, String titulo) {
        for(Artist artist: this.artistas){
            if(artist.getNombre().equals(artista)){
                artist.añadirAlbum(titulo);
            }
        }
    }

    private void añadirArtista(String artista, String titulo) {
        this.artistas.add(Artist.instanciar(artista, titulo));
    }

    public int getNumArtists() {
        return this.artistas.size();
    }

    public String[] getEncabezadoArtistsData(){
        String[]encabezado=new String[7];
        
        encabezado[0]="NOMBRE"; encabezado[1]="BIOGRAFIA"; encabezado[2]="INSTAGRAM";
        encabezado[3]="TWITTER"; encabezado[4]="FACEBOOK"; encabezado[5]="WIKIPEDIA";
        encabezado[6]="ALBUMES";
        
        return encabezado;
    }
    
    public String[][] getArtistsDataToStringMatriz() {
        String [][] data = new String[this.artistas.size()][7];
        
        int k=0;
        for(Artist a:this.artistas){
            data[k]=a.getArtistsDataToStringArray();
            k++;
        }
        
        return data;
        
    }

    public boolean existeAlbum(String titulo) {
        boolean existe=false;
        for(Album a : this.albumes){
            if(a.getTitulo().equals(titulo)){
                existe=true;
            }
        }
        return existe;
    }

    public void borrarAlbum(String titulo) {
        ArrayList<Song> canc = new ArrayList<>();
        
        //ELIMINAR ALBUM
        for(int i=0; i<this.albumes.size();i++){
                if(albumes.get(i).getTitulo().equals(titulo)){
                    canc=albumes.get(i).getCanciones();
                    albumes.remove(i);
                }         
        }
        
        //ELIMINAR ALBUM DE ARTISTA
        for(int i=0; i<this.artistas.size();i++){
            for(int j=0; j<this.artistas.get(i).getAlbumes().size();j++){
                if(this.artistas.get(i).getAlbumes().get(j).equals(titulo)){
                    this.artistas.get(i).getAlbumes().remove(j);
                }
            }
        }
        
        //ELIMINAR CANCIONES DE COLECCION
        for(int i=0;i<this.canciones.size();i++){
            for(int j=0;j<canc.size();j++){
                if(this.canciones.get(i).getTitulo().equals(canc.get(j).getTitulo())){
                    this.canciones.remove(i);
                }
            }
        }
        
        
    }

    public String[][] getAlbumData(String titulo) {
        
        String[][]data=new String[2][7];
        data[0][0]="TITULO"; data[0][1]="INTERPRETES"; data[0][2]="AÑO";
        data[0][3]="DURACION"; data[0][4]="NUMERO CANCIONES"; data[0][5]="TIPO";
        data[0][6]="CANCIONES";
        for(Album a:this.albumes){
            if(a.getTitulo().equals(titulo)){
                data[1]=a.getAlbumDataToStringArray();
            }
        }
        return data;
    }

    public void modificarAlbum(String titulo, int anio, String duracion) {
         if(anio==0){
             for(int i=0;i<this.albumes.size();i++){
                 if(this.albumes.get(i).getTitulo().equals(titulo)){
                     this.albumes.get(i).setDuracion(duracion);
                 }
             }
         }else{
             for(int i=0;i<this.albumes.size();i++){
                 if(this.albumes.get(i).getTitulo().equals(titulo)){
                     this.albumes.get(i).setAño(anio);
                 }
             }
         }
    }

    
    
    public void generacionAleatoria() throws Exception {
        this.albumes=new ArrayList<>();
        this.canciones=new ArrayList<>();
        this.artistas=new ArrayList<>();
        
        Random RR = new Random();
        String rutaRandomAlbumsNames = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfRandomFolder() + File.separator + 
                this.getRandomAlbumsNames();
        
        String rutaRandomArtistsNames = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfRandomFolder() + File.separator + 
                this.getRandomArtistsNames();
        
        String rutaRandomPlaylistsNames = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfRandomFolder() + File.separator + 
                this.getRandomPlaylistsNames();
        
        String rutaRandomSongsNames = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfRandomFolder() + File.separator + 
                this.getRandomSongNames();
        
        File archivoRandomAlbumsNames = new File(rutaRandomAlbumsNames);
        File archivoRandomArtistsNames = new File(rutaRandomArtistsNames);
        File archivoRandomPlaylistsNames = new File(rutaRandomPlaylistsNames);
        File archivoRandomSongsNames = new File(rutaRandomSongsNames);
        
        String[][]nombreAlbumes=importFromDisk(archivoRandomAlbumsNames,";");
        String[][]nombreArtistas=importFromDisk(archivoRandomArtistsNames,";");
        String[][]nombrePlayLists=importFromDisk(archivoRandomPlaylistsNames,";");
        String[][]nombreCanciones=importFromDisk(archivoRandomSongsNames,";");
        
        for(int i=0;i<nombreCanciones.length;i++){
            String[]autores=new String[RR.nextInt(4)+1];
            String duration;
            int min=RR.nextInt(30);
            int seg=RR.nextInt(59);
            if(min==0){
                duration=String.format("%d segundos", seg);
            }else if(seg==0){
                duration=String.format("%d minutos", min);
            }else{
                duration =String.format("%d minutos %d segundos", min, seg);
            }
            for(int j=0;j<autores.length;j++){
                autores[j]=nombreArtistas[RR.nextInt(nombreArtistas.length)][0];
            }
            this.canciones.add(Song.instanciarCancion(nombreCanciones[i][0], RR.nextInt(20)+1999 , duration, autores));
        }
        
        for(int i=0;i<nombreArtistas.length;i++){
            String nombre = nombreArtistas[i][0];
            String biografia = String.format("Biografia de %s", nombre);
            String instagram = String.format("@%s_ig", nombre);
            String twitter = String.format("@%s_tw", nombre);
            String facebook = String.format("%sFBook", nombre);
            String wikipedia = String.format("https://es.wikipedia.org/wiki/%s", nombre);
            String[]albumes = new String[RR.nextInt(6)+1];
            for(int j=0;j<albumes.length;j++){
                albumes[j]=nombreAlbumes[RR.nextInt(nombreAlbumes.length)][0];
            }
            
            this.añadirArtista(nombre, biografia, instagram, twitter, facebook, wikipedia, albumes);
            
        }
        
        for(int i=0;i<nombreAlbumes.length;i++){
            String tipo;
            String nombre =  nombreAlbumes[i][0];
            String[]interpretes=new String[RR.nextInt(5)];
            for(int j=0;j<interpretes.length;j++){
                interpretes[j]=nombreArtistas[RR.nextInt(nombreArtistas.length)][0];
            }
            int anio = RR.nextInt(20)+1999;
            String duration;
            int min=RR.nextInt(30);
            int seg=RR.nextInt(59);
            if(min==0){
                duration=String.format("%d segundos", seg);
            }else if(seg==0){
                duration=String.format("%d minutos", min);
            }else{
                duration =String.format("%d minutos %d segundos", min, seg);
            }
            int numCanc=RR.nextInt(10)+1;
            String[]canc=new String[numCanc];
            if(numCanc==1){
                tipo="sencillo";
                canc[0]=nombre;
            }else{
                tipo="álbum";
                for(int j=0;j<canc.length;j++){
                    canc[j]=nombreCanciones[RR.nextInt(nombreCanciones.length)][0];
                }
            }
            
            this.albumes.add(Album.instanciarAlbum(nombre, interpretes, anio, duration, numCanc, tipo, canc));
            
            
        }
        
        for(int i=0;i<nombrePlayLists.length;i++){
            String nombre = nombrePlayLists[RR.nextInt(nombrePlayLists.length)][0];
            int numCanc=RR.nextInt(10)+1;
            this.añadirPlayList(nombre, numCanc);
            
        }
        
        
        
        
    }

    public void loadBinaryData() {
        
        String rutaArchivoBinario = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfBinaryFolder() + File.separator + 
                this.getNameOfBinaryFile();
        
        File binaryFile = new File(rutaArchivoBinario);
        
        FileInputStream fis;
        BufferedInputStream bis;
        ObjectInputStream ois = null;
        
        try{
            fis = new FileInputStream(binaryFile);
            bis = new BufferedInputStream(fis);
            ois = new ObjectInputStream(bis);
            try {
                this.albumes=(ArrayList<Album>) ois.readObject();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Musicfy.class.getName()).log(Level.SEVERE, null, ex);
            }
            ois.close();
        }catch (IOException ex){
            System.out.println("no fue posible guardar el archivo");
            System.out.println(ex.toString());
        }finally{
            if(null!=ois){  
                try{
                ois.close();
                }catch(IOException ex){
                    System.out.println("ERROR");
                }
            }
        }
    }
    
        
    
    public void saveBinaryData() {
        
        String rutaArchivoBinario = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfBinaryFolder() + File.separator + 
                this.getNameOfBinaryFile();
        
        File binaryFile = new File(rutaArchivoBinario);
        
        FileOutputStream fos;
        BufferedOutputStream bos;
        ObjectOutputStream oos = null;
        
        try{
            fos = new FileOutputStream(binaryFile);
            bos = new BufferedOutputStream(fos);
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this.albumes);
            oos.close();
        }catch (IOException ex){
            System.out.println("no fue posible guardar el archivo");
            System.out.println(ex.toString());
        }finally{
            if(null!=oos){  
                try{
                oos.close();
                }catch(IOException ex){
                    System.out.println("ERROR");
                }
            }
        }
    }

    public void loadTextData() throws Exception {
        String[][]artistsData;
        String[][]albumsData;
        String rutaArchivoDatosAlbumes = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfDataFolder() + File.separator + 
                this.getNameOfAlbumDataFile();
        
        String rutaArchivoDatosArtistas = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfDataFolder() + File.separator + 
                this.getNameOfArtistDataFile();
        
        File artistsFile = new File(rutaArchivoDatosArtistas);
        File albumsFile =  new File(rutaArchivoDatosAlbumes);
        
        artistsData=importFromDisk(artistsFile,"#");
        albumsData=importFromDisk(albumsFile,"\t");
        
        this.saveAlbumDataFromStringMatriz(albumsData);
        this.saveArtistDataFromStringMatriz(artistsData);
        
        
    }

    private void saveAlbumDataFromStringMatriz(String[][] albumsData) {
        for(int i=0;i<albumsData.length;i++){
            
            String autor[]=albumsData[i][1].split(";");
            if(albumsData[i][4].equals("1")){
                String canc[]=new String[1];
                canc[0]=albumsData[i][0];
                this.añadirAlbum(albumsData[i][0], autor,
                        Integer.parseInt(albumsData[i][2]), albumsData[i][3],
                        Integer.parseInt(albumsData[i][4]), albumsData[i][5], canc);
            }else{
                String canc[]=albumsData[i][6].split(";");
                this.añadirAlbum(albumsData[i][0], autor,
                        Integer.parseInt(albumsData[i][2]), albumsData[i][3],
                        Integer.parseInt(albumsData[i][4]), albumsData[i][5], canc);
            }
        }
    }

    private void saveArtistDataFromStringMatriz(String[][] artistsData) {
        
        for(int i=0;i<artistsData.length;i++){
            String[]nomAlbums=artistsData[i][6].split(";");
            ArrayList<String>nombreAlbumes=new ArrayList<>();
            for(int j=0;j<nomAlbums.length;j++){
                nombreAlbumes.add(nomAlbums[j]);
            }
            
            this.artistas.add(new Artist(artistsData[i][0],artistsData[i][1],
                                artistsData[i][2], artistsData[i][3], artistsData[i][4],
             artistsData[i][5], nombreAlbumes));
        }
    }

    public void exportarArtistas() throws Exception {
        FileOutputStream fout;
        PrintStream p;
        String rutaArchivoSalidaArtista = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfExitFolder() + File.separator + 
                this.getNameOfExitArtistsData();
        fout = new FileOutputStream(rutaArchivoSalidaArtista);
        p = new PrintStream(fout);
        
        for(int i=0;i<this.artistas.size();i++){
            String albs = String.join(", ", this.artistas.get(i).getAlbumes());
            p.printf("|%-50s|%-50s|%-50s|%-50s|%-50s|%-50s|%-50s|%n",
                    this.artistas.get(i).getNombre(), this.artistas.get(i).getBiografia(),
                    this.artistas.get(i).getInstagram(), this.artistas.get(i).getTwitter(),
                    this.artistas.get(i).getFacebook(),this.artistas.get(i).getWikipedia(),
                    albs);
        }
        p.close();
        
    }

    public void exportarHTMLalbumes() throws FileNotFoundException {
        
        String rutaArchivoHTMLAlbums = System.getProperty("user.home") +
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfExitFolder() + File.separator + 
                this.getNameOfExitHTMLalbumFile();
        
        
        PrintWriter pw = new PrintWriter(rutaArchivoHTMLAlbums);
        
        pw.printf("<!DOCTYPE html>%n<HTML>%n<HEAD><meta charset=\"UTF-8\"><H1>FACTURAS APARTAMENTOS</H1></HEAD>%n<BODY>%n");
        pw.printf("<TABLE BORDER=1>%n");
        pw.printf("%s%n", Album.showEncabezado());
        for(Album a:this.albumes){
            pw.printf("%s%n", a.toTableRow());
        }
        pw.println("<TABLE>");
        pw.println("</BODY>\n</HTML>");
        pw.close();
        
    }

    public void añadirArtista(String nombre, String biografia, String instagram, String twitter, String facebook, String wikipedia, String[] albumes) {
        if(albumes[0].equals(";")){
            this.artistas.add(Artist.instanciarArtista(nombre,biografia,instagram,
                    twitter,facebook,wikipedia));
        }else{
            this.artistas.add(Artist.instanciarArtista(nombre,biografia,instagram,
                    twitter,facebook,wikipedia,albumes));
        }
    }

    public boolean existeArtist(String nombre) {
        boolean existe = false;
        
        for(int i=0;i<this.artistas.size();i++){
            if(this.artistas.get(i).getNombre().equals(nombre)){
                existe=true;
            }
        }
        return existe;
    }

    public boolean tieneAlbumes(String nombre) {
        boolean tiene=false;
        
        for(Album a:this.albumes){
            ArrayList<String>autoresAlbum=a.getInterpretes();
            for(int i=0;i<autoresAlbum.size();i++){
                if(autoresAlbum.get(i).equals(nombre)){
                    tiene=true;
                }
            }
        }
        
        return tiene;
    }

    public String[] getAlbum(String nombre) {
        String[]albums;
        int k=0;
        for(Album a:this.albumes){
            ArrayList<String>autoresAlbum=a.getInterpretes();
            for(int i=0;i<autoresAlbum.size();i++){
                if(autoresAlbum.get(i).equals(nombre)){
                    k++;
                }
            }
        }
        albums=new String[k];
        k=0;
        for(Album a:this.albumes){
            ArrayList<String>autoresAlbum=a.getInterpretes();
            for(int i=0;i<autoresAlbum.size();i++){
                if(autoresAlbum.get(i).equals(nombre)){
                    albums[k]=a.getTitulo();
                    k++;
                }
            }
        }
        
        return albums;
    }

    public void borrarArtista(String nombre) {
        for(int i=0;i<this.artistas.size();i++){
            if(this.artistas.get(i).getNombre().equals(nombre)){
                this.artistas.remove(i);
            }
        }
    }

    public void modificarArtista(String nombre, int opcion, String nuevo) {
        for(Artist a:this.artistas){
            if(a.getNombre().equals(nombre)){
                switch(opcion){
                    case 1:
                        a.setBiografia(nuevo);
                        break;
                    case 2:
                        a.setInstagram(nuevo);
                        break;
                    case 3:
                        a.setTwitter(nuevo);
                        break;
                    case 4:
                        a.setFacebook(nuevo);
                        break;
                    case 5:
                        a.setWikipedia(nuevo);
                        break;
                    default:
                        break;
                }
            }
        }
    }

    public String[] getArtistData(String nombre) {
        String[]data=new String[7];
        for(Artist a : this.artistas){
            if(a.getNombre().equals(nombre)){
                data=a.getArtistsDataToStringArray();
            }
        }
        return data;
    }

    public String[][] getAlbumesOrderByYear(String nombre) {
        String[][]data;
        Comparator<Album> cmp;
        ArrayList<Album>albumesArtista=new ArrayList<>();
        for(Album a:this.albumes){
            for(int i=0; i<a.getInterpretes().size();i++){
                if(a.getInterpretes().get(i).equals(nombre)){
                    albumesArtista.add(a);
                }
            }
        }
        
        cmp = new ComparadorPorAño();
        Collections.sort(albumesArtista,cmp);
        
        data=new String[albumesArtista.size()][];
        int k=0;
        for(Album a : albumesArtista){
            data[k]=a.getAlbumDataToStringArray();
            k++;
        }
        
        return data;
        
        
    }

    public void ordenarAlbumesAño() {
        Comparator<Album> cmp;
        cmp=new ComparadorPorAño();
        Collections.sort(this.albumes,cmp);
    }

    public void añadirPlayList(String nombre, int numCanc) {
        Random RR = new Random();
        ArrayList<Song>cancionesPlaylist=new ArrayList<>();
        for(int i=0;i<numCanc;i++){
            cancionesPlaylist.add(this.canciones.get(RR.nextInt(this.canciones.size())));
        }
        this.playlists.add(new PlayList(nombre,cancionesPlaylist));
    }

    public boolean existePlayList(String nombre) {
        boolean existe=false;
        
        for(PlayList p : this.playlists){
            if(p.getNombre().equals(nombre)){
                existe=true;
            }
        }
            return existe;
    }

    public String[][] getSongsPlayListData(String nombre) {
        ArrayList<Song>cancionesActuales=new ArrayList<>();
        for(PlayList p : this.playlists){
            if(p.getNombre().equals(nombre)){
                ArrayList<Song>cancionesPlaylist=p.getCanciones();
                cancionesActuales=this.canciones;
                this.canciones=cancionesPlaylist;
            }
        }
        String[][]data;
        data=this.getCancionesDataToStringMatriz();
        this.canciones=cancionesActuales;
        return data;
    }

    public boolean existeCancionInPlayList(String nombre, String nomCanc) {
        boolean existe=false;
        
        for(PlayList p:this.playlists){
            if(p.getNombre().equals(nombre)){
                for(Song s:p.getCanciones()){
                    if(s.getTitulo().equals(nomCanc)){
                        existe=true;
                    }
                }
            }
        }
        
        return existe;
    }

    public void borrarCancionInPlayList(String nombre, String nomCanc) {
        for(PlayList p:this.playlists){
            if(p.getNombre().equals(nombre)){
                for(int i=0;i<p.getCanciones().size();i++){
                    if(p.getCanciones().get(i).getTitulo().equals(nomCanc)){
                        p.getCanciones().remove(i);
                    }
                }
            }
        }
    }

    public int getNumPlayLists() {
        return this.playlists.size();
    }

    public String[][] getPlayListsDataToStringMatriz() {
        String[][]data=new String[this.playlists.size()][];
        int k=0;
        for(PlayList p:this.playlists){
            data[k]=p.getPlayListDataToStringArray();
            k++;
        }
        
        return data;
    }

    public boolean existeSong(String nomCanc) {
        boolean existe=false;
        
        for(Song s:this.canciones){
            if(s.getTitulo().equals(nomCanc)){
                existe=true;
            }
        }
        
        return existe;
    }

    public void añadirCancionPlayList(String nomPlay, String nomCanc) {
        Song añadir=new Song();
        for(Song s:this.canciones){
            if(s.getTitulo().equals(nomCanc)){
                añadir=s;
            }
        }
        for(PlayList p:this.playlists){
            if(p.getNombre().equals(nomPlay)){
                p.setCancion(añadir);
            }
        }
    }

    public boolean existeArchivoBinario() {
        boolean existe;
        String rutaArchivoBinario = System.getProperty("user.home")+
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder()+ File.separator +
                this.getNameOfBinaryFolder() + File.separator +
                this.getNameOfBinaryFile();
        
        File archivoBinario = new File(rutaArchivoBinario);
        Path rutaArchivo = archivoBinario.toPath();
        
        existe = Files.exists(rutaArchivo);
        
        return existe;
    }

    public boolean existeArchivoDatos() {
        boolean existe;
        String rutaArchivoAlbumes = System.getProperty("user.home")+
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfDataFolder() + File.separator +
                this.getNameOfAlbumDataFile();
        
        String rutaArchivoArtistas = System.getProperty("user.home")+
                File.separator + "Desktop" + File.separator +
                this.getNameOfFolder() + File.separator +
                this.getNameOfDataFolder() + File.separator +
                this.getNameOfArtistDataFile();
        
        File archivoDatosAlbumes = new File(rutaArchivoAlbumes);
        File archivoDatosArtistas = new File(rutaArchivoArtistas);
        Path rutaArchivoAlbums = archivoDatosAlbumes.toPath();
        Path rutaArchivoArtists= archivoDatosArtistas.toPath();
        
        existe = Files.exists(rutaArchivoAlbums);
        if(!existe){
            return existe;
        }
        existe = Files.exists(rutaArchivoArtists);
        
        return existe;
    }
    
    
    


}
    
    
    
    
    

