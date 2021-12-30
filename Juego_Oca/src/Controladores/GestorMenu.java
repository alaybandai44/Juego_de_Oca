
package Controladores;

import Vistas.VistaTablero;
import Vistas.VistaReglas;
import Vistas.VistaModoJuego;
import Controladores.ControladorTablero;
import Guardado.CronometroSave;
import Guardado.JugadorPosicionPenalSave;
import Vistas.Cronometro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
  
 */
public class GestorMenu implements ActionListener{
    ControladorTablero con;
    VistaTablero vis;
    VistaReglas vistaReglas;
    CronometroSave partida;
    JugadorPosicionPenalSave partida2;
    
    public GestorMenu(ControladorTablero c, VistaTablero vis) throws IOException{
        con = c;
        this.vis = vis;
        
    }

    /**
     * Metodo ActionListener opciones del menu del frame
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("Nueva Partida")){
            VistaModoJuego nueva = new VistaModoJuego(vis);
        }else if(ae.getActionCommand().equals("Reglas")){
            vistaReglas = new VistaReglas();
        }else if(ae.getActionCommand().equals("Guardar Partida")){
            try {
                 partida = new CronometroSave(vis.cronometro);
                 partida2= new JugadorPosicionPenalSave(vis.con.jugador1.con);
                
            } catch (IOException ex) {
                Logger.getLogger(GestorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else if(ae.getActionCommand().equalsIgnoreCase("Carga Partida")){
            try {      
                
                partida = new CronometroSave();
                 Cronometro cronometro=partida.cargaPartida();
                vis.cronometro.setCronometro(cronometro.getCronometro());
                vis.cronometro.setContador(cronometro.getContador());
                 partida2= new JugadorPosicionPenalSave();
                ControladorJugador jugador = partida2.cargaPartida();
                vis.con.jugador1.con.setPenalizacion(jugador.getPenalizacion());
                int posicion=jugador.getPosicion();
                vis.con.jugador1.con.moverCasilla(posicion, true, true);
            } catch (IOException ex) {
                Logger.getLogger(GestorMenu.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(GestorMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
                        }
    }
}
