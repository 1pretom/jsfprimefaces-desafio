package com.desafioPrimefaces.bean;

import com.desafioPrimefaces.dao.CarroDAO;
import com.desafioPrimefaces.entity.Carro;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CarroBean {
    private Carro carro;
    private List<Carro> carros = new ArrayList<>();


    public void adicionar() throws ClassNotFoundException {
        new CarroDAO().salvar(carro);
        carro = new Carro();
    }
    public void listar() throws SQLException, ClassNotFoundException {
        new CarroDAO().buscar();

    }
    public void editar (Carro c){
        carro = c;
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
