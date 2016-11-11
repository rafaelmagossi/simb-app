package com.magossi.simb.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.magossi.simb.R;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.domain.Bovino;
import com.magossi.simb.domain.Fazenda;
import com.magossi.simb.domain.Proprietario;
import com.magossi.simb.interfaces.FazendaListInterface;
import com.magossi.simb.interfaces.ProprietarioListInterface;
import com.magossi.simb.task.TaskBuscaFazendaList;
import com.magossi.simb.task.TaskBuscaProprietarioList;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment1 extends Fragment implements  ProprietarioListInterface, FazendaListInterface {




    private CadastroActivity cadastroActivity;
    private CadastroFragment2 cadastroFragment2;

    private TaskBuscaProprietarioList taskBuscaProprietarioList = null;
    private TaskBuscaFazendaList taskBuscaFazendaList = null;
    private String[] proprietarios = null;
    private String[] fazendas = null;
    private int contador=0;

    private String nomeBovino;
    private String nomePai;
    private String nomeMae;
    private Date dataNasc;
    private List<Proprietario> proprietariosList;
    private List<Fazenda> fazendasList;

    private Long proprietarioId;
    private Long fazendaId;

    private TextInputLayout editTextNomeBovino;
    private TextInputLayout editTextNomePai;
    private TextInputLayout editTextNomeMae;
    private MaskedEditText editTextDataNasc;
    private Spinner spinnerProprietario;
    private Spinner spinnerFazenda;
    private Button buttonProximo1;


    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.cadastroActivity= (CadastroActivity) myActivity;
        cadastroFragment2 = cadastroActivity.getCadastroFragment2();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("fragment", "CadastroFrag1 -  onCreate()" );
        contador++;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("contador",contador);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("fragment", "CadastroFrag1 -  onCreateView()" );
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_1, container, false);
        setRetainInstance(true);

        editTextNomeBovino = (TextInputLayout) view.findViewById(R.id.input_layout_nome);
        editTextNomePai =(TextInputLayout) view.findViewById(R.id.input_layout_pai);
        editTextNomeMae = (TextInputLayout) view.findViewById(R.id.input_layout_mae);
        editTextDataNasc = (MaskedEditText) view.findViewById(R.id.edittext_datanasc);
        buttonProximo1 = (Button) view.findViewById(R.id.button_proximo1);
        spinnerProprietario = (Spinner) view.findViewById(R.id.spinner_proprietario);
        spinnerFazenda = (Spinner) view.findViewById(R.id.spinner_fazenda);





        buttonProximo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nomePai = editTextNomePai.getEditText().getText().toString();
                nomeMae = editTextNomeMae.getEditText().getText().toString();
                nomeBovino = editTextNomeBovino.getEditText().getText().toString();

                if(nomeBovino.isEmpty()){
                    editTextNomeBovino.getEditText().setError("Insira um Nome");
                    Toast.makeText(getContext(), "Preencha Todos os Campos" , Toast.LENGTH_SHORT).show();
                }else if ( nomePai.isEmpty()){
                    editTextNomePai.getEditText().setError("Insira um Nome");
                    Toast.makeText(getContext(), "Preencha Todos os Campos" , Toast.LENGTH_SHORT).show();
                }else if(nomeMae.isEmpty()){
                    editTextNomeMae.getEditText().setError("Insira um Nome");
                    Toast.makeText(getContext(), "Preencha Todos os Campos" , Toast.LENGTH_SHORT).show();
                }else if(editTextDataNasc.getUnmaskedText().toString().length() < 8) {
                    editTextDataNasc.setError("Insira uma Data");
                    Toast.makeText(getContext(), "Preencha a Data" , Toast.LENGTH_SHORT).show();
                }else{
                    editTextNomeBovino.setErrorEnabled(false);
                    editTextNomePai.setErrorEnabled(false);
                    editTextNomeMae.setErrorEnabled(false);


                    try {
                        dataNasc = formataStringToDate(editTextDataNasc.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(getContext(), "Erro Fomato Data" , Toast.LENGTH_SHORT).show();
                    }

                    cadastroActivity.getBovino().setNomeBovino(nomeBovino);
                    cadastroActivity.getBovino().setPai(nomePai);
                    cadastroActivity.getBovino().setMae(nomeMae);
                    cadastroActivity.getBovino().setDataNascimento(dataNasc);
                    cadastroActivity.getBovino().setProprietario(proprietariosList.get(proprietarioId.intValue()));
                    cadastroActivity.getBovino().setFazenda(fazendasList.get(fazendaId.intValue()));

                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.layout_cadastro, cadastroFragment2 , "FRAG2");
                    ft.commit();

                }



            }
        });

        spinnerProprietario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                proprietarioId = id;
                //Toast.makeText(getContext(), "Proprietario: "+id , Toast.LENGTH_SHORT).show();
               // Toast.makeText(getContext(), "Proprietario: "+parent.getItemAtPosition(position) , Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerFazenda.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fazendaId = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        Log.i("fragment", "CONTADOR =  "+contador );




        return view;
    }


    //chamada webservice
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(savedInstanceState == null ) {

            Log.i("fragment", "CadastroFrag1 -  onActivityCreated()");

            taskBuscaProprietarioList = new TaskBuscaProprietarioList(this.getContext(), this.getActivity(), this);
            taskBuscaProprietarioList.execute("/proprietario", "");

            taskBuscaFazendaList = new TaskBuscaFazendaList(this.getContext(), this.getActivity(), this);
            taskBuscaFazendaList.execute("/fazenda", "");
        }



    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i("fragment", "CadastroFrag1 -  onCreate()" );
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("fragment", "CadastroFrag1 -  onPause()" );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("fragment", "CadastroFrag1 -  onStop()" );
//        Log.i("thread", "-> taskProp - "+taskBuscaProprietarioList.getStatus().name() );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("fragment", "CadastroFrag1 -  onDestroy()" );
    }

    @Override
    public void depoisBuscaFazendas(List<Fazenda> fazendas, String erro) {

        String[] f = new String[fazendas.size()];

        for (int i=0 ; i<fazendas.size() ; i++){
            if(fazendas.get(i).getStatus()) {
                f[i] = fazendas.get(i).getNomeFazenda();
            }
        }
        this.fazendasList = fazendas;
        this.fazendas = f;

        try {

            ArrayAdapter<String>  adapterSpinnerFazenda = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, this.fazendas);
            adapterSpinnerFazenda.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            spinnerFazenda.setAdapter(adapterSpinnerFazenda);

        }catch (Exception e){

        }




    }

    @Override
    public void depoisBuscaProp(List<Proprietario> proprietarios, String erro) {

        String[] p = new String[proprietarios.size()];

        for (int i=0 ; i<proprietarios.size() ; i++){
            if(proprietarios.get(i).getStatus()) {
                p[i] = proprietarios.get(i).getNomeProprietario();
            }
        }

        this.proprietariosList = proprietarios;
        this.proprietarios = p;

        try {

        ArrayAdapter<String> adapterSpinnerProprietario = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, this.proprietarios);
        adapterSpinnerProprietario.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerProprietario.setAdapter(adapterSpinnerProprietario);

        }catch (Exception e){

        }
        taskBuscaProprietarioList.cancel(true);
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