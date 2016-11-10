package com.magossi.simb.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.activity.CadastroActivity;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment3 extends Fragment {

    private CadastroActivity cadastroActivity;

    TextView textview_nome;
    TextView textview_pai;
    TextView textview_mae;
    TextView textview_datanasc;
    TextView textview_proprietario;
    TextView textview_fazenda;
    TextView textview_genero;
    TextView textview_raca;
    TextView textview_pelagem;
    TextView textview_peso;
    TextView textview_ecc;

    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.cadastroActivity= (CadastroActivity) myActivity;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_3, container, false);
        Log.i("fragments", "-> onCreateFragment-3: "+getActivity().getClass().getName() );



        textview_nome = (TextView) view.findViewById(R.id.textview_concluir_nome_bovino);
        textview_pai = (TextView) view.findViewById(R.id.textview_concluir_pai_bovino);
        textview_mae = (TextView) view.findViewById(R.id.textview_concluir_mae_bovino);
        textview_datanasc = (TextView) view.findViewById(R.id.textview_concluir_datanasc_bovino);
        textview_proprietario = (TextView) view.findViewById(R.id.textview_concluir_proprietario_bovino);
        textview_fazenda = (TextView) view.findViewById(R.id.textview_concluir_fazenda_bovino);
        textview_genero = (TextView) view.findViewById(R.id.textview_concluir_genero_bovino);
        textview_raca = (TextView) view.findViewById(R.id.textview_concluir_raca_bovino);
        textview_pelagem = (TextView) view.findViewById(R.id.textview_concluir_pelagem_bovino);
        textview_peso = (TextView) view.findViewById(R.id.textview_concluir_peso_bovino);
        textview_ecc = (TextView) view.findViewById(R.id.textview_concluir_ecc_bovino);

        textview_nome.setText(cadastroActivity.getBovino().getNomeBovino());
        textview_pai.setText(cadastroActivity.getBovino().getPai());
        textview_mae.setText(cadastroActivity.getBovino().getMae());
        try {
            textview_datanasc.setText(formataDateToString(cadastroActivity.getBovino().getDataNascimento()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        textview_proprietario.setText(cadastroActivity.getBovino().getProprietario().getNomeProprietario());
        textview_fazenda.setText(cadastroActivity.getBovino().getFazenda().getNomeFazenda());
        textview_genero.setText("Macho");
        textview_raca.setText("Nelore");
        textview_pelagem.setText("Cinza-Claro");
        textview_peso.setText("33.86" + " arrobas.");
        textview_ecc.setText("7");






        return view;
    }

    //chamada webservice
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public static String formataDateToString(Date data) throws Exception {
        if (data == null || data.equals(""))
            return null;
        String Auxdate = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Auxdate = formatter.format(data);
        } catch (Exception e) {
            throw e;
        }
        return Auxdate;
    }
}
