package com.magossi.simb.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.magossi.simb.R;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment2 extends Fragment {

    private String[] racas = { "Raça 1", "Raça 1", "Raça 2", "Raça 3", "Raça 4",
            "Raça 5" , "Raça 6", "Raça 7", "Raça 8", "Raça 9", "Raça 10", "Raça 11"};

    private String[] pelagens = { "pelagen 1", "pelagen 2", "pelagen 3", "pelagen 4",
            "pelagen 5" , "pelagen 6", "pelagen 7", "pelagen 8", "pelagen 9", "pelagen 10", "pelagen 11"};

    private String[] eccs = { "1", "2", "3", "4", "5" , "6", "7", "8", "9", "10" };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_2, container, false);
        Log.i("fragments", "-> onCreateFragment-2: "+getActivity().getClass().getName() );


        Spinner spinnerRaca = (Spinner) view.findViewById(R.id.spinner_raca);
        ArrayAdapter<String> adapterSpinnerRaca = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, racas);
        adapterSpinnerRaca.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerRaca.setAdapter(adapterSpinnerRaca);

        Spinner spinnerPelagem = (Spinner) view.findViewById(R.id.spinner_pelagem);
        ArrayAdapter<String>  adapterSpinnerPelagem = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, pelagens);
        adapterSpinnerPelagem.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerPelagem.setAdapter(adapterSpinnerPelagem);

        Spinner spinnerEcc = (Spinner) view.findViewById(R.id.spinner_ecc);
        ArrayAdapter<String>  adapterSpinnerEcc = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, eccs);
        adapterSpinnerPelagem.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        spinnerEcc.setAdapter(adapterSpinnerEcc);

        return view;
    }
}
