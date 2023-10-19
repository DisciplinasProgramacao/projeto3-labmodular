 
import org.junit.Test;

public class TesteUsoDeVaga {
    Vaga teste= new Vaga('B', 01);
    Vaga error= new Vaga('A', 02);
    UsoDeVaga A = new UsoDeVaga(teste);
    UsoDeVaga erro= new UsoDeVaga(error); 
    @Test   
    public void teste1sair(){
    A.sair();
    }
    @Test
    public void testeSair2(){
        teste.sair();
        erro.sair();
    }

    @Test
    public void testeValorPago(){
      A.valorPago();
    }

    @Test
    public void testeValorPago2(){
        if(erro.valorPago()<50){
        }
    }
    }

