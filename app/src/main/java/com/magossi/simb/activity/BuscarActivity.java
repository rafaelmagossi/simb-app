package com.magossi.simb.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.fragment.BovinosFragment;

/**
 * Created by RafaelMq on 08/09/2016.
 */



public class BuscarActivity extends AppCompatActivity {

    protected Toolbar toolbarPadrao;
    protected FloatingActionButton button_buscar;
    protected  FragmentManager fm = getSupportFragmentManager();

     String[] opcoes = { "Nome", "Pai", "Mae", "Raça", "Pelagem",
            "Fazenda" , "Proprietario", "Data Nascimento", "ECC"};


    BovinosFragment bovinosFragment;
     View dialogView;
     AlertDialog.Builder dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        toolbarPadrao = (Toolbar)findViewById(R.id.toolbar_busca);
        toolbarPadrao.setLogo(R.drawable.ic_toobar_busca);
        toolbarPadrao.setTitle(" Buscar");
        setSupportActionBar(toolbarPadrao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarPadrao.setNavigationIcon(R.drawable.ic_toolbar_voltar);

        button_buscar = (FloatingActionButton) findViewById(R.id.button_buscar_dado);






//        if(savedInstanceState == null){
//
//            FragmentTransaction ft = fm.beginTransaction();
//            bovinosFragment = new BovinosFragment();
//            ft.add(R.id.layout_busca, bovinosFragment);
//            ft.addToBackStack(null);
//            ft.commit();
//        }


//        // Atualiza o carro no fragment
//        BovinosFragment bf = (BovinosFragment) getSupportFragmentManager().findFragmentById(R.id.BovinoFragment);
//        Bovino b = (Bovino) getIntent().getSerializableExtra("bovino");
//        bf.setBovino(b);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar_busca, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_home:
                //Toast.makeText(CadastroActivity.this, "Menu Config", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void OnClickButtonBuscarDado(View view) {



        dialog = new AlertDialog.Builder( this );
        dialogView = getLayoutInflater().inflate( R.layout.fragment_layout_busca_dadospesquisa, null );

        Spinner spinnerOpcoes = (Spinner) dialogView.findViewById(R.id.spinner_dado);
        ArrayAdapter<String> adapterSpinnerOpcoes = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item, opcoes);
        adapterSpinnerOpcoes.setDropDownViewResource(android.R.layout.simple_list_item_checked);
        //adapterSpinnerOpcoes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerOpcoes.setAdapter(adapterSpinnerOpcoes);


        dialog.setView(dialogView);
        dialog.setMessage("Digite o Dado a para realizar a pesquisa");
        dialog.setPositiveButton("  Pesquisar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //onBackPressed();
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
}
