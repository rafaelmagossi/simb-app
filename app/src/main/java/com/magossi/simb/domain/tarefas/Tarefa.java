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
    private String imei;
    private Date dataInclusao = new Date();
    private boolean statusDaTarefa;
    private Date dataConclusao;
    private boolean status;


    public Tarefa(){
        status = true;
        statusDaTarefa = false;
        imei = "354983059791193";
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

    public TipoTarefaEnum getTipoTarefa() {
        return tipoTarefa;
    }

    public void setTipoTarefa(TipoTarefaEnum tipoTarefa) {
        this.tipoTarefa = tipoTarefa;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public boolean isStatusDaTarefa() {
        return statusDaTarefa;
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
