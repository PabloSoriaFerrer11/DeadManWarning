package com.pasofe.deadmanwarning.logic;

import android.os.AsyncTask;

import com.pasofe.deadmanwarning.interfaces.GetHomeState;
import com.pasofe.deadmanwarning.ui.home.HomeFragment;

public class CheckState extends AsyncTask<Void, Void, String> {
    private boolean checked;
    private GetHomeState HomeInterface;
    private HomeFragment FragmentHome;

    @Override
    protected String doInBackground(Void... voids) {
        FragmentHome  = new HomeFragment();

        try {
            do {
                checked = FragmentHome.isCheckBoxChecked();

                System.out.println("\n Esto es una prueba" + checked);

            }while (checked);

        }catch (Exception E){
            E.printStackTrace();
            System.err.println("\n EY");
        }finally {
            return null;
        }

    }
}


