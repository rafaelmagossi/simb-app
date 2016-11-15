package com.magossi.simb.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.interfaces.BovinoObjInterface;
import com.magossi.simb.task.salvar.TaskSalvaBobinoObj;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment3 extends Fragment implements BovinoObjInterface {

    private CadastroActivity cadastroActivity;


    private TextView textview_nome;
    private TextView textview_pai;
    private TextView textview_mae;
    private TextView textview_datanasc;
    private TextView textview_proprietario;
    private TextView textview_fazenda;
    private TextView textview_genero;
    private TextView textview_raca;
    private TextView textview_pelagem;
    private TextView textview_peso;
    private TextView textview_ecc;

    private Button buttonConcluir;
    TaskSalvaBobinoObj taskSalvaBobinoObj;

    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.cadastroActivity= (CadastroActivity) myActivity;

    }

    public CadastroFragment3(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_3, container, false);
        Log.i("fragments", "-> onCreateFragment-3: "+getActivity().getClass().getName() );

        taskSalvaBobinoObj = new TaskSalvaBobinoObj(this.getContext(),this);

        buttonConcluir = (Button) view.findViewById(R.id.button_concluir);

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
        String auxString = cadastroActivity.getBovino().getGenero().equals(true) ? "Macho" : "Fêmea";
        textview_genero.setText(auxString);
        textview_raca.setText(cadastroActivity.getBovino().getRaca().getNomeRaca());
        textview_pelagem.setText(cadastroActivity.getBovino().getPelagem().getNomePelagem());
        textview_ecc.setText(cadastroActivity.getBovino().getEcc().get(0).getEscore()+"");
        textview_peso.setText(cadastroActivity.getBovino().getPeso().get(0).getPeso() + "  Kilos");


        buttonConcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                taskSalvaBobinoObj.execute(cadastroActivity.getBovino());
     //dsadas

            }
        });

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

    @Override
    public void depoisSalvarBovino(String url, String erro) {

        Intent intent = new Intent(getContext(),MainActivity.class);
        startActivity(intent);

    }
}
