package com.magossi.simb.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.magossi.simb.R;
import com.magossi.simb.activity.BovinoActivity;
import com.magossi.simb.adapter.TarefaAdapter;
import com.magossi.simb.domain.tarefas.Tarefa;
import com.magossi.simb.interfaces.buscar.TarefaListInterface;
import com.magossi.simb.task.buscar.TaskBuscaTarefaList;

import java.util.List;

public class TarefasFragment extends Fragment implements TarefaListInterface {


    protected RecyclerView recyclerView;
    private List<Tarefa> tarefas;
    private LinearLayoutManager mLayoutManager;
    private String tipo;
    private TaskBuscaTarefaList taskBuscaTarefaList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.tipo = getArguments().getString("tipo");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout_tarefas, container, false);
        Log.i("LOG", "onCreateView");


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView_tarefas);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
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



        taskBuscaTarefaList = new TaskBuscaTarefaList(this.getContext(), this);
        taskBuscaTarefaList.execute("/tarefa", "");
    }


    @Override
    public void depoisBuscaTarefas(List<Tarefa> tarefas, String erro) {

        this.tarefas = tarefas;
        recyclerView.setAdapter(new TarefaAdapter(getContext(), this.tarefas, onClickTarefa()));

    }

    private TarefaAdapter.TarefaOnClickListener onClickTarefa() {
        return new TarefaAdapter.TarefaOnClickListener() {
            @Override
            public void onClickTarefa(View view, int idx) {
                Tarefa t = tarefas.get(idx);
                //Toast.makeText(getContext(), "Bovino: " + b.getNomeBovino(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getContext(), BovinoActivity.class);
                intent.putExtra("tarefa", t);
                startActivity(intent);
            }
        };
    }


}
