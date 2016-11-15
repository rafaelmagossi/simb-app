package com.magossi.simb.domain.bovino;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Ecc implements Serializable {


    private Long idECC;
    private Integer escore;
    private Date dataInclusao;
    private Boolean status;

    public Ecc(){
        status = true;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Long getIdECC() {
        return idECC;
    }

    public void setIdECC(Long idECC) {
        this.idECC = idECC;
    }

    public int getEscore() {
        return escore;
    }

    public void setEscore(int escore) {
        this.escore = escore;
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
