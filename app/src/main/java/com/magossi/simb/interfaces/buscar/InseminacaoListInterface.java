package com.magossi.simb.interfaces.buscar;

import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.matriz.Inseminacao;

import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public interface InseminacaoListInterface {

    public void depoisBuscaInseminacoes(List<Inseminacao> inseminacaos, String erro);
}
