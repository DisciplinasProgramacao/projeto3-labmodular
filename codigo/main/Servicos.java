public class Servicos{
ServicosEnum tipoServiço;

public Servicos(String tipo){
    String t=tipo;
    t.toUpperCase();
    switch(t){
       case "MANOBRISTA":
       this.tipoServiço=ServicosEnum.MANOBRISTA;
       break;
       case"LAVAGEM":
       this.tipoServiço=ServicosEnum.LAVAGEM;
       break;
       case"POLIMENTO":
       this.tipoServiço=ServicosEnum.POLIMENTO;
    }
}
public Double valorServico(){
    return this.tipoServiço.getValor();
}
public double tempoMin(){
    return this.tipoServiço.getTempoMin();
}
public String toString(){
    return "preço "+this.valorServico()+" tempo minimo "+this.tempoMin();
}
}