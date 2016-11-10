package com.magossi.simb.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by RafaelMq on 03/11/2016.
 */


public class Ecc implements Serializable {


    private Long idECC;
    private Integer escore;
    private Date dataInclusao = new Date();
    private Boolean status;


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
