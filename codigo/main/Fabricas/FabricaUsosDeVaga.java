package main.Fabricas;

import main.IFabrica;
import main.UsoDeVaga;
import main.Vaga;

public class FabricaUsosDeVaga implements IFabrica<UsoDeVaga> {

    private Vaga vaga;

    @Override
    public UsoDeVaga create() {
        return new UsoDeVaga(vaga);
    }
    
}
