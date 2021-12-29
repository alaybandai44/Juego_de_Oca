/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vistas;
   import Vistas.VistaTablero;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;
/**
 *
 * @author Javier_p
 */
public class Cronometro extends JLabel{
   private int contador;
    private int minutos, segundos;
    private Timer timer;
    private String cronometro;
    private VistaTablero vis;
    
    public Cronometro(int segundos, VistaTablero vis){
        super();
        this.contador = segundos;
        this.setForeground(Color.GREEN);
        this.empiezaCuentaAtras();
        this.vis = vis;
    }
    
    /**
     * Método que actualiza los minutos y segundos que quedan de partida
     */
    public void calcularCronometro(){
        minutos = contador/60;
        segundos = contador%60;
        
        //Cambio de color del Label dependiendo de el tiempo que quede para que la partida acabe
        if(contador > 60){
            this.setForeground(Color.GREEN); 
        }    
        if(contador < 60){
            this.setForeground(Color.ORANGE);
        }    
        if(contador <= 20){
            this.setForeground(Color.RED);
        }
        
        //Calibramos graficamente como queremos que aparezcan esos minutos-segundos, para que sea más amigable para el usuario
        if(segundos<10){
            cronometro = ""+0 + minutos+":0"+segundos;
        }else{
            cronometro = ""+0 + minutos+":"+segundos;
        }
        
        this.setText(cronometro);
    }
    
    /**
     * Metodo por el cual podemos interrumpir el timer del cronómetro y detenerlo
     */
    public void detenerCronometro(){
        if(timer != null){
            timer.stop();
        }
    }
    
    /**
     * Método que aplica al cronómetro de la partida la penalización que haya obtenido el jugador
     * @param turnosPenalizado 
     */
    public void penalizacion(int turnosPenalizado){
        int penalizacion = turnosPenalizado *10;
        this.contador -= penalizacion;
        calcularCronometro();
    }
    
    /**
     * Método que inicia la cuenta atras
     */
    public void empiezaCuentaAtras(){
        
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                calcularCronometro();
                
                if(contador == 0){
                    timer.stop();
                    vis.cronometroFin();
                }
                
                contador--;
            }
        });

        timer.start();
    }
}
