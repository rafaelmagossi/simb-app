package com.magossi.simb.interfaces.buscar;


import com.magossi.simb.domain.tarefas.Tarefa;

import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public interface TarefaListInterface {

    public void depoisBuscaTarefas(List<Tarefa> tarefas, String erro);
}
