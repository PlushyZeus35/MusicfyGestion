/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicaFinal2019;
import static com.coti.guitools.AuxKbd.readInt;
import java.util.Scanner;
import view.View;


/**
 *
 * @author Borja Lorenzo
 */
public class PracticaFinal2019 {

    /**
     * @param args the cond line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        View v = new View();
        
        String menus[]={"%nREPRODUCCION DE MUSICA VIA STREAMING%n"
                + "%n1.-Generacion Aleatoria."
                + "%n2.-Archivos"
                + "%n3.-Album"
                + "%n4.-Artista"
                + "%n5.-PlayList"
                + "%n6.-Canciones"
                + "%nQ.-Salir%n"
                + "Opcion a elegir: ",
                    "%nGESTION DE ARCHIVOS%n"
                + "%n1.-Exportar artistas"
                + "%n2.-Exportar albumes"
                + "%nOpcion a elegir: ", 
                "%nOPERACIONES CON ALBUMES%n"
                + "%n1.-Alta (Añadir un album)"
                + "%n2.-Baja (Borrar un album)"
                + "%n3.-Modificar un album"
                + "%n4.-Consulta de un album"
                + "%nOpcion a elegir: ",
                "%nOPERACIONES CON ARTISTAS%n"
                + "%n1.-Alta(Añadir un artista)"
                + "%n2.-Baja(Borrar un artista)"
                + "%n3.-Modificar un artista"
                + "%n4.-Consulta de albumes de un artista"
                + "%nOpcion a elegir: ", 
                "%nOPERACIONES CON PLAYLISTS%n"
                + "%n1.-Alta (Añadir una PlayList)"
                + "%n2.-Eliminar cancion de PlayList"
                + "%n3.-Añadir cancion a PlayList"
                + "%nOpcion a elegir: "};
        v.arranquePrograma();
        v.runMenu(menus);
        v.salidaPrograma();
        
    }
    
}
