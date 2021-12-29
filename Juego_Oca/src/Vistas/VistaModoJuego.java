package Vistas;


import Controladores.ControladorModoJuego;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.TextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaModoJuego extends JFrame {

    private JButton jugadoresUno, jugadoresDos,empezar;
    private TextField nombreJugadorUno, nombreJugadorDos;
    private ControladorModoJuego ctr;
    private FondoPanelModos fondo = new FondoPanelModos();
    private JLabel textoNombre, textoNombre2;

    private String[] imagenes = {"src/images/Jugador1.jpg", "src/images/Jugador2.jpg"};

    public VistaModoJuego() {
        super("La OCA - Modos de Juego");
        propiedadesVentana();
        anadirFondo();
        inicializarElementos();
        anadirElementos();
        establecerLocalizacionElementos();
        crearEscuchadores();
        cambiarFuente();
    }
    
    public VistaModoJuego(VistaTablero vis) {
        super("La OCA - Modos de Juego");
        vis.setVisible(false);
        propiedadesVentana();
        anadirFondo();
        inicializarElementos();
        anadirElementos();
        establecerLocalizacionElementos();
        crearEscuchadores();
        cambiarFuente();
    }
    
    private void inicializarElementos() {
        ctr = new ControladorModoJuego(this);
        jugadoresUno = new JButton();
        jugadoresUno.setActionCommand("jugadorUno");
        jugadoresUno.setIcon(new ImageIcon(imagenes[0]));
        jugadoresDos = new JButton();
        jugadoresDos.setActionCommand("jugadorDos");
        jugadoresDos.setIcon(new ImageIcon(imagenes[1]));
        nombreJugadorUno = new TextField();
        nombreJugadorUno.setVisible(false);
        nombreJugadorDos = new TextField();
        nombreJugadorDos.setVisible(false);
        empezar=new JButton("JUGAR");
        empezar.setVisible(false);
        textoNombre = new JLabel("Introduzca el Nombre del Jugador 1");
        textoNombre.setVisible(false);
        textoNombre2 = new JLabel("Introduzca el Nombre del Jugador 2");     
        textoNombre2.setVisible(false);
    }
    
    private void establecerLocalizacionElementos() {
        jugadoresUno.setBounds(110, 120, 352, 110);
        jugadoresUno.repaint();
        jugadoresDos.setBounds(560, 120, 352, 110);
        jugadoresDos.repaint();
        textoNombre.setBounds(110, 250, 310, 35);
        nombreJugadorUno.setBounds(110, 280, 250, 35);
        textoNombre.setForeground(Color.white);
        textoNombre2.setBounds(560, 250, 310, 35);
        textoNombre2.setForeground(Color.white);
        nombreJugadorDos.setBounds(560, 280, 250, 35);
        empezar.setBounds(800, 350, 100, 35);
    }
    
    /**
     * Metodo que edita las propiedades de la ventana
     */
    private void propiedadesVentana() {
        this.setLayout(null);
        this.setBounds(500, 300, 1018, 500);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    /**
     * Metodo que añade los elementos al panel principal(panel que se usa como fondo del Frame)
     */
    private void anadirElementos(){
        fondo.add(jugadoresUno);
        fondo.add(jugadoresDos);
        fondo.add(nombreJugadorUno);
        fondo.add(textoNombre);
        fondo.add(nombreJugadorDos);
        fondo.add(textoNombre2);
        fondo.add(empezar);
    }

    /**
     * Metodos para anular el boton de 1 jugador o 2 jugadores
     * @param SiAnular 
     */
    public void anularJugadoresUno(Boolean SiAnular) {
        this.jugadoresUno.setEnabled(SiAnular);
    }

    public void anularJugadoresDos(Boolean SiAnular) {
        this.jugadoresDos.setEnabled(SiAnular);
    }

    /**
     * Metodos para hacer visibles los campos de recoger nombre una vez que el usuario pulse 1 de las 2 opciones
     * @param aparecer 
     */
    public void visibleCampoJugadorUno(boolean aparecer) {
        this.nombreJugadorUno.setVisible(aparecer);
        textoNombre.setVisible(aparecer);
    }

    public void visibleCampoJugadorDos(boolean aparecer) {
        this.nombreJugadorDos.setVisible(aparecer);
        textoNombre2.setVisible(aparecer);
    }

    /**
     * Metodo que añade los escuchadores de los distintos elementos que lo necesiten de nuestra VistaModoJuego
     */
    private void crearEscuchadores() {
        jugadoresUno.addActionListener(ctr);
        jugadoresDos.addActionListener(ctr);
        empezar.addActionListener(ctr);
    }
    
    /**
     * Metodo gracias al cual podemos cambiar la Fuente de los distintos elementos que creamos conveniente
     */
    private void cambiarFuente() {
        Font f = new Font(Font.DIALOG, Font.BOLD, 18);
        textoNombre.setFont(f);
        textoNombre2.setFont(f);
        nombreJugadorUno.setFont(f);
        nombreJugadorDos.setFont(f);
    }

    /**
     * Geters que retonan el nombre de los distintos jugadores
     * @return 
     */
    public String getNombreJugadorUno() {
        return nombreJugadorUno.getText();
    }

    public String getNombreJugadorDos() {
        return nombreJugadorDos.getText();
    }

    /**
     * Metodo para hacer visible el boton Empezar
     * @param SiVisible 
     */
    public void visibleEmpezar(boolean SiVisible) {
        this.empezar.setVisible(SiVisible);
    }
    
    /**
     * Metodo para establecer el panel de fondo de la ventana
     */
    private void anadirFondo() {
        this.setContentPane(fondo);
        fondo.setLayout(null);
    }
    
}

/**
 * Creamos esta clase para poder establecer una imágen de fondo de pantalla de la ventana
 * Una de las cualidades de hacerlo de esta manera es que el tamaño se ajusta a la dimensión de la ventana, aunque esta varie
 * @author 34633
 */
class FondoPanelModos extends JPanel {

    private Image fondo;

    @Override
    public void paint(Graphics g) {
        fondo = new ImageIcon(getClass().getResource("/images/fondo_modosJuego.jpg")).getImage();
        g.drawImage(fondo, 0, 0, this.getWidth(), this.getHeight(), this);

        this.setOpaque(false);
        super.paint(g);
    }
}
