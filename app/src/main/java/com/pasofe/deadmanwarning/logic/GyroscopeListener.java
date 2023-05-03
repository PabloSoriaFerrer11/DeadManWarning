package com.pasofe.deadmanwarning.logic;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GyroscopeListener implements SensorEventListener {
    private List<Float> arrayListX = new ArrayList<Float>();
    private List<Float> arrayListY = new ArrayList<Float>();
    private List<Float> arrayListZ = new ArrayList<Float>();

    private DecimalFormat df = new DecimalFormat("#.##");
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        // Aquí es donde recibes las actualizaciones de datos del giroscopio
        // event.values[0] es el eje X, event.values[1] es el eje Y, event.values[2] es el eje Z
        try {
            if(arrayListX.size()>1){
                if(df.format(arrayListX.get(arrayListX.size()-1)) != df.format(sensorEvent.values[0])){
                    arrayListX.add(sensorEvent.values[0]);
                }
            }
            else{
                arrayListX.add(sensorEvent.values[0]);
            }

            if(arrayListX.size()>150)
                    arrayListX.remove(0);


            if(arrayListY.size()>1){
                if(df.format(arrayListY.get(arrayListY.size()-1)) != df.format(sensorEvent.values[1])){
                    arrayListY.add(sensorEvent.values[1]);
                }
            }
            else{
                arrayListY.add(sensorEvent.values[1]);
            }

            if(arrayListY.size()>150)
                arrayListY.remove(0);


            if(arrayListZ.size()>1){
                if(df.format(arrayListZ.get(arrayListZ.size()-1)) != df.format(sensorEvent.values[2])){
                    arrayListZ.add(sensorEvent.values[2]);
                }
            }
            else{
                arrayListZ.add(sensorEvent.values[2]);
            }

            if(arrayListZ.size()>150)
                arrayListZ.remove(0);

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
        System.out.println("Datos de eje X:");
        Float[] arrayX = arrayListX.toArray(new Float[arrayListX.size()]);
        System.out.println(Arrays.toString(arrayX));

        System.out.println("Datos de eje Y:");
        Float[] arrayY = arrayListY.toArray(new Float[arrayListY.size()]);
        System.out.println(Arrays.toString(arrayY));

        System.out.println("Datos de eje Z:");
        Float[] arrayZ = arrayListZ.toArray(new Float[arrayListZ.size()]);
        System.out.println(Arrays.toString(arrayZ));

        System.out.println("------------------------------------------------");
        System.out.println("\n");
    }

    public float userStateInX() {

        float AVGX = 0, AuxX = 0;
        for (int i = 0; i < arrayListX.size(); i++) {
            AuxX += arrayListX.get(i);
        }
        AVGX = (float) AuxX / arrayListX.size();
        return  AVGX;
    }

    public float userStateInY() {
        float AVGY = 0, AuxY=0;
        for (int i = 0;  i<arrayListY.size(); i++){
            AuxY += arrayListY.get(i);
        }
        AVGY = (float) AuxY / arrayListY.size();
        return  AVGY;
    }

    public float userStateInZ() {
        float AVGZ = 0, AuxZ=0;
        for (int i = 0;  i<arrayListZ.size(); i++){
            AuxZ += arrayListZ.get(i);
        }
        AVGZ = (float) AuxZ / arrayListZ.size();
        return  AVGZ;
    }





}
