package com.magossi.simb.fragment;

/**
 * Created by RafaelMq on 13/10/2016.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.magossi.simb.activity.BovinoActivity;
import com.magossi.simb.activity.MatrizActivity;
import com.squareup.picasso.Picasso;

import com.magossi.simb.R;
import com.magossi.simb.domain.Bovino;



public class BovinoFragment extends Fragment{

        private Bovino bovino;
        private TextView textview_nome;
        private TextView textview_pai;
        private TextView textview_mae;
        private TextView textview_datanasc;
        private TextView textview_proprietario;
        private TextView textview_fazenda;
        private TextView textview_genero;
        private TextView textview_raca;
        private TextView textview_pelagem;
        private Button button_fichamatriz;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fragment_layout_busca_bovino, container, false);
            Log.i("FRAGMENT", "onCreateView - fragment_layout_busca_bovino - " +getActivity().getClass().getName() );
            return view;
        }

        // Método público chamado pela activity para atualizar os dados do bovino
        public void setBovino(final Bovino bovino) {
            this.bovino = bovino;

            //setTextString(R.id.tNome,carro.nome);
            textview_nome = (TextView) getView().findViewById(R.id.textview_busca_nome_bovino);
            textview_pai = (TextView) getView().findViewById(R.id.textview_busca_pai_bovino);
            textview_mae = (TextView) getView().findViewById(R.id.textview_busca_mae_bovino);
            textview_datanasc = (TextView) getView().findViewById(R.id.textview_busca_datanasc_bovino);
            textview_proprietario = (TextView) getView().findViewById(R.id.textview_busca_proprietario_bovino);
            textview_fazenda = (TextView) getView().findViewById(R.id.textview_busca_fazenda_bovino);
            textview_genero = (TextView) getView().findViewById(R.id.textview_busca_genero_bovino);
            textview_raca = (TextView) getView().findViewById(R.id.textview_busca_raca_bovino);
            textview_pelagem = (TextView) getView().findViewById(R.id.textview_busca_pelagem_bovino);


            textview_nome.setText(bovino.getNomeBovino());
            textview_pai.setText(bovino.getPai());
            textview_mae.setText(bovino.getMae());
            textview_datanasc.setText(bovino.getDataNascimento().toString());
            textview_proprietario.setText(bovino.getProprietario().getNomeProprietario());
            textview_fazenda.setText(bovino.getFazenda().getNomeFazenda());
            if(bovino.getGenero()) {
                textview_genero.setText("Macro");
            }else{
                textview_genero.setText("Fêmea");
            }
            textview_raca.setText(bovino.getRaca().getNomeRaca());
            textview_pelagem.setText(bovino.getPelagem().getNomePelagem());


            if(bovino.getGenero()) {
                button_fichamatriz = (Button) getView().findViewById(R.id.button_buscar_fichamatriz);
                button_fichamatriz.setVisibility(getView().GONE);
            }else{

                button_fichamatriz = (Button) getView().findViewById(R.id.button_buscar_fichamatriz);
                button_fichamatriz.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getContext(), MatrizActivity.class);
                        intent.putExtra("bovino", bovino);
                        startActivity(intent);

                        //Toast.makeText(getContext(),"teste" + getActivity(), Toast.LENGTH_SHORT).show();

//                        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                        ft.replace(R.id.fragment_buscar_bovino, new MatrizFragment());
//                        ft.commit();



//                        MatrizFragment mf = (MatrizFragment) getActivity()
//                                .getSupportFragmentManager()
//                                .findFragmentById(R.id.fragment_buscar_bovino);



                        //Bovino b = (Bovino) getIntent().getSerializableExtra("bovino");
                        //bf.setBovino(b);

                    }
                });
            }



            final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
            Picasso.with(getContext()).load(bovino.getUrlFoto()).fit().into(imgView);


        }



    @Override
    public void onStart() {
        super.onStart();

        Log.i("FRAGMENT", "onStart - fragment_layout_busca_bovino - " +getActivity().getClass().getName() );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Log.i("FRAGMENT", "onDestroy() - fragment_layout_busca_bovino - " +getActivity().getClass().getName() );
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i("FRAGMENT", "onPause - fragment_layout_busca_bovino - " +getActivity().getClass().getName() );
    }

    @Override
    public void onResume() {
        super.onResume();

        Log.i("FRAGMENT", "onResume - fragment_layout_busca_bovino - " +getActivity().getClass().getName() );
    }
}




