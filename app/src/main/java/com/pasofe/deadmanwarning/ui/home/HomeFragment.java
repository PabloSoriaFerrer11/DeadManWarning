package com.pasofe.deadmanwarning.ui.home;


import android.os.Bundle;
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

public class HomeFragment extends Fragment implements GetHomeState {

    private FragmentHomeBinding binding;
    private Switch mSwitch;
    private CheckState classCheckState;

    private View rootView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        rootView = binding.getRoot();

        mSwitch = binding.CheckState;

        binding.CheckState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSwitch = binding.getRoot().findViewById(R.id.CheckState);
                isCheckBoxChecked();
            }
        });

        classCheckState = new CheckState();
        classCheckState.execute();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if(mSwitch.isChecked())
            mSwitch.setChecked(false);

        /*binding = null;
        rootView = null;*/
    }

    @Override
    public boolean isCheckBoxChecked() {

        mSwitch = rootView.findViewById(R.id.CheckState);

        if (mSwitch != null && mSwitch.isChecked())
            return true;
        else
            return false;
    }
}