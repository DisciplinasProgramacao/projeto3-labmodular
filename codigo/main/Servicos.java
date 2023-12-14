package main;

import enuns.ServicosEnum;

public class Servicos{
    ServicosEnum tipoServiço;

    public Servicos(String tipo){
        String t = tipo;
        t.toUpperCase();

        switch(t){
            case "MANOBRISTA":
                this.tipoServiço = ServicosEnum.MANOBRISTA;
                break;
            case"LAVAGEM":
                this.tipoServiço = ServicosEnum.LAVAGEM;
                break;
            case"POLIMENTO":
            this.tipoServiço = ServicosEnum.POLIMENTO;
                break;
            case"NENHUM":
                this.tipoServiço = ServicosEnum.NENHUM;
                break;
        }
    }
    
    public Double valorServico(){
        return this.tipoServiço.getValor();
    }

    public double tempoMin(){
        return this.tipoServiço.getTempoMin();
    }

    public String nome(){
        return this.tipoServiço.getNome();
    }

    public String toString(){
        return nome();
    }
}