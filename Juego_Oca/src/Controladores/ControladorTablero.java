
package Controladores;

import Vistas.Jugador;
import Logica.LogicaJuego;
import Vistas.VistaTablero;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*

 */
public class ControladorTablero extends WindowAdapter implements KeyListener, MouseListener{
    
    public VistaTablero vis;
    public Jugador jugador1,jugador2;
    private LogicaJuego logica;
    
    /**
     * Constructor con 2 jugadores
     * @param jugador1
     * @param jugador2 
     */
    public ControladorTablero(Jugador jugador1,Jugador jugador2) throws IOException{
        this.jugador1=jugador1;
        this.jugador2=jugador2;
        
        vis = new VistaTablero(this);
        logica = new LogicaJuego(jugador1, jugador2, vis);
        vis.boton.conDado.establecerLogica(logica);
        vis.setMarcador(jugador1.getNombre()+" VS "+jugador2.getNombre());
    }
    
    /**
     * Constructor con 1 jugador
     * @param jugador1 
     */
    public ControladorTablero(Jugador jugador1) throws IOException{
        this.jugador1=jugador1;
        vis = new VistaTablero(this);
        vis.mostrarCronometro();
        logica = new LogicaJuego(jugador1, vis);
        vis.boton.conDado.establecerLogica(logica);
        vis.setMarcador(jugador1.getNombre());
    }
    
    /**
     * Método para avisar a la llogica que el juego ha terminado
     */
    public void finJuego(){
        logica.finJuego();
    }

    /**
     * Funcionalidad para poder tirar los dados desde la tecla del espacio
     * @param ke 
     */
    @Override
    public void keyTyped(KeyEvent ke) {
        char letra = ke.getKeyChar();
        if(letra==(' ')){
            vis.boton.conDado.tirada();
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {}

    @Override
    public void keyReleased(KeyEvent ke) {}
    
    
    /**
     * Aquí establecemos las funcionalidades que haremos cuando el usuario pulse la x de la ventana
     * @param e 
     */
    @Override
    public void windowClosing(WindowEvent e){
        
        int opcion = JOptionPane.showConfirmDialog(vis, "¿Desea cerrar la aplicación?");
        
        if(opcion == 0){  //Si el usuario pulsa la primera opcion "SI", la ventana se cerrará
            vis.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
            
        }else{  //Si el usuario cualquiera de las otras opciones, la ventana NO se cerrará
            vis.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE );
        }
    }

    /**
     * MousClicked para determinar la funcionalidad del Huevo de Pascua, de tal manera que si se clica en el pajaro en el modo 1 jugador
     * el cronómetro se detendrá y si lo hace en el modo de 2 jugadores no pasará nada.
     * @param me 
     */
    @Override
    public void mouseClicked(MouseEvent me) {
        if(logica.numeroJugadoresPartida() == 1){
            vis.cronometro.detenerCronometro();
            JOptionPane.showMessageDialog(vis, "¡Una oca siempre ayuda a un compañero!\n Ahora tendrás todo el tiempo del mundo para GANAR LA PARTIDA");
        }
    }

    //Diferentes métodos MouseListener que no implementaremos funcionalidad ya que no nos son necesarios
    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
    
}
