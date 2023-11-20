package main;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.xml.crypto.Data;

import java.time.Duration;
 public class UsoDeVaga {

	private static final double FRACAO_USO = 0.25;
	private static final double VALOR_FRACAO = 4.0;
	private static final double VALOR_MAXIMO = 50.0;
	private Vaga vaga;
	private LocalTime entrada;
	private LocalTime saida;
	private double valorPago;
	private boolean status = false;
	private Servicos serviço;
	private LocalDateTime data;

	public UsoDeVaga(Vaga vaga) {
		this.vaga= vaga;
		this.entrada=LocalTime.now();
		this.saida= null;
		this.valorPago=0;
		data=LocalDateTime.now();
	}

	public UsoDeVaga(Vaga vaga, Servicos serv){
		this.vaga= vaga;
		this.entrada=LocalTime.now();
		this.saida= null;
		this.valorPago=0;
		this.serviço = serv;
	}

	public double sair() {
	  this.saida=LocalTime.now();
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
		return this.data.getMonthValue();
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
	public LocalTime getHoraEntrada(){
		return entrada;
	}
	public LocalTime getHoraSaida(){
		return saida;
	}
}
