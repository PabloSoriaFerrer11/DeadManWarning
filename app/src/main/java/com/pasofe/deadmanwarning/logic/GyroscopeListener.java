package com.pasofe.deadmanwarning.logic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class GyroscopeListener implements SensorEventListener {
    private List<Float> arrayX = new ArrayList<Float>();
    private List<Float> arrayY = new ArrayList<Float>();
    private List<Float> arrayZ = new ArrayList<Float>();


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Aquí es donde recibes las actualizaciones de datos del giroscopio
        // event.values[0] es el eje X, event.values[1] es el eje Y, event.values[2] es el eje Z
        try {
            arrayX.add(sensorEvent.values[0]);
            if(arrayX.size()>10)
                arrayX.remove(0);


            arrayY.add(sensorEvent.values[1]);
            if(arrayY.size()>10)
                arrayY.remove(0);


            arrayZ.add(sensorEvent.values[2]);
            if(arrayZ.size()>10)
                arrayZ.remove(0);

        }catch (Exception E){
            System.out.println("Error en onSensorChanged");
            E.printStackTrace();
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // Este método se llama cuando la precisión del sensor cambia.
        // Puedes ignorarlo por ahora si no te interesa.

    }

    public void debugArrayData(){
        System.out.println("Datos de X:");
        for(Float datoX : arrayX) {
            System.out.print(datoX);
            System.out.println("");
        }

        System.out.println("He llegado aqui");

        System.out.println("Datos de Y:");
        for(Float datoY : arrayY) {
            System.out.print(datoY);
            System.out.println("");
        }

        System.out.println("Datos de Z:");
        for(Float datoZ : arrayZ) {
            System.out.print(datoZ);
            System.out.println("");
        }
    }
}
