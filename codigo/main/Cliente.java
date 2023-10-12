/*
 * Classe criada 04/10/2023
 * 
 * Projeto3 regra aplicada por Matheus Vinicius
 */

import enuns.IdentificacaoCliente;
import interfaces.IArrecadavel;
package main;

public class Cliente implements IArrecadavel {

	private String nome;
	private String id;
	private IdentificacaoCliente identificado;
	private Veiculo[] veiculos = new Veiculo[100];

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
		this.veiculos[veiculos.length + 1] = veiculo;
	}

	public boolean validarVeiculo(Veiculo v){
		return v != null;
	}

	public boolean possuiVeiculo(String placa) {
		boolean status = false;
		for(Veiculo veiculo : veiculos){
			if(validarVeiculo(veiculo) && veiculo.getPlaca() == placa){ status = true; }// getPlaca vai ser criado no veiculo
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
			if(validarVeiculo(v)){
				total += v.totalDeUsos(); 
			}
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
			if(validarVeiculo(v) && v.getPlaca() == placa){ total = v.totalArrecadado(); }
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
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'arrecadadoNoMes'");
	}

}
