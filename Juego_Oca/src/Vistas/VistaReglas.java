/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 *
 * @author Javier_p
 */
public class VistaReglas extends JFrame{
      private ImageIcon fotoOca = new ImageIcon("src/images/oca70.png");
    private ImageIcon fotoPuente = new ImageIcon("src/images/puente3.png");
    private ImageIcon fotoParking = new ImageIcon("src/images/parking.png");
    private ImageIcon fotoCarcel = new ImageIcon("src/images/carcel.png");
    private ImageIcon fotoMuerte = new ImageIcon("src/images/muerte.png");
    private JLabel normasOca, oca, normasPuente, puente, normasParking, parking, normasCarcel, carcel,normasMuerte, muerte;
    private Font FUENTE_LABELS = new Font(Font.MONOSPACED, Font.BOLD, 16);;
   
    public VistaReglas(){
        super("El Juego de la OCA - Reglas");
        propiedadesVentana();
        iniciarElementos();
        anadirElementos();
        posicionesElementos();
        cambiarFuenteLabels();
    }
    
    /**
     * Método que establece las propiedades de la ventana
     */
    private void propiedadesVentana() {
        this.setLayout(null);
        this.setBounds(500, 200, 900, 400);
        this.setVisible(true);
        this.setResizable(false);
        
        this.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e){
                ocultarVentana();
            }
        });
    }
    
    /**
     * Metodo que oculta la ventana
     */
    public void ocultarVentana(){
        this.setVisible(false);
    }

    /**
     * Metodo que inicializa los diferentes elementos de la ventana
     */
    private void iniciarElementos() {
        oca = new JLabel();
        oca.setIcon(fotoOca);
        puente = new JLabel();
        puente.setIcon(fotoPuente);
        parking = new JLabel();
        parking.setIcon(fotoParking);
        carcel = new JLabel();
        carcel.setIcon(fotoCarcel);
        muerte = new JLabel();
        muerte.setIcon(fotoMuerte);
        normasOca = new JLabel("OCAS --> Se avanza a la siguiente oca y se vuelve a tirar.");
        normasPuente = new JLabel("PUENTE --> Se avanza o retrocede hasta el otro puente y se vuelve a tirar.");
        normasParking = new JLabel("PARKING --> El jugador quedará retenido durante un turno.");
        normasCarcel = new JLabel("CARCEL --> Se pierden 5 turnos.");
        normasMuerte = new JLabel("MUERTE --> Se vuelve a empezar desde la casilla de Salida.");
    }

    /**
     * Metodo que añade los diferentes elementos de la ventana
     */
    private void anadirElementos() {
        this.add(oca);
        this.add(muerte);
        this.add(puente);
        this.add(parking);
        this.add(carcel);
        
        this.add(normasOca);
        this.add(normasPuente);
        this.add(normasParking);
        this.add(normasCarcel);
        this.add(normasMuerte);
    }

    /**
     * Metodo que establece las diferentes posiciones de los elementos de la ventana
     */
    private void posicionesElementos() {
        oca.setBounds(35, 30, 100, 50);
        puente.setBounds(20, 80, 100, 80);
        parking.setBounds(35, 150, 100, 50);
        carcel.setBounds(35, 210, 100, 50);
        muerte.setBounds(40, 290, 100, 70);
        
        normasOca.setBounds(130, 30, 800, 50);
        normasPuente.setBounds(130, 90, 800, 50);
        normasParking.setBounds(130, 150, 800, 50);
        normasCarcel.setBounds(130, 210, 800, 50);
        normasMuerte.setBounds(130, 290, 800, 50);
    }

    /**
     * Metodo gracias al cual se cambia la fuente de determinados elementos de la ventana
     */
    private void cambiarFuenteLabels() {
        normasOca.setFont(FUENTE_LABELS);
        normasPuente.setFont(FUENTE_LABELS);
        normasParking.setFont(FUENTE_LABELS);
        normasCarcel.setFont(FUENTE_LABELS);
        normasMuerte.setFont(FUENTE_LABELS);
        normasOca.setFont(FUENTE_LABELS);
    }
}
