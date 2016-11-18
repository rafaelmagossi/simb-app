package com.magossi.simb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.magossi.simb.R;
import com.magossi.simb.activity.BovinoActivity;
import com.magossi.simb.activity.MatrizActivity;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.tarefas.Tarefa;
import com.magossi.simb.domain.tarefas.TipoTarefaEnum;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by RafaelMq on 17/11/2016.
 */
public class TarefaFragment extends Fragment {

    private Tarefa tarefa;

    private TextView textview_nome;
    private TextView textview_fazenda;
    private TextView textview_raca;
    private TextView textview_pelagem;
    private Button button_verBovino;
    private Button button_executarTarefa;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_tarefa, container, false);

//        taskSalvaEccBovinoObj = new TaskSalvaEccBovinoObj(this.getContext(),this);
//        taskSalvaPesoBovinoObj = new TaskSalvaPesoBovinoObj(this.getContext(),this);
//        taskSalvaFichaMatrizBovinoObj = new TaskSalvaFichaMatrizBovinoObj(this.getContext(),this);
//        taskSalvaTarefaObj = new TaskSalvaTarefaObj(this.getContext(),this);



        textview_nome = (TextView) view.findViewById(R.id.textview_tarefa_nome_bovino);
        textview_fazenda = (TextView) view.findViewById(R.id.textview_tarefa_fazenda_bovino);
        textview_raca = (TextView) view.findViewById(R.id.textview_tarefa_raca_bovino);
        textview_pelagem = (TextView) view.findViewById(R.id.textview_tarefa_pelagem_bovino);

        button_verBovino = (Button) view.findViewById(R.id.button_tarefa_bovino_ver);
        button_executarTarefa = (Button) view.findViewById(R.id.button_tarefa_executar);

        return view;
    }

    // Método público chamado pela activity para atualizar os dados do bovino

    public void setTarefa(final Tarefa tarefa) {
        this.tarefa = tarefa;



        textview_nome.setText(tarefa.getBovinoMatriz().getNomeBovino());
        textview_fazenda.setText(tarefa.getBovinoMatriz().getFazenda().getNomeFazenda());
        textview_pelagem.setText(tarefa.getBovinoMatriz().getPelagem().getNomePelagem());
        textview_raca.setText(tarefa.getBovinoMatriz().getRaca().getNomeRaca());

        button_verBovino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bovino b = tarefa.getBovinoMatriz();
                Intent intent = new Intent(getContext(), BovinoActivity.class);
                intent.putExtra("bovino", b);
                startActivity(intent);
            }
        });

        if(tarefa.getTipoTarefa().equals(TipoTarefaEnum.Inseminação)){

            button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),"Inseminacao",Toast.LENGTH_LONG).show();
                }
            });
        }else if(tarefa.getTipoTarefa().equals(TipoTarefaEnum.DiagnosticoDeGestacao)){
            button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }else if(tarefa.getTipoTarefa().equals(TipoTarefaEnum.Parto)){
            button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }else if(tarefa.getTipoTarefa().equals(TipoTarefaEnum.IntervaloParto)){
            button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }else if(tarefa.getTipoTarefa().equals(TipoTarefaEnum.CadastrarBovino)){
            button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }

        final ImageView imgView = (ImageView) getView().findViewById(R.id.imgView_tarefa);
        Picasso.with(getContext()).load(tarefa.getBovinoMatriz().getUrlFoto()).fit().into(imgView);
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

    public static Date formataStringToDate(String data) throws Exception {
        if (data == null || data.equals(""))
            return null;
        Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }


}


