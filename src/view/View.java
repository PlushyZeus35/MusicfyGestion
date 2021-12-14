/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import static com.coti.guitools.AuxKbd.readInt;
import com.coti.tools.Esdia;
import static com.coti.tools.Esdia.readString;
import static com.coti.tools.Esdia.readString_ne;
import static com.coti.tools.Esdia.yesOrNo;
import static com.coti.tools.OpMat.join;
import static com.coti.tools.OpMat.printToScreen;
import static com.coti.tools.OpMat.printToScreen3;
import controller.Controller;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Borja Lorenzo
 */
public class View {
   Controller c = new Controller();
   //save y load
    public void runMenu(String[] menu) {
        String opcion;
        boolean salir=false;
        do{
            opcion=readString_ne(menu[0]).toUpperCase();
            switch(opcion){
                case "1":
                    this.generacionAleatoria();
                    break;
                case "2":
                    this.archivos(menu[1]);
                    break;
                case "3":
                    this.album(menu[2]);
                    break;
                case "4":
                    this.artista(menu[3]);
                    break;
                case "5":
                    this.playlist(menu[4]);
                    break;
                case "6":
                    this.canciones();
                    break;
                case "Q":
                    salir=yesOrNo("Desea realmente salir? ");
                    break;
                default:
                    System.out.println("OPCION INCORRECTA");
                    break;
            }
        }while(salir!=true);
    }

    private void añadirAlbum() {
        Scanner sc = new Scanner(System.in);
        String titulo, duracion, tipo;
        String[] interpretes, canciones;
        int año, numCanciones, i, minutos, segundos;
        
        titulo=readString_ne("Introduce el titulo del album: ");
        i=readInt("Cuantos interpretes participan en el album: ");
        interpretes=new String[i];
        for(int j=0; j<i;j++){
            System.out.printf("%nNombre del artista %d: ", j+1);
            interpretes[j]=sc.nextLine();
        }
        año=readInt("Año del album: ");
        
        System.out.printf("%nIntroduce la duración del album:%n");
        minutos=readInt("Minutos: ");
        segundos=readInt("Segundos: ");
        if(minutos==0){
            duracion=String.format("%d segundos", segundos);
        }else if(segundos==0){
            duracion=String.format("%d minutos", minutos);
        }else{
            duracion=String.format("%d minutos %d segundos", minutos, segundos);
        }
        
        do{
        numCanciones=readInt("Introduce el numero de canciones del album: ");
        }while(numCanciones<=0);
        
        canciones=new String[numCanciones];
        
        if(numCanciones==1){
            tipo="sencillo";
            canciones[0]=titulo;
        }else{
            tipo="álbum";
            for(int j=0;j<numCanciones;j++){
                System.out.printf("%nTitulo de la cancion %d: ", j+1);
                canciones[j]=sc.nextLine();
            }
        }
        
        c.añadirAlbum(titulo,interpretes,año,duracion,numCanciones,tipo,canciones);
        
        System.out.printf("%nAlbum [%s] añadido a la colección con EXITO%n", titulo);
            
        
    }

