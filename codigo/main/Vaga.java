package main;


public class Vaga {

	private String id;
	private String EstacionamentoNome;
	private int fila;
	private int numero;
	private boolean disponivel = true;

	public Vaga(int fila, int numero, String nomeEsta) {
		this.EstacionamentoNome = nomeEsta;
		this.fila = fila;
		this.numero = numero;
	}

	/**
	 * Atualiza o status da vaga para false quando o veículo é estacionado.
	 */
	public void estacionar() {
		this.disponivel = false;
	}

	/**
	 * Atualiza o status da vaga para true quando o veículo é estacionado.
	 */
	public void sair() {
		this.disponivel = true;
	}

	public boolean disponivel() {
		return this.disponivel;
	}

	public String getNomeEstacionamento(){
		return this.EstacionamentoNome;
	}

	public String getDadosVaga(){
		return this.fila+""+this.numero;
	}

}