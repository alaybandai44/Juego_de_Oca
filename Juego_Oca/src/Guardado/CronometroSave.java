package Guardado;

import Vistas.Cronometro;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Javier_p
 */
public class CronometroSave {

    private FileOutputStream fileOut;
    private ObjectOutputStream salida;
    private Cronometro cronometro;
    private FileInputStream fileIn;
    private ObjectInputStream entrada;

    public CronometroSave(Cronometro cronometro) throws IOException {
        this.cronometro = cronometro;
        guardadoPartida();
    }

    public CronometroSave() {

    }

    public void guardadoPartida() throws FileNotFoundException, IOException {
        //Creamos un flujo de salida al disco
        fileOut = new FileOutputStream("cronometro.obj");
        //Vinculamos el flujo de salida de objetos con el fichero
        salida = new ObjectOutputStream(fileOut);
        //escribimos el objeto
        salida.writeObject(cronometro);
        //cerramos el flujo
        salida.close();
    }

    public Cronometro cargaPartida() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Creamos un flujo de entrada desde el disco
        fileIn = new FileInputStream("cronometro.obj");
        //Vinculamos la referencia al disco con nuestro flujo de entrada
        entrada = new ObjectInputStream(fileIn);
        //Cargamos el objeto y hacemos el casting del tipo que es
        Cronometro cronometro = (Cronometro) entrada.readObject();
        entrada.close();
        return cronometro;
    }
}
