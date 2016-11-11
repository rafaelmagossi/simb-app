package com.magossi.simb.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.magossi.simb.R;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.domain.Ecc;
import com.magossi.simb.domain.Pelagem;
import com.magossi.simb.domain.Peso;
import com.magossi.simb.domain.Raca;
import com.magossi.simb.interfaces.EccListInterface;
import com.magossi.simb.interfaces.PelagemListInterface;
import com.magossi.simb.interfaces.RacaListInterface;
import com.magossi.simb.task.TaskBuscaEccList;
import com.magossi.simb.task.TaskBuscaPelagemList;
import com.magossi.simb.task.TaskBuscaRacaList;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment2 extends Fragment implements RacaListInterface, PelagemListInterface {

    private CadastroActivity cadastroActivity;

    private CadastroFragment3 cadastroFragment3;

    private TaskBuscaRacaList taskBuscaRacaList;
    private TaskBuscaPelagemList taskBuscaPelagemList;
    private TaskBuscaEccList taskBuscaEccList;

    private List<Raca> racasList;
    private List<Pelagem> pelagemsList;
    private List<Ecc> eccsList;
    private boolean genero;
    private Long racaId;
    private Long pelagemId;
    private Long eccId;

    private RadioGroup radioGroupGenero;
    private RadioButton radioButtonGenero;
    private Spinner spinnerRaca;
    private Spinner spinnerPelagem;
    private Spinner spinnerEcc;
    private MaskedEditText editTextPeso;
    private Button buttonProximo2;





    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.cadastroActivity= (CadastroActivity) myActivity;
        cadastroFragment3 = cadastroActivity.getCadastroFragment3();

    }
    private String[] racas;
    private String[] pelagens;
    private Integer[] eccs = {1 , 2 , 3, 4, 5, 6, 7, 8, 9, 10};



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_2, container, false);
        setRetainInstance(true);

//        Integer[] ec = new Integer[10];
//        int valor=0;
//
//        for (int i=0 ; i<10 ; i++){
//                ec[i] =  valor++;
//        }



        radioGroupGenero = (RadioGroup) view.findViewById(R.id.radiogroup_genero);
        buttonProximo2 = (Button) view.findViewById(R.id.button_proximo2);
        spinnerRaca = (Spinner) view.findViewById(R.id.spinner_raca);
        spinnerPelagem = (Spinner) view.findViewById(R.id.spinner_pelagem);
        spinnerEcc = (Spinner) view.findViewById(R.id.spinner_ecc);
        editTextPeso = (MaskedEditText) view.findViewById(R.id.edittext_peso);

        try{

            ArrayAdapter<Integer>  adapterSpinnerEcc = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, eccs);
            adapterSpinnerEcc.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            spinnerEcc.setAdapter(adapterSpinnerEcc);
        }catch (Exception e){

        }
        spinnerRaca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                racaId = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerPelagem.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pelagemId = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinnerEcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eccId = id+1;
                //Toast.makeText(getContext(), ""+(id+1), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonProximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editTextPeso.getUnmaskedText().toString().length() < 4) {
                    editTextPeso.setError("Insira um Peso Correto");
                   // Toast.makeText(getContext(), "Preencha a Data" , Toast.LENGTH_SHORT).show();
                }else {

                    int selectedId = radioGroupGenero.getCheckedRadioButtonId();
                    radioButtonGenero = (RadioButton) getView().findViewById(selectedId);
                    genero = "Macho".equals(radioButtonGenero.getText().toString()) ? true : false;

                    Ecc eccEscolhido = new Ecc();
                    Peso pesoLido = new Peso();
                    List<Ecc> eccsNovaLista = new ArrayList<>();
                    List<Peso> pesosNovaLista = new ArrayList<>();

                    //eccEscolhido.setIdECC(new Long(1));
                    eccEscolhido.setEscore(eccId.intValue());
                    eccEscolhido.setStatus(true);

                    pesoLido.setDataPesagem(new Date());
                    pesoLido.setPeso(Double.parseDouble(editTextPeso.getText().toString()));
                    pesoLido.setStatus(true);

                    eccsNovaLista.add(0, eccEscolhido);
                    pesosNovaLista.add(0, pesoLido);
                    //Toast.makeText(getContext(), eccsNovaLista.get(1).getIdECC().toString(), Toast.LENGTH_LONG).show();

                    cadastroActivity.getBovino().setGenero(genero);
                    cadastroActivity.getBovino().setRaca(racasList.get(racaId.intValue()));
                    cadastroActivity.getBovino().setPelagem(pelagemsList.get(pelagemId.intValue()));
                    cadastroActivity.getBovino().setEcc(eccsNovaLista);
                    cadastroActivity.getBovino().setPeso(pesosNovaLista);


                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.layout_cadastro, cadastroFragment3);
                    ft.commit();

                }
            }
        });

        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup_genero);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener(){


            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                boolean macho = R.id.radiobutton_macho == checkedId;
                boolean femea = R.id.radiobutton_femea == checkedId;
                if (macho) {
                    Log.i("LOG", "-> Macho: "+checkedId );
                }else if(femea){
                    Log.i("LOG", "-> Femea: "+checkedId );
                }

            }
        });




        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskBuscaRacaList = new TaskBuscaRacaList(this.getContext(), this.getActivity(), this);
        taskBuscaRacaList.execute("/raca", "");

        taskBuscaPelagemList = new TaskBuscaPelagemList(this.getContext(), this.getActivity(), this);
        taskBuscaPelagemList.execute("/pelagem", "");

//        taskBuscaEccList = new TaskBuscaEccList(this.getContext(), this.getActivity(), this);
//        taskBuscaEccList.execute("/ecc", "");
    }

    @Override
    public void depoisBuscaRaca(List<Raca> racas, String erro) {
        String[] r = new String[racas.size()];

        for (int i=0 ; i<racas.size() ; i++){
            if(racas.get(i).getStatus()) {
                r[i] = racas.get(i).getNomeRaca();
            }
        }
        this.racasList = racas;
        this.racas = r;

        try{

            ArrayAdapter<String> adapterSpinnerRaca = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, r);
            adapterSpinnerRaca.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            //adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            spinnerRaca.setAdapter(adapterSpinnerRaca);
        }catch(Exception e){

        }
    }

    @Override
    public void depoisBuscaPelagens(List<Pelagem> pelagens, String erro) {

        String[] p = new String[pelagens.size()];

        for (int i=0 ; i<pelagens.size() ; i++){
            if(pelagens.get(i).getStatus()) {
                p[i] = pelagens.get(i).getNomePelagem();
            }
        }
        this.pelagemsList = pelagens;
        this.pelagens = p;

        try{
            ArrayAdapter<String>  adapterSpinnerPelagem = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, p);
            adapterSpinnerPelagem.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            spinnerPelagem.setAdapter(adapterSpinnerPelagem);
        }catch (Exception e){

        }
    }

//    @Override
//    public void depoisBuscaEccs(List<Ecc> eccs, String erro) {
//
//        Integer[] ec = new Integer[eccs.size()];
//
//        for (int i=0 ; i<eccs.size() ; i++){
//            if(eccs.get(i).getStatus()) {
//                ec[i] =  eccs.get(i).getEscore();
//            }
//        }
//        this.eccsList = eccs;
//        this.eccs = ec;
//
//        try{
//
//            ArrayAdapter<Integer>  adapterSpinnerEcc = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, ec);
//            adapterSpinnerEcc.setDropDownViewResource(android.R.layout.simple_list_item_checked);
//            spinnerEcc.setAdapter(adapterSpinnerEcc);
//        }catch (Exception e){
//
//        }
//    }
}
