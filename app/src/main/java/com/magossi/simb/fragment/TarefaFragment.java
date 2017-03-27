package com.magossi.simb.fragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.magossi.simb.R;
import com.magossi.simb.activity.BovinoActivity;
import com.magossi.simb.activity.MatrizActivity;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.matriz.Inseminacao;
import com.magossi.simb.domain.matriz.Inseminador;
import com.magossi.simb.domain.tarefas.Tarefa;
import com.magossi.simb.domain.tarefas.TipoTarefaEnum;
import com.magossi.simb.interfaces.buscar.InseminadorListInterface;
import com.magossi.simb.interfaces.salvar.InseminacaoTarefaObjInterface;
import com.magossi.simb.interfaces.salvar.TarefaSalvarInterface;
import com.magossi.simb.task.buscar.TaskBuscaInseminadorList;
import com.magossi.simb.task.salvar.TaskSalvaInseminacaoTarefaObj;
import com.magossi.simb.task.salvar.TaskSalvaTarefaObj;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 17/11/2016.
 */
public class TarefaFragment extends Fragment implements InseminadorListInterface, InseminacaoTarefaObjInterface, TarefaSalvarInterface{

    private Tarefa tarefa;

    private TaskBuscaInseminadorList taskBuscaInseminadorList;
    private TaskSalvaInseminacaoTarefaObj taskSalvaInseminacaoTarefaObj;
    private TaskSalvaTarefaObj taskSalvaTarefaObj;

    private TextView textview_nome;
    private TextView textview_fazenda;
    private TextView textview_raca;
    private TextView textview_pelagem;
    private Button button_verBovino;
    private Button button_executarTarefa;

    private TextView textView_TextoInsemonador;
    private RadioGroup radioGroup_inseminador;
    private Spinner spinnerInseminador;
    private MaskedEditText editText_dataInseminacao;
    private TextInputLayout editText_touro;



    //private String[] inseminadores = {"Joao Cesar" , "Armando Silva" , "Pedro Ferreira"};
    private String[] inseminadoresArray;
    private List<Inseminacao> inseminacaosNovaList;
    private List<Inseminador> inseminadoresList;
    private Long inseminadorId;
    private String nomeTouro;
    private Date dataInseminacao;
    private Boolean monta;
    private RadioButton radioButtonMonta;

