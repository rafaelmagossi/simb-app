package com.magossi.simb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.bovino.Peso;
import com.magossi.simb.domain.matriz.DiagnosticoGestacao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 23/10/2016.
 */
public class PesoAdapter extends BaseAdapter {

    public final Context context;
    public final List<Peso> pesos;

    public PesoAdapter(Context context, List<Peso> pesos) {

        this.context = context;
        this.pesos = pesos;
    }

    @Override
    public int getCount() {

        return pesos != null ? pesos.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return pesos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_peso, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView textView_dataPesagem = (TextView) view.findViewById(R.id.textview_item_peso_data);
        TextView textView_valorPeso = (TextView) view.findViewById(R.id.textview_item_peso_valor);



        // Atualiza os valores das views
        Peso peso = pesos.get(position);


        try {
            textView_dataPesagem.setText(formataDateToString(peso.getDataPesagem()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        textView_valorPeso.setText(peso.getPeso()+" Kg");

        // Retorna a view deste inseminacao
        return view;
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
}
