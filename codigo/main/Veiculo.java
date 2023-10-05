package main;
/*
 * Classe criada em 04/10/2023
 * por Gabriel Henrique
 * */
public class Veiculo {

	private String placa;
	private UsoDeVaga[] usos = new UsoDeVaga[8000]; //Mudar para arrayList

	public Veiculo(String placa) {
		this.placa = placa;
	}

	/*
	 * Quando estacionar, muda o status da vaga para disponivel=false..
	 * */
	public void estacionar(Vaga vaga) {
		boolean res = true;
		
		if(vaga.disponivel()) {
			try {
				vaga.estacionar();
			} catch(Exception e) {
				res = false;
			}
		} else {
			res = false;
		}
		
		return res;
	}

	public double sair() {
		
	}

	public double totalArrecadado() {
		
	}

	public double arrecadadoNoMes(int mes) {
		
	}

	public int totalDeUsos(Vaga vaga) {
		
	}
	
	public String getPlaca() {
		return this.placa;
	}

}