    private AlertDialog.Builder dialog;
    private View dialogView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        taskSalvaInseminacaoTarefaObj = new TaskSalvaInseminacaoTarefaObj(this.getContext(),this);
        taskSalvaTarefaObj = new TaskSalvaTarefaObj(this.getContext(),this);
        taskBuscaInseminadorList = new TaskBuscaInseminadorList(this.getContext(), this.getActivity(), this);
        taskBuscaInseminadorList.execute("/inseminador", "");
    }



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

        if (this.tarefa.getStatusDaTarefa()){
            button_executarTarefa.setVisibility(View.GONE);
        }else {

            button_verBovino.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bovino b = tarefa.getBovinoMatriz();
                    Intent intent = new Intent(getContext(), BovinoActivity.class);
                    intent.putExtra("bovino", b);
                    startActivity(intent);
                }
            });

            if (tarefa.getTipoTarefa().equals(TipoTarefaEnum.Inseminação)) {

                button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(getContext(),"Inseminacao",Toast.LENGTH_LONG).show();
                        dialogExecutarTarefaInseminacao(getContext());
                    }
                });
            } else if (tarefa.getTipoTarefa().equals(TipoTarefaEnum.DiagnosticoDeGestacao)) {
                button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else if (tarefa.getTipoTarefa().equals(TipoTarefaEnum.Parto)) {
                button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else if (tarefa.getTipoTarefa().equals(TipoTarefaEnum.IntervaloParto)) {
                button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            } else if (tarefa.getTipoTarefa().equals(TipoTarefaEnum.CadastrarBovino)) {
                button_executarTarefa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });

            }

        }

        final ImageView imgView = (ImageView) getView().findViewById(R.id.imgView_tarefa);
        Picasso.with(getContext()).load(tarefa.getBovinoMatriz().getUrlFoto()).fit().into(imgView);
    }

    public void dialogExecutarTarefaInseminacao(final Context context){

        dialog = new AlertDialog.Builder(context);
        dialogView = getActivity().getLayoutInflater().inflate( R.layout.fragment_layout_tarefa_inseminacao, null );

        radioGroup_inseminador = (RadioGroup) dialogView.findViewById(R.id.radiogroup_tarefa_inseminacao_monta);
        textView_TextoInsemonador = (TextView) dialogView.findViewById(R.id.textview_tarefa_inseminacao_inseminador);


        spinnerInseminador = (Spinner) dialogView.findViewById(R.id.spinner_inseminador);


        radioGroup_inseminador.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId) {
                    case R.id.radiobutton_monta_sim:
                        Toast.makeText(context , "ID: "+checkedId ,Toast.LENGTH_LONG).show();
                        spinnerInseminador.setVisibility(View.GONE);
                        textView_TextoInsemonador.setVisibility(View.GONE);
                        break;
                    case R.id.radiobutton_monta_nao:
                        Toast.makeText(context , "ID: "+checkedId ,Toast.LENGTH_LONG).show();
                        spinnerInseminador.setVisibility(View.VISIBLE);
                        textView_TextoInsemonador.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });

        ArrayAdapter<String> adapterInseminador = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item, inseminadoresArray);
        adapterInseminador.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapterSpinnerOpcoes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerInseminador.setAdapter(adapterInseminador);

        spinnerInseminador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                inseminadorId = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        editText_touro = (TextInputLayout) dialogView.findViewById(R.id.input_layout_touro);
        editText_dataInseminacao = (MaskedEditText) dialogView.findViewById(R.id.edittext_tarefa_inseminacao_datainsem);

        dialog.setView(dialogView);
        //dialog.setMessage("Incluir Peso");

        dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                nomeTouro = editText_touro.getEditText().getText().toString();

                int selectedId = radioGroup_inseminador.getCheckedRadioButtonId();
                radioButtonMonta = (RadioButton) dialogView.findViewById(selectedId);
                monta = "Sim".equals(radioButtonMonta.getText().toString()) ? true : false;


                if (nomeTouro.isEmpty()) {
                    editText_touro.getEditText().setError("Insira um Nome");
                } else if (editText_dataInseminacao.getUnmaskedText().toString().length() < 8) {
                    editText_dataInseminacao.setError("Insira uma Data");
                    Toast.makeText(getContext(), "Preencha a Data", Toast.LENGTH_SHORT).show();
                } else {


                    try {


                        try {
                            dataInseminacao = formataStringToDate(editText_dataInseminacao.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Erro Fomato Data", Toast.LENGTH_SHORT).show();
                        }


                        if (tarefa.getBovinoMatriz().getFichaMatriz().getInseminacao().isEmpty()) {
                            inseminacaosNovaList = new ArrayList<>();
                            tarefa.getBovinoMatriz().getFichaMatriz().setInseminacao(inseminacaosNovaList);

                        }


                        Inseminacao inseminacao = new Inseminacao();
                        inseminacao.setDataDaInseminacao(dataInseminacao);
                        inseminacao.setMonta(monta);
                        if (!monta) {
                            inseminacao.setInseminador(inseminadoresList.get(inseminadorId.intValue()));
                        }
                        inseminacao.setTouro(nomeTouro);

                        tarefa.getBovinoMatriz().getFichaMatriz().getInseminacao().add(inseminacao);


                        tarefa.setStatusDaTarefa(true);
                        tarefa.setDataConclusao(new Date());

                        //Chamada Webservice Atualizar tarefa
                        taskSalvaInseminacaoTarefaObj.execute(tarefa);



                    } catch (Exception ex) {
                        ex.printStackTrace();
                        Toast.makeText(getContext(), "Erro ao Finalizar Tarefa", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });



        dialog.setNegativeButton("Cancelar            ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = dialog.create();
        dialog.show();

    }

    public void dialogExecutarTarefaDiagnostico(final Context context){

    }

    @Override
    public void depoisBuscaInseminadores(List<Inseminador> inseminadores, String erro) {

        String[] ins = new String[inseminadores.size()];

        for (int i=0 ; i<inseminadores.size() ; i++){
            if(inseminadores.get(i).getStatus()) {
                ins[i] = inseminadores.get(i).getNomeInseminador();
            }
        }
        this.inseminadoresArray = ins;
        this.inseminadoresList = inseminadores;

    }

    @Override
    public void depoisAlteraInseminacaoTarefa(String url, String erro) {


        if (url != null && erro == null ){
//            Tarefa tarefa = new Tarefa();
//            tarefa.setBovinoMatriz(this.tarefa.getBovinoMatriz());
//            tarefa.setTipoTarefa(TipoTarefaEnum.DiagnosticoDeGestacao);

/*            taskSalvaTarefaObj.execute(tarefa);*/

        }



    }

    @Override
    public void depoisSalvarTarefa(String url, String erro) {

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


