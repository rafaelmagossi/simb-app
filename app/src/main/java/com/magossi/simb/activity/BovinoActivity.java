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
import android.view.View;
import android.widget.Button;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.domain.Bovino;
import com.magossi.simb.fragment.BovinoFragment;
import com.magossi.simb.fragment.BovinosFragment;

/**
 * Created by RafaelMq on 13/10/2016.
 */
public class BovinoActivity extends AppCompatActivity {

    private Toolbar toolbarPadrao;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bovino);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        BovinoFragment bf = (BovinoFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_buscar_bovino);
        Bovino b = (Bovino) getIntent().getSerializableExtra("bovino");
        bf.setBovino(b);

        toolbarPadrao = (Toolbar)findViewById(R.id.toolbar_busca);
        toolbarPadrao.setLogo(R.drawable.ic_toolbar_cadastro);
        toolbarPadrao.setTitle(b.getNomeBovino());
        setSupportActionBar(toolbarPadrao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarPadrao.setNavigationIcon(R.drawable.ic_toolbar_voltar);







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

