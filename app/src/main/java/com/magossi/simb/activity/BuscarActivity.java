package com.magossi.simb.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.fragment.BovinosFragment;

/**
 * Created by RafaelMq on 08/09/2016.
 */



public class BuscarActivity extends AppCompatActivity {

    Toolbar toolbarPadrao;
    FragmentManager fm = getSupportFragmentManager();


    BovinosFragment bovinosFragment;

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
}