package main;

/*
 * Classe criada 04/10/2023
 * 
 * Projeto3 regra aplicada por Matheus Vinicius
 */

import java.util.ArrayList;

import enuns.IdentificacaoCliente;
import interfaces.IArrecadavel;

public class Cliente implements IArrecadavel {

	private String nome;
	private String id;
	private IdentificacaoCliente identificado;
	private ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

	/*
	 * Contrutores da classe Cliente
	 * - O primeiro define um novo cliente com nome e ID
	 * - O segundo define um novo cliente apenas com o ID, deixando o
	 * nome como anônimo
	 */
	public Cliente(String nome, String id) {
		this.identificado = IdentificacaoCliente.IDENTIFICADO;
		this.nome = nome;
		this.id = id;
	}

	public Cliente(String id) {
		this.identificado = IdentificacaoCliente.NAO_IDENTIFICADO;
		this.nome = "anônimo";
		this.id = id;
	}

	/*
	 * Adiciona um novo veiculo ao vetor de veiculos
	 */
	public void addVeiculo(Veiculo veiculo) {
		if(!this.possuiVeiculo(veiculo.getPlaca())){
			veiculos.add(veiculo);
		}
	}

	/*
	 * Retorna se o cliente possui veiculo se baseando na placa
	 */
	public boolean possuiVeiculo(String placa) {
		boolean status = false;
		for(Veiculo veiculo : veiculos){
			if(veiculo.getPlaca() == placa){ status = true; }// getPlaca vai ser criado no veiculo
		}
		return status;
	}

	/*
	 * Percorre todo o vetor de veículos do cliente e faz a soma
	 * da quantidade de vagas que os veículos utilizaram
	 */
	public int totalDeUsos() {
		int total = 0;
		//Percorrendo o vetor de Veiculos do cliente
		for(Veiculo v : veiculos){ 
			total += v.getUsosCount();
		}
		return total;
	}

	/*
	 * Retorna a soma dos valores das vagas utilizadas por um veículo
	 * do cliente
	 */
	public double arrecadadoPorVeiculo(String placa) {
		double total = 0.0;
		for(Veiculo v : veiculos){ 
			if(v.getPlaca() == placa){ total = v.totalArrecadado(); }
		}
		return total;
	}

	/*
	 * Retorna a soma dos valores das vagas utilizadas pelos veículos
	 * do cliente
	 */
	@Override
	public double arrecadadoTotal() {
		double total = 0.0;
		for(Veiculo v : veiculos){ 
			total += v.totalArrecadado(); 
		}
		return total;
	}

	@Override
	public double arrecadadoNoMes(int mes) {
		double total = 0.0;
		for(Veiculo v : veiculos){ 
			for(UsoDeVaga u : v.getUsos()){
				if(u.getMesEntrada() == mes){ total += u.valorPago();  }
			}
		}
		return total;
	}

	//RELATORIOS
	public String usoDeEstacionamento(String estacionamento){
		StringBuilder b = new StringBuilder();

		for(Veiculo v : veiculos){ 
			for(UsoDeVaga u : v.getUsos()){
				if(v.getUsosCount() > 0 && u.getVaga().getNomeEstacionamento() == estacionamento){
					b.append(u.getVaga().getNomeEstacionamento() + " - " + u.getData());
				}
			}
		}
		return b.toString();
	}

	/*
	 * Retorna todos os veiculos do cliente
	 * 
	 */
	public int getVeiculosCount(){
		return this.veiculos.size();
	}

	public String imprimirVeiculos(){
		StringBuilder sb = new StringBuilder();
		int cont = 0;
		for (Veiculo veiculo : veiculos) {
			sb.append(cont + " - " + veiculo.getPlaca() + "\n");
			cont++;
		}
		return sb.toString();
	}

	public Veiculo getVeiculo(String placa){
		Veiculo ve = null;
		for(Veiculo v: veiculos){
			if(v.getPlaca() == placa){ ve = v; }
		}
		return ve;
	}

	public Veiculo getVeiculo(int posicao){
		return veiculos.get(posicao);
	}

	@Override
	public String toString(){
		return this.nome + " - " + this.id; 
	}
}
