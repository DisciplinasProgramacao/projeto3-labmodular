package main.Teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import main.Vaga;
import main.Veiculo;

public class VeiculoTeste {
    private Vaga v;
    private Veiculo ve;

    @Before
    public void create(){
        v =  new Vaga(0, 15, "Teste");
        ve = new Veiculo("abcdefg");
    }

    @Test
    public void estacionarTest(){
        boolean resp = ve.estacionar(v);

        Assert.assertTrue(resp);
    }

    @Test
    public void sairTest(){
        boolean resp = ve.sair(v);

        Assert.assertTrue(resp);
    }

    @Test
    public void totalArrecadadoTest(){
        double resp = ve.totalArrecadado();

        Assert.assertTrue(resp >= 0);
    }

    @Test
    public void totalDeUsosTest(){
        double resp = ve.totalDeUsos(v);

        Assert.assertTrue(resp >= 0);
    }
}
