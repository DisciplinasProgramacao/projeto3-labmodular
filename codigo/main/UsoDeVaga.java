package main;
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
	private boolean status = false;
	private Servicos serviço;

	public UsoDeVaga(Vaga vaga) {
		this.vaga= vaga;
		this.entrada=LocalDateTime.now();
		this.saida= null;
		this.valorPago=0;
	}

	public UsoDeVaga(Vaga vaga, Servicos serv){
		this.vaga= vaga;
		this.entrada=LocalDateTime.now();
		this.saida= null;
		this.valorPago=0;
		this.serviço = serv;
	}

	public double sair() {
	  this.saida=LocalDateTime.now();
	  Duration duracao=Duration.between(this.entrada,saida);
	  long hora=duracao.toHours();
	  long minutos=duracao.toMinutes()/60;
	  double tempoDeUso=hora+minutos;
	  this.status = true;
	  if(tempoDeUso/FRACAO_USO > VALOR_MAXIMO){
		return VALOR_MAXIMO;
	  }else{
		return tempoDeUso/FRACAO_USO;
	  }
	}

	public double valorPago() {
		valorPago=this.sair()*VALOR_FRACAO;
		if(valorPago>VALOR_MAXIMO){
           valorPago=VALOR_MAXIMO;
		}
		return valorPago+ serviço.valorServico();
	}

	public int getMesEntrada(){
		return this.entrada.getMonthValue();
	}

	public Vaga getVaga(){
		return this.vaga;
	}

	public String getData(){
		return this.entrada.toString();
	}

	public boolean getStatus(){
		return this.status;
	}
}
