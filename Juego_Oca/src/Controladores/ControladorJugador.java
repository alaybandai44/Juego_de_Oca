
package Controladores;

import Vistas.Jugador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.Timer;

public class ControladorJugador implements Serializable {
    
    public  Jugador jug;
    private transient Timer timer;
    private int posicion;
     private int penalizacion;
    private transient ControladorTablero conTablero;
    
    private int[] coordenadasX ={150, 270, 355, 425, 495, 565, 645, 720, 790, 870, 930, 980, 1000, 1030, 1030, 1010, 1000, 950, 
        880, 790, 710, 640, 570, 500, 430, 360, 290, 220, 160, 120, 90, 80, 80, 90, 115, 170, 230, 300, 360, 430, 500, 570, 645, 
        725, 795, 855, 900, 920, 920, 900, 850, 775, 680, 580, 500, 420, 350, 290, 210, 200, 200, 240, 300, 500};

    private int[] coordenadasY ={760, 760, 760, 760, 760, 760, 760, 760, 760, 740, 680, 630, 570, 510, 430, 360, 290, 230, 180, 150, 
        150, 150, 150, 150, 150, 150, 150, 160, 200, 250, 310, 380, 450, 520, 580, 620, 650, 660, 660, 660, 660, 660, 660, 660, 660, 
        600, 540, 480, 410, 350, 290, 250, 250, 250, 250, 250, 250, 250, 320, 410, 480, 530, 550, 550};
    
    
    public ControladorJugador(Jugador j1){
        this.jug = j1;
        this.posicion = 0;
        this.penalizacion = 0;
    }
    
    /**
     * Metodo gracias al cual la clase ControladorJugador recibe una variable para su atributo conTablero
     * @param con 
     */
    public void anadirControlador(ControladorTablero con){
        this.conTablero = con;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
    }

    public void setPenalizacion(int penalizacion) {
        this.penalizacion = penalizacion;
    }
    
    /**
     * Metodo gracias al cual el jugador se mueve de casilla en casilla con una transicion visual
     * @param casilla
     * @param volverInicio
     * @param retroceder 
     */
    public void moverCasilla(int casilla, boolean volverInicio, boolean retroceder) {

        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(posicion < 63){
                    if (jug.getX() < coordenadasX[posicion + 1]) {
                        jug.setBounds((jug.getX() + 1), jug.getY(), 100, 100);
                    }

                    if (jug.getX() > coordenadasX[posicion + 1]) {
                        jug.setBounds((jug.getX() - 1), jug.getY(), 100, 100);
                    }

                    if (jug.getY() < coordenadasY[posicion + 1]) {
                        jug.setBounds(jug.getX(), (jug.getY() + 1), 100, 100);
                    }

                    if (jug.getY() > coordenadasY[posicion + 1]) {
                        jug.setBounds(jug.getX(), (jug.getY() - 1), 100, 100);
                    }

                    if ((coordenadasX[posicion + 1] == jug.getX()) && (coordenadasY[posicion + 1] == jug.getY())){
                        posicion++;
                    }
                    
                    if(posicion > 61 && retroceder){  //usaremos este algoritmo en las ultimas tiradas cuando el jugador esté en las casillas finales
                        posicion = casilla;
                        jug.setBounds(coordenadasX[casilla], coordenadasY[casilla], 100, 100);
                    }
                    
                    if(posicion == 62 && volverInicio){  //usaremos este algoritmo en las ultimas tiradas cuando el jugador haya caido en la muerte o en el puente de la casilla 42, para que de la vuelva a la casilla 0 
                        jug.setBounds(coordenadasX[0], coordenadasY[0], 100, 100);
                        posicion = 0;
                    }
                }

                if (posicion == casilla) {
                    conTablero.vis.finalTirada();
                    timer.stop();
                }
                
            }
        });

        timer.start();
    }

    /**
     * Diferentes geters de algunos de los atributos del objeto
     * @return 
     */
    public int getPosicion() {
        return posicion;
    }

    public int getPenalizacion() {
        return penalizacion;
    }
    
    /**
     * Método que modifica la variable penalización, sumandole a la del objeto los turnos de penalización que se mande por parámetros
     * @param turnosPenalizacion 
     */
    public void nuevaPenalizacion(int turnosPenalizacion) {
        this.penalizacion += turnosPenalizacion;
    }
    
    /**
     * Metodo que se llama cuando el jugador tiene una penalización vigente, de tal manera que cada turno transcurrido va restando
     * una unidad a la penalización del jugador
     */
    public void turnoCastigado() {
        this.penalizacion--;
    }
    
}
