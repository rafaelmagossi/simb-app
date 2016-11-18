package com.magossi.simb.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.magossi.simb.R;
import com.magossi.simb.adapter.DiagnosticoAdapter;
import com.magossi.simb.adapter.InseminacaoAdapter;
import com.magossi.simb.adapter.PartoAdapter;
import com.magossi.simb.domain.matriz.DiagnosticoGestacao;
import com.magossi.simb.domain.matriz.Inseminacao;
import com.magossi.simb.domain.matriz.Parto;

import java.util.List;

public class MatrizActivity extends AppCompatActivity  {
    private Toolbar toolbarPadrao;
    private ListView listView;
    private Button button_diagnostico_incluir;
    private View dialogView;
    private AlertDialog.Builder dialog;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_matriz);

        button_diagnostico_incluir = (Button) findViewById(R.id.button_diagnostico_incluir);
        button_diagnostico_incluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                dialog = new AlertDialog.Builder(v.getContext());
//                dialogView = getLayoutInflater().inflate( R.layout.item_parto, null );
//
//                listView = (ListView) findViewById(R.id.listview_parto);
//                List<Parto> partos = Parto.getPartos();
//                listView.setAdapter(new PartoAdapter(v.getContext(), partos));
//                listView.setFocusable(false);
//
//
//                dialog.setView(dialogView);
//                dialog.setTitle("Partos");
//                dialog.setPositiveButton("  OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        //onBackPressed();
//                    }
//                });
//
//                dialog.setNegativeButton("Cancelar            ", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//
//                AlertDialog alertDialog = dialog.create();
//                dialog.show();

            }
        });



        // ListView Inseminação
        listView = (ListView) findViewById(R.id.listview_inseminacao);
//        List<Inseminacao> inseminacaos = Inseminacao.getInseminacoes();
//        listView.setAdapter(new InseminacaoAdapter(this, inseminacaos));
//        listView.setFocusable(false);
//        listView.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), "Inseminacao: "+position , Toast.LENGTH_SHORT).show();
//                Log.i("TESTE", "entrou");
//            }
//        });


        // ListView Diagnostico
        listView = (ListView) findViewById(R.id.listview_diagnostico);
        List<DiagnosticoGestacao> diagnosticos = DiagnosticoGestacao.getDiagnosticos();
        listView.setAdapter(new DiagnosticoAdapter(this, diagnosticos));
        listView.setFocusable(false);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Diagnostico: "+position , Toast.LENGTH_SHORT).show();
            }
        });


        // ListView parto
        listView = (ListView) findViewById(R.id.listview_parto);
        List<Parto> partos = Parto.getPartos();
        listView.setAdapter(new PartoAdapter(this, partos));
        listView.setFocusable(false);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "Parto: "+position , Toast.LENGTH_SHORT).show();
            }
        });
    }





}
