package com.magossi.simb;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.magossi.simb.activity.BuscarActivity;
import com.magossi.simb.activity.CadastroActivity;
import com.magossi.simb.activity.ScannerActivity;
import com.magossi.simb.extra.MainConfig;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbarPadrao;
    private ImageButton imgbuttonCadastro;
    private Spinner spinnerConfigServer;

    private AlertDialog.Builder dialog;
    private View dialogView;
    private String[] servidores;
    private String opcaoSpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        MainConfig mainConfig = new MainConfig();
        mainConfig.setUrl(MainConfig.getUrlLocal());

        servidores = MainConfig.getServidores();

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


                dialog = new AlertDialog.Builder( this );
                dialogView = getLayoutInflater().inflate( R.layout.fragment_layout_configmain, null );

                spinnerConfigServer = (Spinner) dialogView.findViewById(R.id.spinner_dado);
                ArrayAdapter<String> adapterConfigServer = new ArrayAdapter<String>(dialogView.getContext(), android.R.layout.simple_spinner_item,servidores);
                adapterConfigServer.setDropDownViewResource(android.R.layout.simple_list_item_checked);
                //adapterSpinnerOpcoes.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
                spinnerConfigServer.setAdapter(adapterConfigServer);

                spinnerConfigServer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        opcaoSpinner = parent.getItemAtPosition(position).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                dialog.setView(dialogView);
                dialog.setMessage("Configurações Gerais");
                dialog.setPositiveButton("  Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainConfig.setUrl(MainConfig.getServidor(opcaoSpinner));
                        Log.i("config", MainConfig.getUrl()+"");

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


                break;

            case R.id.menu_sobre:
                Toast.makeText(MainActivity.this, "Menu Sobre", Toast.LENGTH_SHORT).show();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
