package com.magossi.simb;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.magossi.simb.activity.BuscarActivity;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.activity.ScannerActivity;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarPadrao;
    ImageButton imgbuttonCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarPadrao = (Toolbar)findViewById(R.id.toolbar_padrao);
        imgbuttonCadastro = (ImageButton) findViewById(R.id.button_img_novocadastro);

        toolbarPadrao.setLogo(R.mipmap.ic_logo_vaca);
        toolbarPadrao.setTitle(getString(R.string.app_toolbar_name));
        toolbarPadrao.setSubtitle(getString(R.string.app_toolbar_subname));
        setSupportActionBar(toolbarPadrao);

    }

    // Image Buttons

    public void OnClickImgButtonNovoCadastro(View view) {

        Intent intent = new Intent(this,CadastroActivity.class);
        startActivity(intent);

    }

    public void OnClickImgButtonScanner(View view) {

        Intent intent = new Intent(this,ScannerActivity.class);
        startActivity(intent);

    }

    public void OnClickImgButtonBuscar(View view) {

        Intent intent = new Intent(this,BuscarActivity.class);
        startActivity(intent);
    }

    public void OnClickImgButtonTarefas(View view) {
    }


    //Menu da Toobar (Configurações e Sobre)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menu_config:
                Toast.makeText(MainActivity.this, "Menu Config", Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu_sobre:
                Toast.makeText(MainActivity.this, "Menu Sobre", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
