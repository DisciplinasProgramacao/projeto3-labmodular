

public class Vaga {

	private String id;
	private boolean disponivel;

	public Vaga
	(char fila, int numero) {
	   this.id="Fila "+fila+" numero "+numero;
	   disponivel=false;
	}

	public boolean estacionar() {
		return true;
	}

	public boolean sair() {
		return true;
	}

	public boolean disponivel() {
		return true;
	}

}
