package com.pasofe.deadmanwarning.logic;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.widget.Toast;

import com.pasofe.deadmanwarning.MainActivity;
import com.pasofe.deadmanwarning.WarningActivity;

import java.text.DecimalFormat;
import java.text.Normalizer;
import java.util.stream.DoubleStream;


public class CheckState extends AsyncTask<Boolean, Void, Void> {
    private boolean checked;
    private SensorManager sensor;
    private Context mContext;
    private Sensor gyroSensor;
    private GyroscopeListener giroscopio;
    private int contadorWhile;
    private int contadorVeces;


    private Float AverageInX = 0f, AverageInY = 0f,AverageInZ = 0f;
    private Float NewAverageInX = 0f, NewAverageInY = 0f, NewAverageInZ = 0f;

    private DecimalFormat df = new DecimalFormat("#.####");
    private float epsilon = 0.0001f;

    public CheckState(Context context){
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(Boolean... arrayState) {
        checked = arrayState[0];
        sensor = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);
        gyroSensor = sensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        giroscopio = new GyroscopeListener();
        contadorWhile = 0;
        contadorVeces = 0;
        try {
            sensor.registerListener(giroscopio, gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);
            do {
                if (gyroSensor == null) {
                    throw new Exception("El sensor no esta en este dispostivo");
                }

                Thread.sleep(800); // espera por 0,8 segundos

                if(contadorWhile % 10 == 0){
                    AverageInX = giroscopio.userStateInX();
                    AverageInY = giroscopio.userStateInY();
                    AverageInZ = giroscopio.userStateInZ();


                    System.out.println(AverageInX + " | " +NewAverageInX);
                    System.out.println(AverageInY + " | " + NewAverageInY);
                    System.out.println(AverageInZ + " | " + NewAverageInZ);


                    if( ( Math.abs(AverageInX - NewAverageInX) < epsilon ) && ( Math.abs(AverageInY - NewAverageInY) < epsilon )){
                        contadorVeces++;
                    } else if( ( Math.abs(AverageInY - NewAverageInY) < epsilon) && ( Math.abs(AverageInZ - NewAverageInZ) < epsilon)){
                        contadorVeces++;
                    } else if(  ( Math.abs(AverageInX - NewAverageInX) < epsilon )  && ( Math.abs(AverageInZ - NewAverageInZ) < epsilon)){
                        contadorVeces++;
                    }

                    NewAverageInX = AverageInX;
                    NewAverageInY = AverageInY;
                    NewAverageInZ = AverageInZ;
                }

                System.out.println(contadorWhile + " " + contadorVeces);
                contadorWhile++;
                if(contadorVeces == 12)
                    break;
            }while (checked);


        }catch (Exception E){
            System.out.println("Error en doInBackground");
            E.printStackTrace();
        }finally {
            return null;
        }

    }

    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        if(contadorVeces == 12){
            Toast.makeText(mContext.getApplicationContext(), "Se ha envido un aviso",Toast.LENGTH_LONG).show();
            Intent intento = new Intent(mContext.getApplicationContext(), WarningActivity.class);
            mContext.startActivity(intento);
        }

    }
}


