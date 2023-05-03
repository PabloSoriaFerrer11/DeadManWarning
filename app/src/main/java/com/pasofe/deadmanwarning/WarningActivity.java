package com.pasofe.deadmanwarning;

import android.content.Context;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pasofe.deadmanwarning.databinding.ActivityWarningBinding;

public class WarningActivity extends AppCompatActivity {

    private ActivityWarningBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityWarningBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Vibrator vb = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationEffect vibrationEffect = VibrationEffect.createOneShot(10000, VibrationEffect.DEFAULT_AMPLITUDE);

        vb.vibrate(vibrationEffect);
    }
}