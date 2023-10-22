package main;
/*
 * Classe criada em 04/10/2023
 * por Gabriel Henrique
 * */

import interfaces.IArrecadavel;
import java.util.ArrayList;

public class Veiculo implements IArrecadavel {

	private String placa;
	private boolean status = false;//não esta estacionado
	private boolean temDono = false;
	private ArrayList<UsoDeVaga> usos = new ArrayList<UsoDeVaga>(); //Mudar para arrayList

	public Veiculo(String placa) {
		this.placa = placa;
	}

	/*
	 * Quando estacionar, muda o status da vaga para disponivel=false..
	 * */
	public boolean estacionar(Vaga vaga) {
		boolean res = true;
		if(vaga.disponivel()) {
			try {
				vaga.estacionar();
				UsoDeVaga usoVaga = new UsoDeVaga(vaga);
				this.usos.add(usoVaga);
				this.changeStatus();
			} catch(Exception e) {
				res = false;
			}
		} else {
			res = false;
		}
		return res;
	}

	/*
	 * Quando sair da vaga, invoca o método sair da classe Vaga
  	 * e disponibiliza como disponivel=true;
	 * */
	public boolean sair(Vaga vaga) {
		boolean res = true;
		try {
			vaga.sair();
			this.status = false;
		} catch(Exception e) {
			res = false;
		}
		return res;
	}

	public double totalArrecadado() {
		double total = 0.0;
		for(UsoDeVaga uv : usos) {
			total += uv.valorPago();
		}
		return total;
	}

	public double arrecadadoNoMes(int mes) {
		return 0.0;
	}
	
	/*
	 * Inclui no uso de vaga, a vaga utilizada.
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

	@Override
	public double arrecadadoTotal() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'arrecadadoTotal'");
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

	public void changeStatus(){
		this.status = true;
	}

	public boolean getStatus(){
		return this.status;
	}

	public void temDono(){
		this.temDono = true;
	}

	public boolean getTemDono(){
		return this.temDono;
	}
}
