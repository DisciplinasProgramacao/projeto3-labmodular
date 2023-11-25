package main;

import java.time.LocalDate;

import interfaces.ICategoriaCliente;

public class Horista implements ICategoriaCliente{
    private Cliente cliente;
    
    public Horista(Cliente obj){
        this.cliente=obj;
    }

    @Override
    public double calcularPagamento(){
        return this.cliente.arrecadadoTotal();
    }
}
