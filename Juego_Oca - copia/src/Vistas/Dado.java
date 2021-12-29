/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
import Controladores.ControladorTablero;
import Controladores.ControladorDado;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/**
 *
 * @author Javier_p
 */
public class Dado extends JButton{
     private ImageIcon BOTON_DADOS = new ImageIcon("src/images/botonDados.jpeg");
    private ImageIcon cara1,cara2,cara3,cara4,cara5,cara6;
    private String imagenes[]={"src/images/dado1.png","src/images/dado2.png","src/images/dado3.png","src/images/dado4.png","src/images/dado5.png","src/images/dado6.png"};
    private ImageIcon caraDados[]={cara1,cara2,cara3,cara4,cara5,cara6};
    private ImageIcon caraTirada;
    private ControladorTablero con;
    public ControladorDado conDado;
    
    public Dado(ControladorTablero controla){
        super();
        this.setActionCommand("dado");
        this.con = controla;
        this.setIcon(new ImageIcon(BOTON_DADOS.getImage().getScaledInstance(130, 130, Image.SCALE_SMOOTH)));
        this.conDado = new ControladorDado(this);
        cargaImagenesIconos();
        this.addActionListener(conDado);
    }
    
    /**
     * Metodo que se instancia cuando el usuario realiza una nueva tirada
     * @param tirada 
     */
    public void nuevaTirada(int tirada) {
        caraTirada = new ImageIcon();
        caraTirada = caraDados[tirada-1];
        
        con.vis.manoVisible();
        con.vis.mostrarTiradaDado(caraTirada);
        this.setEnabled(false);
    }

    
    /**
     * Metodo que al llamarse carga las diferentes imagenes de las posibles caras del dado
     */
    private void cargaImagenesIconos() {
        for (int i = 0; i < 6; i++) {
            caraDados[i]= new ImageIcon (imagenes[i]);
        }
    }
}
