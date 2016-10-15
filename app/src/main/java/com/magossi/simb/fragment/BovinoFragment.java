package com.magossi.simb.fragment;

/**
 * Created by RafaelMq on 13/10/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import com.magossi.simb.R;
import com.magossi.simb.domain.Bovino;



public class BovinoFragment extends Fragment{

        private Bovino bovino;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_layout_busca_bovino, container, false);
            return view;
        }

        // Método público chamado pela activity para atualizar os dados do carro
        public void setBovino(Bovino bovino) {
            this.bovino = bovino;

            //setTextString(R.id.tNome,carro.nome);
            TextView textDesc = (TextView) getView().findViewById(R.id.tDesc);
            textDesc.setText(bovino.desc);

            final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
            Picasso.with(getContext()).load(bovino.urlFoto).fit().into(imgView);


        }


    }

