package com.magossi.simb.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.magossi.simb.MainActivity;
import com.magossi.simb.R;
import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.interfaces.buscar.BovinoBuscarObjInterface;
import com.magossi.simb.task.buscar.TaskBuscaBovinoObj;

import java.io.UnsupportedEncodingException;

/**
 * Created by RafaelMq on 07/09/2016.
 */
public class ScannerActivity extends AppCompatActivity implements BovinoBuscarObjInterface {

    private Toolbar toolbarPadrao;
    private ImageView imageView;
    private NfcAdapter adaptadorNFC;
    private String tag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scanner);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);

        toolbarPadrao = (Toolbar)findViewById(R.id.toolbar_scanner);
        toolbarPadrao.setLogo(R.drawable.ic_toolbar_scanner);
        toolbarPadrao.setTitle(" Scanner");
        setSupportActionBar(toolbarPadrao);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbarPadrao.setNavigationIcon(R.drawable.ic_toolbar_voltar);
        imageView = (ImageView) findViewById(R.id.imv_scanner);
        imageView.setImageResource(R.drawable.scanner_activity);

        adaptadorNFC = NfcAdapter.getDefaultAdapter(this);


    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        if(intent.hasExtra(NfcAdapter.EXTRA_TAG)){


                Parcelable[] parcelables = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);

                if(parcelables != null){
                    lerMensagemDaTag((NdefMessage)parcelables[0]);

                }else{
                    alert("Nenhuma Mensagem NDEF encontrada");
                }


        }

    }

    private void lerMensagemDaTag(NdefMessage ndefMessage) {
        tag = null;
        NdefRecord[] ndefRecords = ndefMessage.getRecords();
        TaskBuscaBovinoObj taskBuscaBovinoObj = new TaskBuscaBovinoObj(this,this);


        if(ndefRecords != null &&ndefRecords.length>0){
            NdefRecord ndefRecord = ndefRecords[0];
            String conteudoDaTag = getTextFromNdefRecord(ndefRecord);
            tag = conteudoDaTag;
            Log.i("TAG: ",tag);
            //txtTagNfc.setText(conteudoDaTag);
            taskBuscaBovinoObj.execute("/tag", conteudoDaTag);

        }else{
            alert("Nenhuma  NDEF Records encontrada");
        }
    }

    public String getTextFromNdefRecord(NdefRecord ndefRecord)
    {
        String tagContent = null;
        try {
            byte[] payload = ndefRecord.getPayload();
            String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
            int languageSize = payload[0] & 0063;
            tagContent = new String(payload, languageSize + 1,
                    payload.length - languageSize - 1, textEncoding);
        } catch (UnsupportedEncodingException e) {
            Log.e("getTextFromNdefRecord", e.getMessage(), e);
        }
        return tagContent;
    }

    @Override
    protected void onResume() {
        super.onResume();

        enableForegroundDispatchSystem();
    }

    @Override
    protected void onPause() {
        super.onPause();

        disableForegroundDispatchSystem();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toobar_scanner, menu);
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

            case R.id.menu_config:
                break;

            case R.id.menu_sobre:
                break;


        }
        return super.onOptionsItemSelected(item);
    }

    private void enableForegroundDispatchSystem(){

        Intent intent = new Intent(this, ScannerActivity.class).addFlags(Intent.FLAG_RECEIVER_REPLACE_PENDING);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent, 0);
        IntentFilter[] intentFilters = new IntentFilter[]{};
        adaptadorNFC.enableForegroundDispatch(this, pendingIntent, intentFilters, null);


    }

    private void disableForegroundDispatchSystem(){

    }

    private void alert(String s) {

        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    @Override
    public void depoisBuscaBovino(Bovino bovino, String erro) {


        if (bovino != null && erro == null ){
            //Log.e("http","EntrouOK");
            Intent intent = new Intent(this, BovinoActivity.class);
            intent.putExtra("bovino", bovino);
            startActivity(intent);

        }else if("404".equals(erro) && bovino == null){
            Log.e("http","EntrouErro");
            Intent intent = new Intent(this, CadastroActivity.class);
            Bundle params = new Bundle();
            params.putString("tagNFC", tag);
            intent.putExtras(params);
            startActivity(intent);
        }else{

        }

    }
}
