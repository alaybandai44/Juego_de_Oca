/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Logica;

import Vistas.Jugador;
import Vistas.VistaTablero;
import javax.swing.ImageIcon;

public class LogicaJuego {
    private Jugador[] jugadores;
    private int[] OCAS = {5, 9, 14, 18, 23, 27, 32, 36, 41, 45, 50, 54, 59, 63};
    private int[] PUENTES = {6, 42};
    private int[] PARKING = {19, 31};
    private int CARCEL = 52;
    private int CASILLA_FIN = 63;
    private int MUERTE = 58;
    private int TURNOS_PENALIZACION_CARCEL = 5;
    private int TURNOS_PENALIZACION_PARKING = 1;
    private int turno;
    
    private VistaTablero vis;
    private boolean repiteTurno, finJuego, volverInicio, retroceder;
    
    
    public LogicaJuego(Jugador j1, VistaTablero vis){
        turno = 0;
        jugadores = new Jugador[1];
        jugadores[0] = j1;
        this.vis = vis;
        
        this.repiteTurno = false;
        this.finJuego = false;
        this.volverInicio = false;
        this.retroceder = false;
    }
    
    public LogicaJuego(Jugador j1, Jugador j2, VistaTablero vis){
        turno = 0;
        jugadores = new Jugador[2];
        jugadores[0] = j1;
        jugadores[1] = j2;
        this.vis = vis;
        vis.cambiarImagenTurno((ImageIcon) jugadores[turno].getIcon());
    }
    
    /**
     * Algoritmo de juego que se recorre cada vez que se realiza una tirada
     * @param tirada 
     */
    public void nuevaTirada(int tirada){
        
        int posicion;
        this.repiteTurno = false;
        this.volverInicio = false;
        this.retroceder = false;
        
        //SI HAY MAS DE 1 JUGADOR EN LA PARTIDA MOSTRAMOS EN LA PARTE SUPERIOR IZQUIERDA QUE JUGADOR TIENE EL TURNO
        if(jugadores.length > 1){
            vis.cambiarImagenTurno((ImageIcon) jugadores[turno].getIcon());
        }
        
        //SI EL JUGADOR NO ESTA PENALIZADO
        if(jugadores[turno].con.getPenalizacion() == 0  && !finJuego){ 
            
            posicion = jugadores[turno].con.getPosicion() + tirada;
            
            //EN EL CASO DE QUE LA POSICION OBTENIDA SEA MAYOR A LA POSIBLE (ULTIMAS JUGADAS)(EL JUGADOR REBOTA HASTA QUE CLAVE LA CASILLA FINAL)
            if(posicion > CASILLA_FIN){ 
                int diferencia;
                diferencia = posicion-CASILLA_FIN;
                posicion = CASILLA_FIN-diferencia;
                retroceder = true;
            }

            //SI LA POSICION OBTENIDA ES IGUAL A LA CASILLA FINAL
            if(posicion == CASILLA_FIN){  
                jugadores[turno].con.moverCasilla(posicion, volverInicio, retroceder);
                vis.ganadorPartida(jugadores[turno].getNombre());
                finJuego = true;
            }else{
                //SI LA NUEVA POSICION ES UNA OCA, AVANZAREMOS HASTA LA SIGUIENTE OCA
                for (int i = 0; i < OCAS.length; i++) {
                    if(posicion == OCAS[i]){
                        repiteTurno = true;

                        if(OCAS[i] != OCAS[OCAS.length-1]){
                            posicion = OCAS[i+1];
                        }
                        break;
                    }
                }

                //SI LA NUEVA POSICION ES UN PUENTE, AVANZAREMOS HASTA EL SIGUIENTE PUENTE
                for (int i = 0; i < PUENTES.length; i++) {
                    if(posicion == PUENTES[i]){
                        repiteTurno = true;

                        if(PUENTES[i] == PUENTES[0]){
                            posicion = PUENTES[i+1];
                        }else{
                            posicion = PUENTES[0];
                            volverInicio = true;
                        }
                        break;
                    }
                }
                
                //SI LA NUEVA POSICION ES UN PUENTE, AVANZAREMOS HASTA EL SIGUIENTE PUENTE
                for (int i = 0; i < PARKING.length; i++) {
                    if(posicion == PARKING[i]  &&  (jugadores.length > 1)){
                        jugadores[turno].con.nuevaPenalizacion(TURNOS_PENALIZACION_PARKING);

                    }else if(posicion == PARKING[i]  &&  (jugadores.length < 1)){
                        vis.cronometro.penalizacion(TURNOS_PENALIZACION_PARKING);
                    }
                }

                //SI LA POSICION A LA QUE HEMOS AVANZADO ES LA CARCEL, APLICAREMOS LA CORRESPONDIENTE PENALIZACION AL JUGADOR
                if(posicion == CARCEL){
                    if(jugadores.length > 1){
                        jugadores[turno].con.nuevaPenalizacion(TURNOS_PENALIZACION_CARCEL);
                    }else{
                        vis.cronometro.penalizacion(TURNOS_PENALIZACION_CARCEL);
                    }
                }

                 //SI LA POSICION A LA QUE HEMOS AVANZADO ES LA MUERTE, EL JUGADOR VOLVERA A EMPEZAR DESDE LA CASILLA 0
                if(posicion == MUERTE){
                    posicion = 0;
                    volverInicio = true;
                }
            }
            
            //MOVEMOS AL JUGADOR A LA NUEVA CASILLA
            jugadores[turno].con.moverCasilla(posicion, volverInicio, retroceder);
            
            
            vis.marcoVisible(posicion);
            
            if(posicion == CASILLA_FIN){ //SI POR CASUALIDAD HEMOS CAIDO EN UNA OCA, VOLVEMOS A ANALIZAR SI LA POSICION FINAL ES LA FINAL
                vis.ganadorPartida(jugadores[turno].getNombre());
                finJuego = true;
            }
            
        //SI EL JUGADOR ESTA PENALIZADO, RESTAMOS EL CORRESPONDIENTE TURNO A LA PENALIZACION QUE TIENE
        }else{  
            jugadores[turno].con.turnoCastigado();
            vis.finalTirada();
        }
        
        //CAMBIO DE TURNO PARA LA SIGUIENTE RONDA (desarrollado para futuras implementaciones de más de 2 jugadores)
        if(jugadores.length > 1 && !repiteTurno){
            if(turno == (int)jugadores.length - 1){
                turno = 0;
            }else {
                turno++;
            }
        }
        
    }
    
    /**
     * Retorna el numero de jugadores de la partida logica
     * @return 
     */
    public int numeroJugadoresPartida(){
        return jugadores.length;
    }
    
    /**
     * Metodo para terminar el juego logicamente
     */
    public void finJuego(){
        this.finJuego = true;
    }
}