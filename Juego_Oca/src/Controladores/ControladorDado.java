
package Controladores;

import Vistas.Dado;
import Logica.LogicaJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class ControladorDado implements ActionListener{
    private Dado dado;
    private Random generarTirada;
    private int numeroTirada;
    private LogicaJuego logica;
    
    public ControladorDado(Dado d){
        this.dado = d;
        this.generarTirada= new Random();
    }
    
    /**
     * Metodo que se instancia cuando el usuario realiza una nueva tirada
     */
    public void tirada(){
        numeroTirada = generarTirada.nextInt(6)+1;
        dado.nuevaTirada(numeroTirada);
        logica.nuevaTirada(numeroTirada);
    }

    /**
     * Metodo que se instanciará cuando el usuario pulse el boton dado
     * @param ae 
     */
    @Override
    public void actionPerformed(ActionEvent ae) {
        tirada();
    }
    
    /**
     * Método que se llama para guardar la variable del atributo logica del ControladorDado
     * @param log 
     */
    public void establecerLogica(LogicaJuego log){
        this.logica = log;
    }
}
