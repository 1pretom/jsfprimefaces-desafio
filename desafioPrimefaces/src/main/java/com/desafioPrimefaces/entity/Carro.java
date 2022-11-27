package com.desafioPrimefaces.entity;

import java.util.Date;
import java.util.Objects;

public class Carro {
    private String modelo, fabricante, cor;
    private Date ano;
    private Integer id;

    public String getModelo() {
        return modelo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Carro carro = (Carro) o;
        return id.equals(carro.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
