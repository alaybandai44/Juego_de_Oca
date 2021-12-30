/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;

import Controladores.ControladorTablero;
import Controladores.GestorMenu;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import static java.awt.event.KeyEvent.VK_C;
import static java.awt.event.KeyEvent.VK_N;
import static java.awt.event.KeyEvent.VK_R;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Javier_p
 */
public class VistaTablero extends JFrame {

    private FondoPanel fondo;
    public ControladorTablero con;
    private MenuItem op1, op2, op3, op4;
    private Menu menu;
    private MenuBar menuBar;
    private MenuShortcut m1, m2, m3, m4;
    private GestorMenu gMenu;
    private ImageIcon tablon, luz;
    private ImageIcon IMAGEN_HUEVO_PASCUA = new ImageIcon("src/images/ocaPascua.png");
    private ImageIcon IMAGEN_TABLERO = new ImageIcon("src/images/tablero.png");
    private ImageIcon ANIMACION_TIRADA_DADOS = new ImageIcon("src/images/tirada_dados.gif");
    private ImageIcon ANIMACION_GANADOR = new ImageIcon("src/images/ganador.png");
    private JLabel tablero, tirada, marcador, marcadortexto, j1, j2, animacionGanador, nombreGanador, turnoJugador, efectoCasilla, tiradaDado, huevoPascua;
    public Dado boton;
    public Cronometro cronometro;

    int[] coordenadasX = {150, 270, 355, 425, 495, 565, 645, 720, 790, 870, 930, 980, 1000, 1030, 1030, 1010, 1000, 950, 880, 790, 710, 640, 570, 500, 430,
        360, 290, 220, 160, 120, 90, 80, 80, 90, 115, 170, 230, 300, 360, 430, 500, 570, 645, 725, 795, 855, 900, 920, 920, 900,
        850, 775, 680, 580, 500, 420, 350, 290, 210, 200, 200, 240, 300, 500};

    int[] coordenadasY = {760, 760, 760, 760, 760, 760, 760, 760, 760, 740, 680, 630, 570, 510, 430, 360, 290, 230, 180, 150, 150, 150, 150, 150, 150,
        150, 150, 160, 200, 250, 310, 380, 450, 520, 580, 620, 650, 660, 660, 660, 660, 660, 660, 660, 660, 600, 540, 480, 410, 350,
        290, 250, 250, 250, 250, 250, 250, 250, 320, 410, 480, 530, 550, 550};

    String mensajeInicial = "¡Bienvenidos al juego de la OCA de Javier y Omar! \n"
            + "Hemos pasado mucho tiempo desarrollandolo, en la asignatura de Desarrollo de Interfaces\n"
            + "Esperamos que os guste, disfrutad jugando.\n\n"
            + "                                                           ¡¡BUENA SUERTE!!\n\n"
            + "Colegio Trinitarias de Salamanca";

    public VistaTablero(ControladorTablero controla) throws IOException {
        super("¡El juego de la OCA!");
        try {
            Image img = ImageIO.read(new File("src/images/oca70.png"));
            this.setIconImage(img);
        } catch (Exception e) {

        }
        this.con = controla;
        j1 = controla.jugador1;
        j2 = controla.jugador2;
        propiedadesVentana();
        iniciarElementos();
        anadirFondo();
        anadirElementos();
        posicionesElementos();
        anadirEscuchadores();
        JOptionPane.showMessageDialog(this, mensajeInicial);
    }

    /**
     * Metodo que edita las propiedades de la ventana
     */
    private void propiedadesVentana() {
        this.setLayout(null);
        this.setBounds(370, 10, 1200, 1000);
        this.setVisible(true);
        this.setResizable(false);
    }

    /**
     * Metodo que inicializa los elementos de la ventana
     */
    private void iniciarElementos() throws IOException {
        fondo = new FondoPanel();
        boton = new Dado(con);
        cronometro = new Cronometro(185, this);
        cronometro.setFont(new Font(Font.DIALOG, Font.BOLD, 40));
        cronometro.setVisible(false);
        turnoJugador = new JLabel();
        turnoJugador.setVisible(false);

        op1 = new MenuItem("Nueva Partida");
        op2 = new MenuItem("Reglas");
        op3 = new MenuItem("Guardar Partida");
        op4 = new MenuItem("Carga Partida");
        menu = new Menu("Opciones");
        menuBar = new MenuBar();
        m1 = new MenuShortcut(VK_N);
        m2 = new MenuShortcut(VK_R);
        m3 = new MenuShortcut(VK_C);
        gMenu = new GestorMenu(con, this);

        tirada = new JLabel();
        tirada.setIcon(ANIMACION_TIRADA_DADOS);
        tirada.setVisible(false);

        nombreGanador = new JLabel("GANADOR");
        nombreGanador.setFont(new Font(Font.DIALOG, Font.BOLD, 60));
        nombreGanador.setForeground(Color.GREEN);
        nombreGanador.setVisible(false);

        animacionGanador = new JLabel();
        animacionGanador.setIcon(ANIMACION_GANADOR);
        animacionGanador.setVisible(false);

        tablero = new JLabel();
        tablero.setIcon(IMAGEN_TABLERO);

        efectoCasilla = new JLabel();
        luz = new ImageIcon("src/images/luz2.png");
        efectoCasilla.setIcon(luz);
        efectoCasilla.setVisible(false);

        tablon = new ImageIcon("src/images/tabla.png");
        marcador = new JLabel();
        marcador.setIcon(tablon);

        marcadortexto = new JLabel();
        marcadortexto.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
        marcadortexto.setForeground(Color.RED);

        huevoPascua = new JLabel();
        huevoPascua.setIcon(IMAGEN_HUEVO_PASCUA);

        tiradaDado = new JLabel();
    }

