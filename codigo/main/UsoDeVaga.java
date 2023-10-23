import java.time.LocalDateTime;
import java.time.Duration;
 public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalDateTime entrada;
	private LocalDateTime saida;
	private double valorPago;
	private Servicos serviço;

	public UsoDeVaga(Vaga vaga) {
		this.vaga= vaga;
		this.entrada=LocalDateTime.now();
		this.saida= null;
		this.valorPago=0;
		if(this.vaga.disponivel()==true){
          vaga.estacionar();
		}
	}
	public double sair() {
	  this.saida=LocalDateTime.now();
	  Duration duracao=Duration.between(this.entrada,saida);
	  long hora=duracao.toHours();
	  long minutos=duracao.toMinutes()/60;
	  double tempoDeUso=hora+minutos;
	  return tempoDeUso/FRACAO_USO;
	}
	public void fornecerServiço(String tipo){
      this.serviço= new Servicos(tipo);
	}

	public double valorPago() {
		valorPago=this.sair()*VALOR_FRACAO;
		if(valorPago>VALOR_MAXIMO){
           valorPago=VALOR_MAXIMO;
		}
		return valorPago+serviço.valorServico();
	}

}
