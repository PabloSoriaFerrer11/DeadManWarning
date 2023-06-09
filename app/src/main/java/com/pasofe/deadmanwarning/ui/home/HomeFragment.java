package com.pasofe.deadmanwarning.ui.home;


import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.ViewModelProvider;

import com.pasofe.deadmanwarning.R;
import com.pasofe.deadmanwarning.databinding.FragmentHomeBinding;
import com.pasofe.deadmanwarning.interfaces.GetHomeState;
import com.pasofe.deadmanwarning.logic.CheckState;
import com.pasofe.deadmanwarning.logic.DataManager;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private Switch mSwitch;
    private CheckState classCheckState;

    private DataManager dm;
    private SQLiteDatabase appdb;

    private View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        rootView = binding.getRoot();

        mSwitch = binding.CheckState;

        dm = new DataManager(rootView.getContext());



        binding.CheckState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mSwitch.isChecked()){
                    classCheckState = new CheckState(view.getContext());
                    classCheckState.execute(true);
                }else{
                    if(classCheckState != null)
                        classCheckState.cancel(true);
                }

            }
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(mSwitch.isChecked()){
            mSwitch.setChecked(false);
            classCheckState.cancel(true);
        }


        binding = null;
        rootView = null;
    }


}