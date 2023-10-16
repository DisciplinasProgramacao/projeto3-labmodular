package main;

import java.util.Random;

public class Estacionamento {

	private String nome;
	private Cliente[] id;
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;
	private int quantClientes=0;
    private int quantVagas=quantFileiras*vagasPorFileira;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		id=new Cliente[100];
		this.nome=nome;
		this.quantFileiras=fileiras;
		this.vagasPorFileira=vagasPorFila;
	    vagas=new Vaga[quantVagas];	
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
	for(int i=0;i<quantClientes;i++){
		if(id[i].getId().equals(idCli)){
		   id[i].addVeiculo(veiculo);
		}
	}

	System.out.println("Cliente nao encontrado não foi possivel adicionar o veiculo");


}

	public void addCliente(Cliente cliente) {
	if(quantClientes<id.length){
		id[quantClientes]=cliente;
		quantClientes++;
	}
	System.out.println("O cliente "+cliente.getNome()+" foi adicionado ");
	}

	private void gerarVagas() {
	if(quantVagas<vagas.length){
	Random ale= new Random();
	int numeroVaga=ale.nextInt(quantVagas);
	char letraVaga=(char)('a'+ale.nextInt(26));
      Vaga nova= new Vaga(letraVaga,numeroVaga);
	  vagas[quantVagas]=nova;
	  quantVagas++;
	  System.out.println("Nova vaga criada numero "+numeroVaga+"posição da vaga "+letraVaga);
	}
	
	}

	public void estacionar(String placa) {
    for(int i=0;i<quantClientes;i++){
		if(id[i].possuiVeiculo(placa)==true){
			for(int j=0;j<quantVagas;j++){
				if(vagas[j].disponivel()==true){
					Vaga disponivel= vagas[j];
					UsoDeVaga nova = new UsoDeVaga(disponivel);
					disponivel.estacionar();
				}
			}
			
		}
	}
	}

	public double sair(String placa) {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

}