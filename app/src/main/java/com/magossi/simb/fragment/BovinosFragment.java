package com.magossi.simb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import com.magossi.simb.R;
import com.magossi.simb.activity.BovinoActivity;
import com.magossi.simb.adapter.BovinoAdapter;
import com.magossi.simb.domain.Bovino;
import com.magossi.simb.domain.BovinoService;

public class BovinosFragment extends Fragment {
    protected RecyclerView recyclerView;
    private List<Bovino> bovinos;
    private LinearLayoutManager mLayoutManager;
    private String tipo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_busca_bovinos, container, false);
        Log.i("LOG", "onCreateView");


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
                BovinoAdapter adapter = (BovinoAdapter) recyclerView.getAdapter();

                if (bovinos.size() == llm.findLastCompletelyVisibleItemPosition() + 1) {
                    //aqui carrega a quantidade
                    List<Bovino> listAux = BovinoService.getBovinos(getContext(), bovinos.size());

                    for (int i = 0; i < listAux.size(); i++) {
                        adapter.addListItem(listAux.get(i), bovinos.size());


                    }

                }
            }
        });

        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        taskbovinos();
    }

    private void taskbovinos() {
        // Busca os bovinos
        this.bovinos = BovinoService.getBovinos(getContext(), 0);
        // Atualiza a lista
        recyclerView.setAdapter(new BovinoAdapter(getContext(), bovinos, onClickBovino()));
    }

    private BovinoAdapter.BovinoOnClickListener onClickBovino() {
        return new BovinoAdapter.BovinoOnClickListener() {
            @Override
            public void onClickBovino(View view, int idx) {
                Bovino b = bovinos.get(idx);
               // Toast.makeText(getContext(), "Bovino: " + c.nome, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), BovinoActivity.class);
                intent.putExtra("bovino", b);
                startActivity(intent);
            }
        };
    }


}
