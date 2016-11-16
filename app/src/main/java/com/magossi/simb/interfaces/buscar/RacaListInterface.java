package com.magossi.simb.interfaces.buscar;

import com.magossi.simb.domain.bovino.Raca;

import java.util.List;

/**
 * Created by RafaelMq on 08/11/2016.
 */
public interface RacaListInterface {

    public void depoisBuscaRaca(List<Raca> racas, String erro);
}
