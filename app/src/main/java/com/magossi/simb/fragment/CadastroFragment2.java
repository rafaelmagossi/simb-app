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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.magossi.simb.R;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.domain.Ecc;
import com.magossi.simb.domain.Pelagem;
import com.magossi.simb.domain.Raca;
import com.magossi.simb.interfaces.EccListInterface;
import com.magossi.simb.interfaces.PelagemListInterface;
import com.magossi.simb.interfaces.RacaListInterface;
import com.magossi.simb.task.TaskBuscaEccList;
import com.magossi.simb.task.TaskBuscaPelagemList;
import com.magossi.simb.task.TaskBuscaRacaList;

import java.util.List;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroFragment2 extends Fragment implements RacaListInterface, PelagemListInterface, EccListInterface {

    private CadastroActivity cadastroActivity;

    private CadastroFragment3 cadastroFragment3;

    private TaskBuscaRacaList taskBuscaRacaList;
    private TaskBuscaPelagemList taskBuscaPelagemList;
    private TaskBuscaEccList taskBuscaEccList;
    private Spinner spinnerRaca;
    private Spinner spinnerPelagem;
    private Spinner spinnerEcc;
    private Button buttonProximo2;
    int contador = 0;


    @Override
    public void onAttach(Activity myActivity) {
        super.onAttach(myActivity);
        this.cadastroActivity= (CadastroActivity) myActivity;
        cadastroFragment3 = cadastroActivity.getCadastroFragment3();

    }
    private String[] racas;

//            = { "Raça 1", "Raça 1", "Raça 2", "Raça 3", "Raça 4",
//            "Raça 5" , "Raça 6", "Raça 7", "Raça 8", "Raça 9", "Raça 10", "Raça 11"};

    private String[] pelagens;
//            = { "pelagen 1", "pelagen 2", "pelagen 3", "pelagen 4",
//            "pelagen 5" , "pelagen 6", "pelagen 7", "pelagen 8", "pelagen 9", "pelagen 10", "pelagen 11"};

    private Integer[] eccs;

//            = { "1", "2", "3", "4", "5" , "6", "7", "8", "9", "10" };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contador++;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_cadastro_2, container, false);
        setRetainInstance(true);

        buttonProximo2 = (Button) view.findViewById(R.id.button_proximo2);
        buttonProximo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("nome", "xibs");


                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                //cadastroFragment3.setArguments(bundle);
                ft.replace(R.id.layout_cadastro, cadastroFragment3);
                ft.commit();
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

        taskBuscaEccList = new TaskBuscaEccList(this.getContext(), this.getActivity(), this);
        taskBuscaEccList.execute("/ecc", "");
    }

    @Override
    public void depoisBuscaRaca(List<Raca> racas, String erro) {
        String[] r = new String[racas.size()];

        for (int i=0 ; i<racas.size() ; i++){
            if(racas.get(i).getStatus()) {
                r[i] = racas.get(i).getNomeRaca();
            }
        }

        this.racas = r;

        try{
            spinnerRaca = (Spinner) getView().findViewById(R.id.spinner_raca);
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

        this.pelagens = p;

        try{
            spinnerPelagem = (Spinner) getView().findViewById(R.id.spinner_pelagem);
            ArrayAdapter<String>  adapterSpinnerPelagem = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, p);
            adapterSpinnerPelagem.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            spinnerPelagem.setAdapter(adapterSpinnerPelagem);
        }catch (Exception e){

        }
    }

    @Override
    public void depoisBuscaEccs(List<Ecc> eccs, String erro) {

        Integer[] ec = new Integer[eccs.size()];

        for (int i=0 ; i<eccs.size() ; i++){
            if(eccs.get(i).getStatus()) {
                ec[i] =  eccs.get(i).getEscore();
            }
        }

        this.eccs = ec;

        try{
            spinnerEcc = (Spinner) getView().findViewById(R.id.spinner_ecc);
            ArrayAdapter<Integer>  adapterSpinnerEcc = new ArrayAdapter<Integer>(this.getActivity(), android.R.layout.simple_spinner_item, ec);
            adapterSpinnerEcc.setDropDownViewResource(android.R.layout.simple_list_item_checked);
            spinnerEcc.setAdapter(adapterSpinnerEcc);
        }catch (Exception e){

        }
    }
}
