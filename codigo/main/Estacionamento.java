package main;

import java.util.ArrayList;
import java.util.Random;

public class Estacionamento {

	private String nome;
	private ArrayList<Cliente> id = new ArrayList<Cliente>();
	private Vaga[] vagas;
	private int quantFileiras;
	private int vagasPorFileira;
	private int quantClientes=0;
    private int quantVagas;

	public Estacionamento(String nome, int fileiras, int vagasPorFila) {
		this.nome=nome;
		this.quantFileiras=fileiras;
		this.vagasPorFileira=vagasPorFila;
		this.quantVagas = quantFileiras*vagasPorFileira;
	    vagas=new Vaga[quantVagas];	
		gerarVagas();
	}

	public void addVeiculo(Veiculo veiculo, String idCli) {
	for(int i=0;i<quantClientes;i++){
		if(id.get(i).getId().equals(idCli)){
		   id.get(i).addVeiculo(veiculo);
		}
	}

	System.out.println("Cliente nao encontrado não foi possivel adicionar o veiculo");


}

	public void addCliente(Cliente cliente) {
		if(id.contains(cliente)){
			System.out.println("O cliente "+cliente.getNome()+" já existe no estacionamento ");
		}else{
			id.add(cliente);
			quantClientes++;
			System.out.println("O cliente "+cliente.getNome()+" foi adicionado ");
		}
	}

	private void gerarVagas() {
		for(int i=0; i < quantVagas; i++){
			Random ale= new Random();
			int numeroVaga=ale.nextInt(quantVagas);
			char letraVaga=(char)('a'+ale.nextInt(26));
			Vaga nova= new Vaga(letraVaga,numeroVaga, this.nome);
			vagas[i]=nova;
		}
	}

	public void estacionar(String placa) {
		for(int i=0;i<quantClientes;i++){
			if(id.get(i).possuiVeiculo(placa)==true){
				for(int j=0;j<quantVagas;j++){
					if(vagas[j].disponivel()==true){
						Vaga disponivel= vagas[j];
						// UsoDeVaga nova = new UsoDeVaga(disponivel);
						id.get(i).getVeiculo(placa).estacionar(disponivel);
						disponivel.estacionar();
						return;
					}
				}
			}
		}
	}

	public void estacionar(String placa, Servicos serv) {
		for(int i=0;i<quantClientes;i++){
			if(id.get(i).possuiVeiculo(placa)==true){
				for(int j=0;j<quantVagas;j++){
					if(vagas[j].disponivel()==true){
						Vaga disponivel= vagas[j];
						id.get(i).getVeiculo(placa).estacionar(disponivel, serv);
						disponivel.estacionar();
						return;
					}
				}
			}
		}
	}

	public ArrayList<Cliente> getClientes(){
		return this.id;
	}


	public double sair(String placa) {
		double total = 0d;
		for(int i=0;i<quantClientes;i++){
			if(id.get(i).possuiVeiculo(placa)){
				for(UsoDeVaga uv : id.get(i).getVeiculo(placa).getUsos()){
					if(!uv.getStatus()){ 
						total = uv.valorPago();
						if(total > 0){
							uv.sair();
							uv.getVaga().sair();
						}
					}
				}
			}
		}
		return total;
	}

	public double totalArrecadado() {
		
	}

	public double arrecadacaoNoMes(int mes) {
		
	}

	public double valorMedioPorUso() {
		
	}

	public String top5Clientes(int mes) {
		
	}

	@Override
	public String toString(){
		return this.nome;
	}

	public Vaga[] getVagas(){
		return this.vagas;
	}

}