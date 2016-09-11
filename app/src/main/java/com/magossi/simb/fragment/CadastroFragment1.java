package com.magossi.simb.fragment;


import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magossi.simb.R;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment1 extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_1, container, false);
        setRetainInstance(true);

        Log.i("fragments", "-> onCreateFragment-1: "+getActivity().getClass().getName() );


        return view;


    }



    //chamada webservice

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void alteraTextView(String texto){

        TextView tv = (TextView) getView().findViewById(R.id.textview_frag_cadastro_1);
        tv.setText(texto);


    }
}
