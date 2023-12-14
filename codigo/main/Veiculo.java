package main;
/*
 * Classe criada em 04/10/2023
 * por Gabriel Henrique
 * */
import java.util.ArrayList;

public class Veiculo {

	private int id;
	private String placa;
	private boolean temDono;
	private boolean estacionado;
	private ArrayList<UsoDeVaga> usos = new ArrayList<UsoDeVaga>(); //Mudar para arrayList

	public Veiculo(){
		this.estacionado = false;
	}

	/**
	 * Define um veiculo com id e placa
	 * @param id
	 * @param placa
	 */
	public Veiculo(int id, String placa) {
		this.id = id;
		this.placa = placa;
		this.estacionado = false;
	}

	/**
	 * Quando estacionar, muda o status da vaga para disponivel=false..
	 * @param vaga
	 * */
	// public boolean estacionar(Vaga vaga) {
	// 	boolean res = true;
	// 	if(vaga.disponivel()) {
	// 		try {
	// 			vaga.estacionar();
	// 			UsoDeVaga usoVaga = new UsoDeVaga(vaga);
	// 			this.usos.add(usoVaga);
	// 			this.setEstacionado(true);
	// 		} catch(Exception e) {
	// 			res = false;
	// 		}
	// 	} else {
	// 		res = false;
	// 	}
	// 	return res;
	// }

	/**
	 * Estaciona um veiculo com a utilização de um serviço
	 * @param vaga
	 * @param serv
	 * @return boolean
	 */
	public boolean estacionar(Vaga vaga, Servicos serv) {
		boolean res = true;
		if(vaga.disponivel()) {
			try {
				vaga.estacionar();
				UsoDeVaga usoVaga = new UsoDeVaga(vaga, serv);
				this.usos.add(usoVaga);
				this.setEstacionado(true);
			} catch(Exception e) {
				res = false;
			}
		} else {
			res = false;
		}
		return res;
	}
	
	/**
	 * Quando sair da vaga, invoca o método sair da classe Vaga
  	 * e disponibiliza como disponivel=true;
	 * @param vaga
	 * */
	public boolean sair(Vaga vaga) {
		boolean res = true;
		try {
			vaga.sair();
			this.setEstacionado(true); 
		} catch(Exception e) {
			res = false;
		}
		return res;
	}

	/**
	 * Retorna o total arrecada pelo veiculo
	 * @return double
	 */
	public double totalArrecadado() {
		double total = 0.0;
		for(UsoDeVaga uv : usos) {
			total += uv.getValorPago();
		}
		return total;
	}

	/**
	 * Adiciona um novo uso de vaga para o veiculo
	 * @param u
	 */
	public void adicionarUso(UsoDeVaga u){
		this.getUsos().add(u);
	}

	public double arrecadadoNoMes(int mes) {
		return 0.0;
	}
	
	/**
	 * Inclui no uso de vaga, a vaga utilizada.
	 * @param vaga
	 * @return int
	 * */
	public int totalDeUsos(Vaga vaga) {
		int total = 0;
		for(UsoDeVaga uv: usos){
			if(uv.getVaga().equals(vaga)){
				total++;
			}
		}
		return total;
	}
	
	public String getPlaca() {
		return this.placa;
	}

	public int getUsosCount(){
		return this.usos.size();
	}

	public ArrayList<UsoDeVaga> getUsos(){
		return this.usos;
	}

	@Override
	public String toString(){
		return this.placa;
	}

	public boolean getTemDono(){
		return this.temDono;
	}

	public void atribuirDono(){
		this.temDono = true;
	}

	public boolean getEstacionado(){
		return this.estacionado;
	}

	public void setEstacionado(boolean status){
		this.estacionado = status;
	}

	public int getId(){
		return this.id;
	}

	public void setId(int id){
		this.id = id;
	}

	public void setPlaca(String placa){
		this.placa = placa;
	}
}
