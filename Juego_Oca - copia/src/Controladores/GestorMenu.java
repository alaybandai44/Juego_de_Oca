
package Controladores;

import Vistas.VistaTablero;
import Vistas.VistaReglas;
import Vistas.VistaModoJuego;
import Controladores.ControladorTablero;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
  
 */
public class GestorMenu implements ActionListener{
    ControladorTablero con;
    VistaTablero vis;
    VistaReglas vistaReglas;
    
    public GestorMenu(ControladorTablero c, VistaTablero vis){
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
        }else if(ae.getActionCommand().equals("Opcion 3")){
            
        }
    }
}
