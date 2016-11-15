package com.magossi.simb.fragment;

/**
 * Created by RafaelMq on 13/10/2016.
 */

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.pinball83.maskededittext.MaskedEditText;
import com.magossi.simb.activity.MatrizActivity;
import com.magossi.simb.adapter.EccAdapter;
import com.magossi.simb.adapter.PesoAdapter;
import com.magossi.simb.domain.bovino.Ecc;
import com.magossi.simb.domain.bovino.Peso;
import com.magossi.simb.interfaces.EccBovinoListInterface;
import com.magossi.simb.interfaces.EccBovinoObjInterface;
import com.magossi.simb.interfaces.PesoBovinoObjInterface;
import com.magossi.simb.task.buscar.TaskBuscaEccBovinoList;
import com.magossi.simb.task.salvar.TaskSalvaEccBovinoObj;
import com.magossi.simb.task.salvar.TaskSalvaPesoBovinoObj;
import com.squareup.picasso.Picasso;

import com.magossi.simb.R;
import com.magossi.simb.domain.bovino.Bovino;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;


public class BovinoFragment extends Fragment implements EccBovinoObjInterface, PesoBovinoObjInterface{

    private TaskSalvaEccBovinoObj taskSalvaEccBovinoObj;
    private TaskSalvaPesoBovinoObj taskSalvaPesoBovinoObj;

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
    private TextView textView_ultimoPeso;
    private TextView textView_ultimoEcc;
    private Button button_verTodosPesos;
    private Button button_incluirPeso;
    private MaskedEditText editText_data;
    private MaskedEditText editText_peso;
    private Button button_verTodosEccs;
    private Button button_incluirEcc;
    private Spinner spinnerEcc;
    private Button button_fichamatriz;
    private ListView listView_pesos;
    private ListView listView_eccs;

    private AlertDialog.Builder dialog;
    private View dialogView;

