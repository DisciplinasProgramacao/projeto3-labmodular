package main;
/*
 * Classe criada em 04/10/2023
 * por Gabriel Henrique
 * */
public class Veiculo {

	private String placa;
	private ArrayList<UsoDeVaga> usos = new ArrayList<UsoDeVaga>(); //Mudar para arrayList

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

	/*
	 * Quando sair da vaga, invoca o método sair da classe Vaga
  	 * e disponibiliza como disponivel=true;
	 * */
	public double sair(Vaga vaga) {
		vaga.sair();
	}

	public double totalArrecadado() {
		
	}

	public double arrecadadoNoMes(int mes) {
		
	}
	
	/*
	 * Inclui no uso de vaga, a vaga utilizada.
	 * */
	public int totalDeUsos(Vaga vaga) {
		UsoDeVaga usoVaga = new UsoDeVaga(vaga);
		this.usos.add(usoVaga);
	}
	
	public String getPlaca() {
		return this.placa;
	}

}
