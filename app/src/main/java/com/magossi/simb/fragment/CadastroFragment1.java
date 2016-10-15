package com.magossi.simb.fragment;


import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.magossi.simb.R;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment1 extends Fragment {



    private String[] proprietarios = { "Joao Antonio", "Marceno Limo", "Marcos da Silva"};

    private String[] fazendas = { "Santa Ana", "Santa Helena", "NSrª da Conceição", "NSrª do Amparo",
            "Queixo Duro" , "Paradeiro do Sul"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout_cadastro_1, container, false);
        setRetainInstance(true);
        Log.i("fragments", "-> onCreateFragment-1: "+getActivity().getClass().getName() );


        Spinner spinnerRaca = (Spinner) view.findViewById(R.id.spinner_proprietario);
        ArrayAdapter<String> adapterSpinnerRaca = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, proprietarios);
        adapterSpinnerRaca.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerRaca.setAdapter(adapterSpinnerRaca);

        Spinner spinnerPelagem = (Spinner) view.findViewById(R.id.spinner_fazenda);
        ArrayAdapter<String>  adapterSpinnerPelagem = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, fazendas);
        adapterSpinnerPelagem.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerPelagem.setAdapter(adapterSpinnerPelagem);

        return view;

    }


    //chamada webservice
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



}