    private Integer[] eccs = {1 , 2 , 3, 4, 5, 6, 7, 8, 9, 10};
    private Long eccId;
    private Date dataPesagem;
    private List<Ecc> eccsList;
    private List<Peso> pesosList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_busca_bovino, container, false);



        taskSalvaEccBovinoObj = new TaskSalvaEccBovinoObj(this.getContext(),this);
        taskSalvaPesoBovinoObj = new TaskSalvaPesoBovinoObj(this.getContext(),this);


        textview_nome = (TextView) view.findViewById(R.id.textview_busca_nome_bovino);
        textview_pai = (TextView) view.findViewById(R.id.textview_busca_pai_bovino);
        textview_mae = (TextView) view.findViewById(R.id.textview_busca_mae_bovino);
        textview_datanasc = (TextView) view.findViewById(R.id.textview_busca_datanasc_bovino);
        textview_proprietario = (TextView) view.findViewById(R.id.textview_busca_proprietario_bovino);
        textview_fazenda = (TextView) view.findViewById(R.id.textview_busca_fazenda_bovino);
        textview_genero = (TextView) view.findViewById(R.id.textview_busca_genero_bovino);
        textview_raca = (TextView) view.findViewById(R.id.textview_busca_raca_bovino);
        textview_pelagem = (TextView) view.findViewById(R.id.textview_busca_pelagem_bovino);
        textView_ultimoPeso = (TextView) view.findViewById(R.id.textview_busca_peso_bovino);
        textView_ultimoEcc = (TextView) view.findViewById(R.id.textview_busca_ecc_bovino);

        button_verTodosPesos = (Button) view.findViewById(R.id.button_buscar_peso_ver);
        button_incluirPeso = (Button) view.findViewById(R.id.button_buscar_peso_incluir);

        button_verTodosEccs = (Button) view.findViewById(R.id.button_buscar_ecc_ver);
        button_incluirEcc = (Button) view.findViewById(R.id.button_buscar_ecc_incluir);
        spinnerEcc = (Spinner) view.findViewById(R.id.spinner_incluir_ecc);



        return view;
    }

    // Método público chamado pela activity para atualizar os dados do bovino
    public void setBovino(final Bovino bovino) {
        this.bovino = bovino;

        textview_nome.setText(bovino.getNomeBovino());
        textview_pai.setText(bovino.getPai());
        textview_mae.setText(bovino.getMae());
        try {
            textview_datanasc.setText(formataDateToString(bovino.getDataNascimento()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        textview_proprietario.setText(bovino.getProprietario().getNomeProprietario());
        textview_fazenda.setText(bovino.getFazenda().getNomeFazenda());
        String auxString = bovino.getGenero().equals(true) ? "Macho" : "Fêmea";
        textview_genero.setText(auxString);
        textview_raca.setText(bovino.getRaca().getNomeRaca());
        textview_pelagem.setText(bovino.getPelagem().getNomePelagem());
        textView_ultimoPeso.setText(bovino.getPeso().get(bovino.getPeso().size()-1).getPeso()+" Kilos");
        textView_ultimoEcc.setText(bovino.getEcc().get(bovino.getEcc().size()-1).getEscore()+"");

        button_verTodosEccs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogVerTodosEccs(getContext());
            }
        });

        button_verTodosPesos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogVerTodosPesos(getContext());
            }
        });

        button_incluirPeso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogIncluirPeso(getContext(), bovino.getIdBovino()+"");

            }
        });

        button_incluirEcc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogIncluirEcc(getContext(), bovino.getIdBovino()+"");
            }
        });

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



                }
            });
        }

        final ImageView imgView = (ImageView) getView().findViewById(R.id.img);
        Picasso.with(getContext()).load(bovino.getUrlFoto()).fit().into(imgView);



    }

    public void dialogVerTodosEccs(Context context){
        dialog = new AlertDialog.Builder(context);
        dialogView = getActivity().getLayoutInflater().inflate( R.layout.fragment_layout_vertodosecc, null );

        eccsList = bovino.getEcc();

           Collections.sort(eccsList, new Comparator<Ecc>() {
               @Override
               public int compare(Ecc ecc1, Ecc ecc2) {

                   String id1 = ecc1.getDataInclusao().toString();
                   String id2 = ecc2.getDataInclusao().toString();
                   // decresente
                   return id2.compareTo(id1);
                    //cresente
                   //return id1.compareTo(id2);
               }
           });

        listView_eccs = (ListView) dialogView.findViewById(R.id.listview_vertodos_ecc);
        listView_eccs.setAdapter(new EccAdapter(getContext(), this.eccsList));
        listView_eccs.setFocusable(false);
        listView_eccs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });


        dialog.setView(dialogView);
        dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


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

    public void dialogVerTodosPesos(Context context){
        dialog = new AlertDialog.Builder(context);
        dialogView = getActivity().getLayoutInflater().inflate( R.layout.fragment_layout_vertodospeso, null );


        pesosList = bovino.getPeso();

        Collections.sort(pesosList, new Comparator<Peso>() {
            @Override
            public int compare(Peso peso1, Peso peso2) {

                String id1 = peso1.getDataPesagem().toString();
                String id2 = peso2.getDataPesagem().toString();
                // decresente
                return id2.compareTo(id1);
                //cresente
                //return id1.compareTo(id2);
            }
        });


        listView_pesos = (ListView) dialogView.findViewById(R.id.listview_vertodos_peso);
        listView_pesos.setAdapter(new PesoAdapter(context, pesosList));
        listView_pesos.setFocusable(false);
        listView_pesos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        dialog.setView(dialogView);
        //dialog.setMessage("Todas as Pesagens");
        dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


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

    public void dialogIncluirPeso(Context context, final String id){

        dialog = new AlertDialog.Builder(context);
        dialogView = getActivity().getLayoutInflater().inflate( R.layout.fragment_layout_incluirpeso, null );

        editText_data = (MaskedEditText) dialogView.findViewById(R.id.edittext_incluir_peso_data);
        editText_peso = (MaskedEditText) dialogView.findViewById(R.id.edittext_incluir_peso_valor);


        dialog.setView(dialogView);
        dialog.setMessage("Incluir Peso");
        dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {

                    if (editText_data.getUnmaskedText().toString().length() < 8) {
                        editText_data.setError("Insira uma Data");
                        Toast.makeText(getContext(), "Preencha a Data", Toast.LENGTH_SHORT).show();
                    } else if (editText_peso.getUnmaskedText().toString().length() < 4) {
                        editText_peso.setError("Insira um Peso Correto");
                        //Toast.makeText(getContext(), "Preencha a Data" , Toast.LENGTH_SHORT).show();
                    } else {

                        try {
                            dataPesagem = formataStringToDate(editText_data.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(getContext(), "Erro Fomato Data", Toast.LENGTH_SHORT).show();
                        }


                        Peso pesoLido = new Peso();
                        pesoLido.setDataPesagem(dataPesagem);
                        pesoLido.setPeso(Double.parseDouble(editText_peso.getText().toString()));
                        pesoLido.setStatus(true);

                        bovino.getPeso().add(pesoLido);

                        //chamada WebService
                        taskSalvaPesoBovinoObj.execute(bovino);

                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    Toast.makeText(getContext(), "Erro ao Salvar Peso", Toast.LENGTH_SHORT).show();
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

    public void dialogIncluirEcc(Context context, final String id){

        dialog = new AlertDialog.Builder(context);
        dialogView = getActivity().getLayoutInflater().inflate( R.layout.fragment_layout_incluirecc, null );

        spinnerEcc = (Spinner) dialogView.findViewById(R.id.spinner_incluir_ecc);
        ArrayAdapter<Integer> adapterSpinnerEcc = new ArrayAdapter<Integer>(dialogView.getContext(), android.R.layout.simple_spinner_item, eccs);
        adapterSpinnerEcc.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapterSpinnerOpcoes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerEcc.setAdapter(adapterSpinnerEcc);

        spinnerEcc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                eccId = id+1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dialog.setView(dialogView);
        dialog.setMessage("Incluir Ecc");
        dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                try {

                    Ecc eccEscolhido = new Ecc();
                    eccEscolhido.setEscore(eccId.intValue());
                    eccEscolhido.setDataInclusao(new Date());
                    eccEscolhido.setStatus(true);

                    bovino.getEcc().add(eccEscolhido);

                    taskSalvaEccBovinoObj.execute(bovino);

                }catch (Exception e){
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Erro ao Salvar Ecc", Toast.LENGTH_SHORT).show();
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

    @Override
    public void depoisAlteraPesoBovino(String url, String erro) {

        textView_ultimoPeso.setText(bovino.getPeso().get(bovino.getPeso().size()-1).getPeso()+" Kilos");

    }

    @Override
    public void depoisAlteraEccBovino(String url, String erro) {

        textView_ultimoEcc.setText(bovino.getEcc().get(bovino.getEcc().size()-1).getEscore()+"");

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




