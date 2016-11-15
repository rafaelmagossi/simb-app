package com.magossi.simb.domain.bovino;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;



/**
 * Created by RafaelMq on 26/09/2016.
 */
public class Bovino implements Serializable{
    private static final long serialVerionUID = 6601006766832473959L;


    private Long idBovino;
    private String tag;
    private String nomeBovino;
    private Boolean genero;
    private String pai;
    private String mae;
    private Date dataNascimento;
    private Raca raca;
    private Pelagem pelagem;
    private Fazenda fazenda;
    private Proprietario proprietario;
    private List<Ecc> ecc;
    private List<Peso> peso;
    private Boolean status;
    private Date dataInclusao;
    private String urlFoto;



    public Bovino(){
        status = true;
    }

    public Date getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(Date dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public Long getIdBovino() {
        return idBovino;
    }

    public void setIdBovino(Long idBovino) {
        this.idBovino = idBovino;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getNomeBovino() {
        return nomeBovino;
    }

    public void setNomeBovino(String nomeBovino) {
        this.nomeBovino = nomeBovino;
    }

    public Boolean getGenero() {
        return genero;
    }

    public void setGenero(Boolean genero) {
        this.genero = genero;
    }

    public String getPai() {
        return pai;
    }

    public void setPai(String pai) {
        this.pai = pai;
    }

    public String getMae() {
        return mae;
    }

    public void setMae(String mae) {
        this.mae = mae;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Raca getRaca() {
        return raca;
    }

    public void setRaca(Raca raca) {
        this.raca = raca;
    }

    public Pelagem getPelagem() {
        return pelagem;
    }

    public void setPelagem(Pelagem pelagem) {
        this.pelagem = pelagem;
    }

    public Fazenda getFazenda() {
        return fazenda;
    }

    public void setFazenda(Fazenda fazenda) {
        this.fazenda = fazenda;
    }

    public Proprietario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Proprietario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Ecc> getEcc() {
        return ecc;
    }

    public void setEcc(List<Ecc> ecc) {
        this.ecc = ecc;
    }

    public List<Peso> getPeso() {
        return peso;
    }

    public void setPeso(List<Peso> peso) {
        this.peso = peso;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getUrlFoto() {
        return urlFoto;
    }

    public void setUrlFoto(String urlFoto) {
        this.urlFoto = urlFoto;
    }

    @Override
    public String toString() {
        return "Bovino{" + "nome='" + nomeBovino + '\'' + '}';
    }
}
