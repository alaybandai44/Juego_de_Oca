/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import Vistas.Jugador;
import Vistas.VistaModoJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;


public class ControladorModoJuego implements ActionListener{
    
    private VistaModoJuego modoJuego;
    private ControladorTablero ctrTablero;
    private ImageIcon IMAGEN_FICHA_1 = new ImageIcon("src/images/coche.png");  //MANDAMOS LAS IMAGENES DESDE EL CONTROLADOR DE MODO DE JUEGO PARA FUTURAS IMPLEMENTACIONES EN LAS QUE EL USUARIO PUEDA ELEGIR LA FICHA QUE QUIERA DESDE ESTA VISTA
    private ImageIcon IMAGEN_FICHA_2 = new ImageIcon("src/images/moto.png");

    public ControladorModoJuego(VistaModoJuego modoJuego){
        this.modoJuego=modoJuego;
    }
    
    /**
     * Metodo que evalua los ActionEvent de la VistaModoJuego
     * @param nombre 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        
        if(ae.getActionCommand().equalsIgnoreCase("jugadorUno")){
            modoJuego.visibleCampoJugadorUno(true);
            modoJuego.visibleEmpezar(true);
            modoJuego.anularJugadoresDos(Boolean.FALSE);
        }
        
        else if(ae.getActionCommand().equalsIgnoreCase("jugadorDos")){
            modoJuego.visibleCampoJugadorUno(true);
            modoJuego.visibleCampoJugadorDos(true);
            modoJuego.visibleEmpezar(true);
            modoJuego.anularJugadoresUno(Boolean.FALSE);
        }
       
        if(ae.getActionCommand().equalsIgnoreCase("JUGAR") && modoJuego.getNombreJugadorDos().isEmpty()) {
            crearTableroUnJugador(modoJuego.getNombreJugadorUno());
        } 
        
        else if(ae.getActionCommand().equalsIgnoreCase("JUGAR") && !modoJuego.getNombreJugadorDos().equalsIgnoreCase("player2")){
            try {
                crearTableroDosJugadores(modoJuego.getNombreJugadorUno(),modoJuego.getNombreJugadorDos());
            } catch (IOException ex) {
                Logger.getLogger(ControladorModoJuego.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Metodo que se llama cuando creamos un tablero con 2 jugadores
     * @param nombre 
     */
    public void crearTableroDosJugadores(String nombre1Jugador, String nombre2Jugador) throws IOException {
        Jugador jugador1 = new Jugador(nombre1Jugador, IMAGEN_FICHA_1);
        Jugador jugador2 = new Jugador(nombre2Jugador, IMAGEN_FICHA_2);
        ctrTablero = new ControladorTablero(jugador1, jugador2);
        modoJuego.setVisible(false);
        jugador1.con.anadirControlador(ctrTablero);
        jugador2.con.anadirControlador(ctrTablero);
    }
    
    /**
     * Metodo que se llama cuando creamos un tablero con un solo jugador
     * @param nombre 
     */
    public void crearTableroUnJugador(String nombre) {
        Jugador jugador1 = new Jugador(nombre, IMAGEN_FICHA_1);
        try {
            ctrTablero = new ControladorTablero(jugador1);
        } catch (IOException ex) {
            Logger.getLogger(ControladorModoJuego.class.getName()).log(Level.SEVERE, null, ex);
        }
        modoJuego.setVisible(false);
        jugador1.con.anadirControlador(ctrTablero);
    }
}
