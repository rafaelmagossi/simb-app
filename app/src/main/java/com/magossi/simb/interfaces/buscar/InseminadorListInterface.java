package com.magossi.simb.interfaces.buscar;

import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.matriz.Inseminador;

import java.util.List;

/**
 * Created by RafaelMq on 13/11/2016.
 */
public interface InseminadorListInterface {

    public void depoisBuscaInseminadores(List<Inseminador> inseminadores, String erro);
}
