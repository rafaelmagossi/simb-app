package com.magossi.simb.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Pelagem implements Serializable {


    private Long idPelagem;
    private String nomePelagem;
    private Date dataInclusao = new Date();
    private Boolean status;


    public Long getIdPelagem() {
        return idPelagem;
    }

    public String getNomePelagem() {
        return nomePelagem;
    }

    public void setIdPelagem(Long idPelagem) {
        this.idPelagem = idPelagem;
    }

    public void setNomePelagem(String nomePelagem) {
        this.nomePelagem = nomePelagem;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
