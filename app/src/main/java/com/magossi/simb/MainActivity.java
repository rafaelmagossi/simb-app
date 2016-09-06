package com.magossi.simb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbarPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbarPadrao = (Toolbar)findViewById(R.id.tb_padrao);
        toolbarPadrao.setLogo(R.mipmap.ic_logo_vaca);
        toolbarPadrao.setTitle(getString(R.string.app_name));
        toolbarPadrao.setSubtitle(getString(R.string.app_subname));
        setSupportActionBar(toolbarPadrao);

        //teste


    }
}
