package com.magossi.simb.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.adapter.CadastroFragmentAdapter;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.fragment.CadastroFragment1;
import com.magossi.simb.fragment.CadastroFragment2;
import com.magossi.simb.fragment.CadastroFragment3;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class CadastroActivity extends AppCompatActivity {

    private FragmentManager fm = getSupportFragmentManager() ;
    private CadastroFragmentAdapter fa = new CadastroFragmentAdapter(fm);
    private CadastroFragment1 cadastroFragment1;
    private CadastroFragment2 cadastroFragment2;
    private CadastroFragment3 cadastroFragment3;

    private Bovino bovino = new Bovino();

    public CadastroActivity(){
        cadastroFragment1 = new CadastroFragment1();
        cadastroFragment2 = new CadastroFragment2();
        cadastroFragment3 = new CadastroFragment3();
    }

    public Bovino getBovino() {
        return bovino;
    }

    public CadastroFragment2 getCadastroFragment2() {
        return cadastroFragment2;
    }

    public CadastroFragment3 getCadastroFragment3() {
        return cadastroFragment3;
    }

    private Toolbar toolbarPadrao;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("fragment", "Activity -  onCreate()" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        toolbarPadrao = (Toolbar)findViewById(R.id.toolbar_cadastro);
        toolbarPadrao.setLogo(R.drawable.ic_toolbar_cadastro);
        toolbarPadrao.setTitle(" Novo Cadastro");
        setSupportActionBar(toolbarPadrao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarPadrao.setNavigationIcon(R.drawable.ic_toolbar_voltar);
        toolbarPadrao.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = fm.beginTransaction();
                String activityName = fm.findFragmentById(R.id.layout_cadastro).getClass().getSimpleName();

                if("CadastroFragment3".equals(activityName)){
                    //Toast.makeText(CadastroActivity.this, "FRAG 1", Toast.LENGTH_SHORT).show();
                    ft.replace(R.id.layout_cadastro, cadastroFragment2 , "FRAG2");
                    ft.commit();
                }else if("CadastroFragment2".equals(activityName)){
                    ft.replace(R.id.layout_cadastro, cadastroFragment1 );
                    ft.commit();

                }else if("CadastroFragment1".equals(activityName)){
                    onBackPressed();
                    onBackPressed();
                }


            }




        });


        if(savedInstanceState == null){
            FragmentTransaction ft = fm.beginTransaction();
            //fa.setIdAtual(1);
            ft.add(R.id.layout_cadastro, cadastroFragment1);
            //ft.addToBackStack(null);
            ft.commit();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.i("fragment", "Activity -  onResume()" );
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("fragment", "Activity -  onPause()" );
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("fragment", "Activity -  onStop()" );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("fragment", "Activity -  onDestroy()" );
    }

    //Menu da Toobar (Configurações e Sobre)

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar_cadastro, menu);
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





//    // Metodo Botao Proximo
//
//    public void OnClickButtonProximo(View view) {
//
//
//        FragmentTransaction ft = fm.beginTransaction();
//        int proximo = fa.getProximo();
//
//        if(proximo != -1){
//            ft.replace(R.id.layout_cadastro, fa.getItem(proximo) );
//            fa.setIdAtual(proximo);
//            ft.addToBackStack(null);
//            ft.commit();
//        }
//
//
//
//
//        String strI = String.valueOf(fa.getCount());
//        Toast.makeText(CadastroActivity.this, strI, Toast.LENGTH_SHORT).show();
//
//    }

    //                Toast.makeText(CadastroActivity.this, getSupportFragmentManager()
//                        .findFragmentById(R.id.layout_cadastro).getClass().getSimpleName(), Toast.LENGTH_SHORT).show();


    //fm.getFragments().get(1).getClass().getSimpleName()


//
//                int anterior = fa.getAnterior();
//
//                if(anterior != -1){
//
//                    ft.replace(R.id.layout_cadastro, fa.getItem(anterior) );
//                    fa.setIdAtual(anterior);
//                    ft.addToBackStack(null);
//                    ft.commit();
//                }
//
//                String strI = String.valueOf(fm.getFragment());
//                Toast.makeText(CadastroActivity.this, strI, Toast.LENGTH_SHORT).show();
//
//                //onBackPressed();

