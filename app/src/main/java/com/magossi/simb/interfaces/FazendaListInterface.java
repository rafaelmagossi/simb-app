package com.magossi.simb.interfaces;

import com.magossi.simb.domain.bovino.Fazenda;

import java.util.List;

/**
 * Created by RafaelMq on 06/11/2016.
 */
public interface FazendaListInterface {

    public void depoisBuscaFazendas(List<Fazenda> fazendas, String erro);

}
