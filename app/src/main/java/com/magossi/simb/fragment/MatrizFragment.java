package com.magossi.simb.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.magossi.simb.R;
import com.magossi.simb.adapter.InseminacaoAdapter;
import com.magossi.simb.domain.Inseminacao;

import java.util.List;

/**
 * Created by RafaelMq on 17/10/2016.
 */
public class MatrizFragment extends Fragment implements AdapterView.OnItemClickListener {

    private ListView listView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_busca_matriz, container, false);

        listView = (ListView) view.findViewById(R.id.listview);
        List<Inseminacao> inseminacaos = Inseminacao.getInseminacoes();
        listView.setAdapter(new InseminacaoAdapter(this.getContext(), inseminacaos));
        listView.setOnItemClickListener(this);


        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int idx, long id) {
        Inseminacao i = (Inseminacao) parent.getAdapter().getItem(idx);
        Toast.makeText(this.getContext(), "Inseminacao: " + i.id, Toast.LENGTH_SHORT).show();
    }
}
