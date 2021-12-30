/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guardado;

import Controladores.ControladorJugador;
import Vistas.Cronometro;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Javier_p
 */
public class JugadorPosicionPenalSave {
    
      private FileOutputStream fileOut;
    private ObjectOutputStream salida;
    private ControladorJugador jugador;
    private FileInputStream fileIn;
    private ObjectInputStream entrada;

    public JugadorPosicionPenalSave(ControladorJugador jugador) throws IOException {
        this.jugador = jugador;
        guardadoPartida();
    }

    public JugadorPosicionPenalSave() {

    }

    public void guardadoPartida() throws FileNotFoundException, IOException {
        //Creamos un flujo de salida al disco
        fileOut = new FileOutputStream("jugador.obj");
        //Vinculamos el flujo de salida de objetos con el fichero
        salida = new ObjectOutputStream(fileOut);
        //escribimos el objeto
        salida.writeObject(jugador);
        //cerramos el flujo
        salida.close();
    }

    public ControladorJugador cargaPartida() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Creamos un flujo de entrada desde el disco
        fileIn = new FileInputStream("jugador.obj");
        //Vinculamos la referencia al disco con nuestro flujo de entrada
        entrada = new ObjectInputStream(fileIn);
        //Cargamos el objeto y hacemos el casting del tipo que es
        ControladorJugador jugador = (ControladorJugador) entrada.readObject();
        entrada.close();
        return jugador;
    }
    
}
