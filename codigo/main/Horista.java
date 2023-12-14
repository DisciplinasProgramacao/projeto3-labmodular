package main;

import java.util.ArrayList;
import java.util.List;

import interfaces.ICategoriaCliente;

public class Horista implements ICategoriaCliente{
    private Cliente cliente;
    
    public Horista(Cliente obj){
        this.cliente=obj;
    }

    /**
	 * Retorna o valor pago pelo Horista.
	 */
    @Override
    public double calcularPagamento(){
        System.out.print("Horista\n");
        double total = 0d;

        for(Veiculo c: this.cliente.getVeiculos()){
            if(c.getEstacionado() == true){
                for(UsoDeVaga u: c.getUsos()){
                    if(u.getStatus() == false){
                        u.sair();
                        total = u.getValorPago();
                    }
                }
            }
        }

        return total;
    }
}
