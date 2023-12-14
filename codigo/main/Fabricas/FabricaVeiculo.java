package main.Fabricas;

import main.IFabrica;
import main.Veiculo;

public class FabricaVeiculo implements IFabrica<Veiculo> {

    @Override
    public Veiculo create() {
        return new Veiculo();
    }
    
}
