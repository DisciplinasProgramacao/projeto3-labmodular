package main;
import java.time.LocalDateTime;
import java.time.LocalTime;

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
	private Servicos servico;
	private LocalDateTime data;

	 /**
	 * Cria um novo uso de vaga com serviço.
	 * @param vaga
	 * @param serv
	 */
	public UsoDeVaga(Vaga vaga, Servicos serv){
		this.vaga= vaga;
		this.entrada=LocalTime.now();
		this.saida= null;
		this.valorPago=0;
		this.servico = serv;
	}

	/**
	 * USADO PARA CARGA DE DADOS INICIAL
	 */
	public UsoDeVaga(Vaga vaga, String inicio, String fim, Servicos serv){
		this.vaga = vaga;
		this.entrada = LocalTime.parse(inicio);
		this.saida = LocalTime.parse(fim);
		this.valorPago = this.calcularValor();
		this.status = true;
		this.data = LocalDateTime.now();
		this.servico = serv;
	}

	/**
	 * UTILIZADO PARA COMPLEMENTAR O CONSTRUTOR DA CARGA DE DADOS
	 */
	public double calcularValor(){
		Duration duracao=Duration.between(this.entrada,this.saida);
		Double horas=(double)duracao.toMinutes()/60;
		double tempoDeUso = horas;

		Double valorPago = 1d;

		if(tempoDeUso/FRACAO_USO > VALOR_MAXIMO){
			valorPago = VALOR_MAXIMO;
		}else{
			valorPago = tempoDeUso/FRACAO_USO * VALOR_FRACAO;
		}
		return valorPago;
	}

	public void fecharUso(){
		this.status = true;
	}

	 /**
	 * Remove o veículo da vaga.
	 */
	public double sair() {
		Duration duracao = Duration.between(this.entrada, LocalTime.now());
		double tempoDeUso = duracao.toMinutes();

		if(tempoDeUso >= this.servico.tempoMin()){
			this.vaga.sair();
			this.data = LocalDateTime.now();
			this.saida = LocalTime.now();

			if(tempoDeUso/FRACAO_USO > VALOR_MAXIMO){
				this.valorPago = VALOR_MAXIMO;
				return VALOR_MAXIMO;
			}else{
				this.valorPago = tempoDeUso * VALOR_FRACAO + servico.valorServico();
				return valorPago;
			}
		}else{ return -1d; }
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

	public double getValorPago(){
		return this.valorPago;
	}

	public Servicos getServicos(){
		return this.servico;
	}
}