    public void mostrarTiradaDado(ImageIcon tirada) {
        tiradaDado.setVisible(true);
        tiradaDado.setIcon(tirada);
    }

    /**
     * Metodo que hactiva en la vista una serie de animaciones y detiene otras
     * cuando se encuentra un ganador
     *
     * @param nombre
     */
    public void ganadorPartida(String nombre) {
        nombreGanador.setText(nombre);
        animacionGanador.setVisible(true);
        nombreGanador.setVisible(true);
        boton.setEnabled(false);
        cronometro.detenerCronometro();
    }

    /**
     * Metodo que hacae visible en caso de ser necesario(Modo 1 Jugador) el
     * cronometro
     */
    public void mostrarCronometro() {
        cronometro.setVisible(true);
    }

    /**
     * Metodo que modifica el marcador
     *
     * @param marcador
     */
    public void setMarcador(String marcador) {
        this.marcadortexto.setText(marcador);
    }

    /**
     * Metodo que pone visible un .gif que simula una tirada de dados
     *
     * @param casilla
     */
    public void manoVisible() {
        tirada.setVisible(true);
    }

    /**
     * Metodo que pone visible el marco que indica a que casilla se va a
     * desplazar el jugador
     *
     * @param casilla
     */
    public void marcoVisible(int casilla) {
        efectoCasilla.setBounds(coordenadasX[casilla], coordenadasY[casilla], 100, 100);
        efectoCasilla.setVisible(true);
    }

    /**
     * Método que administra los diferentes elementos cuando una tirada ha
     * acabado por completo
     */
    public void finalTirada() {
        tirada.setVisible(false);
        efectoCasilla.setVisible(false);
        boton.setEnabled(true);
        tiradaDado.setVisible(false);
    }

    /**
     * Metodo que añade los elementos a la ventana
     */
    private void anadirElementos() {
        menu.add(op1);
        menu.add(op2);
        menu.add(op3);
        menu.add(op4);
        op1.setShortcut(m1);
        op2.setShortcut(m2);
        op3.setShortcut(m3);
        op4.setShortcut(m4);
        menuBar.add(menu);
        this.setMenuBar(menuBar);

        fondo.add(j1);
        if (j2 != null) {
            fondo.add(j2);
        }

        fondo.add(cronometro);
        fondo.add(turnoJugador);
        fondo.add(nombreGanador);
        fondo.add(animacionGanador);
        fondo.add(boton);
        fondo.add(tiradaDado);
        fondo.add(efectoCasilla);
        fondo.add(tablero);
        fondo.add(tirada);
        fondo.add(marcadortexto);
        fondo.add(marcador);
        fondo.add(huevoPascua);
    }

    /**
     * Metodo que edita las posiciones de los elementos en la ventana
     */
    private void posicionesElementos() {
        boton.setBounds(1050, 770, 130, 130);
        marcadortexto.setBounds(500, 50, 700, 50);
        marcador.setBounds(20, -33, 300, 200);
        tablero.setBounds(50, 100, 1150, 800);
        tirada.setBounds(600, 250, 500, 500);
        animacionGanador.setBounds(300, 100, 600, 600);
        nombreGanador.setBounds(500, 500, 500, 100);
        cronometro.setBounds(105, 30, 200, 100);
        turnoJugador.setBounds(110, 30, 200, 100);
        j1.setBounds(150, 760, 100, 100);
        tiradaDado.setBounds(400, 420, 200, 200);
        huevoPascua.setBounds(950, 120, 40, 40);

        if (j2 != null) {
            j2.setBounds(150, 800, 100, 100);
        }
    }

    /**
     * Método gracias al cual podemos cambiar la imagen que indica al usuario
     * visualmente quien tiene el turno
     *
     * @param jugadorTurno
     */
    public void cambiarImagenTurno(ImageIcon jugadorTurno) {
        turnoJugador.setVisible(true);
        turnoJugador.setIcon(jugadorTurno);
    }

    /**
     * Metodo que añade los escucahdores a los elementos que lo requieran en la
     * ventana
     */
    private void anadirEscuchadores() {
        this.addWindowListener(con);
        op1.addActionListener(gMenu);
        op2.addActionListener(gMenu);
        op3.addActionListener(gMenu);
        op4.addActionListener(gMenu);
        huevoPascua.addMouseListener(con);
    }

    /**
     * Método que establece el panel "fondo" con su imagen preestablecida como
     * fondo de la ventana
     */
    private void anadirFondo() {
        this.setContentPane(fondo);
        fondo.setLayout(null);
    }

    public void cronometroFin() {
        if (cronometro.isVisible()) {
            con.finJuego();
        }
    }

}

/**
 * Creamos esta clase para poder establecer una imágen de fondo de pantalla de
 * la ventana Una de las cualidades de hacerlo de esta manera es que el tamaño
 * se ajusta a la dimensión de la ventana, aunque esta varie
 *
 * @author 34633
 */
class FondoPanel extends JPanel {

    private ImageIcon fondo;

    @Override
    public void paint(Graphics g) {
        fondo = new ImageIcon(getClass().getResource("/images/fondo.jpg"));
        g.drawImage(fondo.getImage(), 0, 0, getWidth(), getHeight(), this);

        this.setOpaque(false);
        super.paint(g);
    }
}
