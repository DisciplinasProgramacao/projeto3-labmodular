/*
 * Classe criada 04/10/2023
 * 
 * Projeto3 regra aplicada por Matheus Vinicius
 */

public class Cliente {

	private String nome;
	private String id;
	private Veiculo[] veiculos = new Veiculo[100];


	/*
	 * Contrutores da classe Cliente
	 * - O primeiro define um novo cliente com nome e ID
	 * - O segundo define um novo cliente apenas com o ID, deixando o
	 * nome como anônimo
	 */
	public Cliente(String nome, String id) {
		this.nome = nome;
		this.id = id;
	}

	public Cliente(String id) {
		this.nome = "anônimo";
		this.id = id;
	}

	/*
	 * Adiciona um novo veiculo ao vetor de veiculos
	 */
	public void addVeiculo(Veiculo veiculo) {
		this.veiculos[veiculos.length + 1] = veiculo;
	}

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
		return 0;
	}

	/*
	 * Retorna a soma dos valores das vagas utilizadas por um veículo
	 * do cliente
	 */
	public double arrecadadoPorVeiculo(String placa) {
		return 0.00;
	}

	/*
	 * Retorna a soma dos valores das vagas utilizadas pelos veículos
	 * do cliente
	 */
	public double arrecadadoTotal() {
		return 0.00;
	}


	public double arrecadadoNoMes(int mes) {
		
	}

}
