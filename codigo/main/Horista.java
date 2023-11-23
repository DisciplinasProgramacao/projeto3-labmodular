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

    public double valorMedioHoristas(Estacionamento estac){
        int qtdHoristas = estac.getClientes().stream()
                                             .filter(x -> x.getCategoria() == this.cliente.getCategoria())
                                             .count();
        double valor = this.cliente.arrecadadoNoMes(LocalDate.now().getMonthValue());
        double media = (valor/qtdHoristas);
        return media;
    }
}
