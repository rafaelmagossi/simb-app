package com.magossi.simb.domain.tarefas;


import com.magossi.simb.domain.bovino.Bovino;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 16/11/2016.
 */


public class Tarefa implements Serializable {


    private Long idTarefa;
    private Bovino bovinoMatriz;
    private TipoTarefaEnum tipoTarefa;
    private String identificacaoSmartfone;
    private Date daraInclusao = new Date();
    private boolean statusDaTarefa;
    private Date dataConclusao;
    private boolean status;


    public Tarefa(){
        status = true;
        statusDaTarefa = false;
    }

    public Long getIdTarefa() {
        return idTarefa;
    }

    public void setIdTarefa(Long idTarefa) {
        this.idTarefa = idTarefa;
    }

    public Bovino getBovinoMatriz() {
        return bovinoMatriz;
    }

    public void setBovinoMatriz(Bovino bovinoMatriz) {
        this.bovinoMatriz = bovinoMatriz;
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

    public TipoTarefaEnum getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(TipoTarefaEnum tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
    }

    public String getIdentificacaoSmartfone() {
        return identificacaoSmartfone;
    }

    public void setIdentificacaoSmartfone(String identificacaoSmartfone) {
        this.identificacaoSmartfone = identificacaoSmartfone;
    }
}
