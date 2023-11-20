package main;

import interfaces.ICategoriaCliente;

public class Mensalista implements ICategoriaCliente{
    Cliente cli;
    public Mensalista(Cliente c){
        this.cli=c;
    }
    public double calcularPagamento(){
        return 500.00;
    }
}
