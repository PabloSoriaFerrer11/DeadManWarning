package com.pasofe.deadmanwarning.logic;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.AsyncTask;


public class CheckState extends AsyncTask<Boolean, Void, Void> {
    private boolean checked;
    private SensorManager sensor;
    private Context mContext;
    private Sensor gyroSensor;
    private GyroscopeListener giroscopio;

    public CheckState(Context context){
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(Boolean... arrayState) {
        checked = arrayState[0];
        sensor = (SensorManager) mContext.getSystemService(mContext.SENSOR_SERVICE);
        gyroSensor = sensor.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        giroscopio = new GyroscopeListener();

        try {
            sensor.registerListener(giroscopio, gyroSensor,SensorManager.SENSOR_DELAY_NORMAL);

            do {
                if (gyroSensor == null) {
                    throw new Exception("El sensor no esta en este dispostivo");
                }

                Thread.sleep(2000); // espera por dos segundos

                giroscopio.debugArrayData();

            }while (checked);

        }catch (Exception E){
            System.out.println("Error en doInBackground");
            E.printStackTrace();
        }finally {
            return null;
        }

    }
}


