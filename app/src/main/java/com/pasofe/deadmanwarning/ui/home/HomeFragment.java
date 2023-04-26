package com.pasofe.deadmanwarning.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.pasofe.deadmanwarning.databinding.FragmentHomeBinding;
import com.pasofe.deadmanwarning.interfaces.GetHomeState;
import com.pasofe.deadmanwarning.logic.CheckState;

public class HomeFragment extends Fragment implements GetHomeState {

    private FragmentHomeBinding binding;
    private Switch CheckStateSwitch;
    private CheckState classCheckState;

    private View rootView;

    private LayoutInflater Inflador;
    private ViewGroup Contenedor;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        Inflador = inflater;
        Contenedor = container;

        binding = FragmentHomeBinding.inflate(Inflador, Contenedor, false);
        rootView = binding.getRoot();


        CheckStateSwitch = binding.CheckState;

        binding.CheckState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isCheckBoxChecked();
            }
        });

        classCheckState = new CheckState();
        classCheckState.execute();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(CheckStateSwitch.isChecked())
            CheckStateSwitch.setChecked(false);

        binding = null;
        rootView = null;
    }

    @Override
    public boolean isCheckBoxChecked() {
        System.out.println("Prueba psoria");
       if (rootView != null) {
           //CheckStateSwitch = rootView.findViewById(R.id.CheckState); // reasignar CheckStateSwitch*/
           if (CheckStateSwitch != null && CheckStateSwitch.isChecked())
               return true;
       }
        return false;
    }
}