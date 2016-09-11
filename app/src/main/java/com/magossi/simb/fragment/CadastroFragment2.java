package com.magossi.simb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magossi.simb.R;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment2 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_2, container, false);

        Log.i("fragments", "-> onCreateFragment-2: "+getActivity().getClass().getName() );

        return view;
    }
}
