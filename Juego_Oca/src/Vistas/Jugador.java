/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
import Controladores.ControladorJugador;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
/**
 *
 * @author Javier_p
 */
public class Jugador extends JLabel{
     private String nombre;
     public ControladorJugador con;
    
    public Jugador(String nombre, ImageIcon imagenFicha){
        this.nombre = nombre;
        con = new ControladorJugador(this);
        this.setIcon(imagenFicha);
    }

    /**
     * Metodo que retorna el nombre del jugador
     * @return 
     */
    public String getNombre() {
        return nombre;
    }
}
