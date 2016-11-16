package com.magossi.simb.domain.tarefas;


import com.magossi.simb.domain.bovino.Bovino;
import com.magossi.simb.domain.matriz.Inseminacao;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 16/11/2016.
 */



public class TarefaInseminacao implements Serializable {


    private Long idTarefaInseminacao;
    private Bovino bovinoMatriz;
    private Inseminacao inseminacao;
    private boolean statusDaTarefa;
    private Date daraInclusao = new Date();
    private Date dataConclusao;
    private boolean status;


    public TarefaInseminacao(){
        status = true;
    }

    public Long getIdTarefaInseminacao() {
        return idTarefaInseminacao;
    }

    public void setIdTarefaInseminacao(Long idTarefaInseminacao) {
        this.idTarefaInseminacao = idTarefaInseminacao;
    }

    public Bovino getBovinoMatriz() {
        return bovinoMatriz;
    }

    public void setBovinoMatriz(Bovino bovinoMatriz) {
        this.bovinoMatriz = bovinoMatriz;
    }

    public Inseminacao getInseminacao() {
        return inseminacao;
    }

    public void setInseminacao(Inseminacao inseminacao) {
        this.inseminacao = inseminacao;
    }

    public Date getDaraInclusao() {
        return daraInclusao;
    }

    public void setDaraInclusao(Date daraInclusao) {
        this.daraInclusao = daraInclusao;
    }

    public Date getDataConclusao() {
        return dataConclusao;
    }

    public void setDataConclusao(Date dataConclusao) {
        this.dataConclusao = dataConclusao;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatusDaTarefa() {
        return statusDaTarefa;
    }

    public void setStatusDaTarefa(boolean statusDaTarefa) {
        this.statusDaTarefa = statusDaTarefa;
    }
}
