package com.magossi.simb.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.magossi.simb.R;
import com.magossi.simb.domain.bovino.Ecc;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by RafaelMq on 14/11/2016.
 */
public class EccAdapter extends BaseAdapter {


    public final Context context;
    public final List<Ecc> eccs;

    public EccAdapter(Context context, List<Ecc> eccs) {

        this.context = context;
        this.eccs = eccs;
    }

    @Override
    public int getCount() {

        return eccs != null ? eccs.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return eccs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Infla a view
        View view = LayoutInflater.from(context).inflate(R.layout.item_ecc, parent, false);
        // Faz findViewById das views que precisa atualizar
        TextView textview_dataAvaliacao = (TextView) view.findViewById(R.id.textview_item_ecc_data);
        TextView textview_valorEcc = (TextView) view.findViewById(R.id.textview_item_ecc_valor);


        // Atualiza os valores das views
        Ecc ecc = eccs.get(position);


        try {
            textview_dataAvaliacao.setText(formataDateToString(ecc.getDataInclusao()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        textview_valorEcc.setText(ecc.getEscore() + "");

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
