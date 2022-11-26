package com.desafioPrimefaces.bean;

import com.desafioPrimefaces.entity.Carro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CarroBean {
    private Carro carro;
    private List<Carro> carros = new ArrayList<>();

    public void adicionar(){
        carros.add(carro);
        carro = new Carro();
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public List<Carro> getCarros() {
        return carros;
    }

    public void setCarros(List<Carro> carros) {
        this.carros = carros;
    }
}