    private void mostrarAlbumes() {
        String[][] data;
        data=c.getAlbumsData();
        
        
        if(c.getNumAlbumes()==0){
            System.out.println("no hay datos aun");
            
        }else{
            try {
                 printToScreen3(data);
             } catch (Exception ex) {
               Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void mostrarCanciones() {
        String[][] data;
        data=c.getSongsData();
        if(c.getNumCanciones()==0){
            System.out.println("NO hay datos aun");
        }else{
            try {
                printToScreen3(data);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    private void saveBinaryData() {
        c.saveBinaryData();
    }

    private void loadBinaryData() {
        c.loadBinaryData();
    }

    private void mostrarArtistas() {
        String[][] artistsData;
        if(c.getNumArtists()==0){
            System.out.println("Lista de Artistas vacia");
        }else{
            artistsData = c.getArtistsData();
            
            try {
                printToScreen3(artistsData);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void borrarAlbum() {
        this.mostrarAlbumes();
        System.out.println("");
        String titulo=readString_ne("Introduce el nombre del album a borrar: ");
        if(c.existeAlbum(titulo)){
            c.borrarAlbum(titulo); 
            System.out.printf("%nEl album %s y sus canciones se han borrado correctamente%n",titulo);
        }else{
            System.out.printf("%nEl album %s no existe%n",titulo);
        }
    }

    private void modificarAlbum() {       
        int anio=0;
        String duracion="No";
        this.mostrarAlbumes();
        System.out.println("");
        String titulo=readString_ne("Introduce el titulo del album a modificar:");
        System.out.println("");
        if(c.existeAlbum(titulo)){
            String[][]album;
            album=c.getAlbumData(titulo);
            
            try {
                printToScreen3(album);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.printf("%nQue atributo quiere modificar?%n"
                    + "1.-Año%n"
                    + "2.-Duracion%n");
            int opcion=readInt("Opcion: ");
            switch(opcion){
                case 1:
                    anio=readInt("Introduce el año nuevo: ");
                    c.modificarAlbum(titulo,anio,duracion);
                    System.out.printf("%nModificación del año realizada con EXITO%n");
                    break;
                case 2:
                    int min=readInt("Numero de minutos de duracion: ");
                    int seg=readInt("Numero de segundos de duracion: ");
                    if(min==0){
                        duracion=String.format("%d segundos", seg);
                    }else if(seg==0){
                        duracion=String.format("%d minutos", min);
                    }else{
                        duracion=String.format("%d minutos %d segundos", min, seg);
                    }
                    c.modificarAlbum(titulo,anio,duracion);
                    System.out.printf("%nModificación de la duracion realizada con EXITO%n");
                    break;
                default:
                    System.out.println("Opcion incorrecta");
                    break;
            }
            
        }else{
            System.out.printf("%nEl album %s no existe%n",titulo);
        }
    }

    private void consultaAlbum() {
        System.out.println("");
        String titulo=readString_ne("Introduce el titulo del album a consultar: ");
        System.out.println("");
        if(c.existeAlbum(titulo)){
            String[][]albumData;
            albumData=c.getAlbumData(titulo);
            try {
                printToScreen3(albumData);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.printf("%nEl album %s no existe%n", titulo);
        }
        System.out.println("");
    }

    private void generacionAleatoria() {
       try {
           c.generacionAleatoria();
       } catch (Exception ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
        System.out.printf("%nColeccion inicializadas con valores aleatorios%n");
    }

    private void loadTextData() {
       try {
           c.loadTextData();
       } catch (Exception ex) {
           System.out.println("ERROR: no se pudo importar datos de disco. ");
       }
    }

    private void exportarArtistas() {
       try {
           c.exportarArtistas();
           System.out.printf("%nArtistas exportados a disco con EXITO%n");
       } catch (Exception ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    private void exportarHTMLalbumes() {
       try {
           c.exportarHTMLalbumes();
           System.out.printf("%nAlbumes exportados a disco en HTML con EXITO%n");
       } catch (FileNotFoundException ex) {
           Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
       }
    }

    private void añadirArtista() {
        Scanner sc = new Scanner(System.in);
        String[]albumes;
        System.out.println("");
        String nombre = readString_ne("Introduce el nombre del artista: ");
        System.out.printf("%nIntroduce la biografia de %s: ", nombre);
        String biografia = sc.nextLine();
        
        System.out.printf("%nIntroduce el instagram de %s: ", nombre);
        String instagram = sc.nextLine();
        
        System.out.printf("%nIntroduce el twitter de %s: ", nombre);
        String twitter = sc.nextLine();
        
        System.out.printf("%nIntroduce el facebook de %s: ", nombre);
        String facebook = sc.nextLine();
        
        System.out.printf("%nIntroduce la wikipedia de %s: ", nombre);
        String wikipedia = sc.nextLine();
        
        System.out.printf("%nCuantos albumes tiene %s: ", nombre);
        int numAlbum = sc.nextInt();
        
        if(numAlbum==0){
            albumes=new String[1];
            albumes[0]=";";
        }else{
            albumes=new String[numAlbum];
            for(int i=0;i<numAlbum;i++){
                //System.out.printf("%nIntroduce el nombre del album %d: ", i+1);
                albumes[i]=readString("Introduce el nombre del album ");
            }
        }
        c.añadirArtista(nombre,biografia,instagram,twitter,facebook,wikipedia,albumes);
        System.out.printf("%nArtista [%s] añadido a la colección con EXITO%n", nombre);
        
    }

    private void borrarArtista() {
        System.out.println("");
        this.mostrarArtistas();
        System.out.println("");
        String nombre = readString_ne("Introduce el nombre del artista a borrar: ");
        String[] albumes;
        if(c.existeArtista(nombre)){
            if(c.tieneAlbumes(nombre)){
                albumes = c.getAlbum(nombre);
                System.out.printf("%nLos albumes %s estan en la coleccion de albumes%n", Arrays.toString(albumes));
            }else{
                c.borrarArtista(nombre);
                System.out.printf("%nEl artista %s se ha eliminado de la colección con EXITO", nombre);
            }
        }else{
            System.out.printf("%nEl artista %s no existe%n", nombre);
        }
        
    }

    private void modificarArtista() {
        System.out.println("");
        this.mostrarArtistas();
        System.out.println("");
        String nombre = readString_ne("Introduce el nombre del artista a modificar: ");
        String nuevo="";
        if(c.existeArtista(nombre)){
            String[][]artistData=c.getArtistData(nombre);
            try {
                printToScreen3(artistData);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            System.out.printf("%nQue atributo desea modificar?%n"
                    + "1.-Biografia%n"
                    + "2.-Instagram%n"
                    + "3.-Twitter%n"
                    + "4.-FaceBook%n"
                    + "5.-Wikipedia%n");
            int opcion = readInt("Opcion: ");
            switch(opcion){
                case 1:
                    nuevo = readString("Nuevo valor de Biografia: ");
                    break;
                case 2:
                    nuevo = readString("Nuevo valor de Instagram: ");
                    break;
                case 3:
                    nuevo = readString("Nuevo valor de Twitter: ");
                    break;
                case 4:
                    nuevo = readString("Nuevo valor de Facebook: ");
                    break;
                case 5: 
                    nuevo =  readString("Nuevo valor de Wikipedia: ");
                    break;
                default:
                    System.out.printf("%nOpcion Incorrecta%n");
                    break;
            }
            c.modificarArtista(nombre,opcion,nuevo);
            
            System.out.printf("%nModificación de %s realizada con EXITO.%n", nombre);
                    
        }else{
            System.out.printf("%nEl artista %s NO EXISTE%n", nombre);
        }
    }

    private void mostrarAlbumesArtista() {
        String nombre=readString_ne("Introduce el nombre del artista a mostrar los albumes: ");
        
        if(c.tieneAlbumes(nombre)){
            String[][]albumes=c.getAlbumesOrderByYear(nombre);
            try {
                printToScreen3(albumes);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.printf("%nEl artista %s no tiene albumes registrados.%n", nombre);
        }
    }

    /*private void ordenarAlbumesAño() {
        c.ordenarAlbumesAño();
    }
    */
    private void añadirPlayList() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");
        String nombre=readString_ne("Introduce el nombre de la nueva PlayList: ");
        System.out.printf("%nCuantas canciones va a tener la PlayList %s?: ",nombre);
        int numCanc=sc.nextInt();
        
        c.añadirPlayList(nombre,numCanc);
        System.out.printf("%nPlayList [%s] añadida a la colección con EXITO%n", nombre);
        
    }

    private void eliminarCancionPlayList() {
        if(c.getNumPlayLists()==0){
            System.out.printf("%nLa colección de PlayLists está vacia!%n");
        }else{
        System.out.println("");
        this.mostrarPlayLists();
        System.out.println("");
        String nombre=readString_ne("Introduce el nombre de la PlayList a eliminar una cancion: ");
        if(c.existePlayList(nombre)){
            String[][]dataPlaylist=c.getDataPlayList(nombre);
            try {
                printToScreen3(dataPlaylist);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
            String nomCanc=readString_ne("Introduce el nombre la cancion a eliminar: ");
            if(c.existeCancionInPlayList(nombre,nomCanc)){
                c.borrarCancionInPlayList(nombre,nomCanc);
                System.out.printf("%nLa cancion %s se ha eliminado de la PlayList %s con EXITO%n", nombre,nomCanc);
            }else{
                System.out.printf("%nLa cancion %s no existe en la PlayList %s%n", nomCanc,nombre);
            }
        }else{
            System.out.printf("%nLa PlayList %s no existe en la coleccion%n", nombre);
        }
        }
    }

    private void añadirCancionPlayList() {
        Scanner sc = new Scanner(System.in);
        if(c.getNumPlayLists()==0){
            System.out.printf("%nLa colección de PlayLists esta vacia!%n");
        }else{
        System.out.println("");
        this.mostrarCanciones();
        System.out.println("");
        String nomCanc=readString_ne("Nombre de cancion a añadir: ");
        if(c.existeSong(nomCanc)){
            this.mostrarPlayLists();
            System.out.printf("%nNombre de PlayList a la que añadir la cancion [%s]: ", nomCanc);
            String nomPlay=sc.nextLine();
            if(c.existePlayList(nomPlay)){
                c.añadirCancionPlayList(nomPlay,nomCanc);
            }else{
                System.out.printf("%nLa PlayList %s no existe en la coleccion%n", nomPlay);
            }
        }else{
            System.out.printf("%nLa cancion %s no existe en la coleccion%n", nomCanc);
        }
        }
    }

    private void mostrarPlayLists() {
        String[][]playlistsData;
        if(c.getNumPlayLists()==0){
            System.out.printf("%nLa Coleccion de PlayList está vacia%n");
        }else{
            playlistsData=c.getPlayListsData();
            try {
                printToScreen3(playlistsData);
            } catch (Exception ex) {
                Logger.getLogger(View.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void arranquePrograma() {
        
        
        
        if(c.existeArchivoBinario()==true){
            this.loadBinaryData();
            System.out.printf("%nSe han importado todos los datos del fichero binario%n");
        }else{
            if(c.existeArchivosDatos()==true){
                try {
                    this.loadTextData();
                } catch (Exception ex) {
                    System.out.printf("%nERROR: no se ha podido importar los datos de los archivos de texto%n");
                }
                System.out.printf("%nSe han importado todos los datos del fichero de datos%n");
            }else{
                System.out.printf("%nEl archivo binario y el archivo de datos no existen%n");
            }
        }
        
    }
    
    public void salidaPrograma(){
        this.saveBinaryData();
    }

    private void archivos(String subMenu) {
        String opcion = readString(subMenu);
        switch(opcion){
            case "1":
                this.exportarArtistas();
                break;
            case "2":
                this.exportarHTMLalbumes();
                break;
            default:
                System.out.printf("%nOpcion incorrecta%n");
                break;
        }
        String nada=readString("Introduce [INTRO] para continuar");
        
    }

    private void album(String subMenu) {
        String opcion = readString(subMenu);
        switch(opcion){
            case "1":
                this.añadirAlbum();
                break;
            case "2":
                this.borrarAlbum();
                break;
            case "3":
                this.modificarAlbum();
                break;
            case "4":
                this.consultaAlbum();
                break;
            default:
                System.out.printf("%nOpcion incorrecta%n");
                break;
        }
        String nada=readString("Introduce [INTRO] para continuar");
    }

    private void artista(String subMenu) {
        String opcion = readString(subMenu);
        switch(opcion){
            case "1":
                this.añadirArtista();
                break;
            case "2":
                this.borrarArtista();
                break;
            case "3":
                this.modificarArtista();
                break;
            case "4":
                this.mostrarAlbumesArtista();
                break;
            default:
                System.out.printf("%nOpcion incorrecta%n");
                break;
        }
        String nada=readString("Introduce [INTRO] para continuar");
    }

    private void playlist(String subMenu) {
        String opcion = readString(subMenu);
        switch(opcion){
            case "1":
                this.añadirPlayList();
                break;
            case "2":
                this.eliminarCancionPlayList();
                break;
            case "3":
                this.añadirCancionPlayList();
                break;
            default:
                System.out.printf("%nOpcion incorrecta%n");
                break;
        }
        String nada=readString("Introduce [INTRO] para continuar");
    }

    private void canciones() {
        System.out.println("");
        this.mostrarCanciones();
        String nada=readString("Introduce [INTRO] para continuar");
    }

    
    
    
   
}
