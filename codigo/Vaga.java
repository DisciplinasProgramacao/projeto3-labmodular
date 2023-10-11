

public class Vaga {

	private String id;
	private boolean disponivel;

	public Vaga(int fila, int numero) {import java.util.Date;

public class UsoDeVaga {
    private String identificadorCliente;
    private int numeroVaga;
    private Date dataInicio;
    private Date dataFim;
    private double valorPago;

    public UsoDeVaga(String identificadorCliente, int numeroVaga, Date dataInicio) {
        this.identificadorCliente = identificadorCliente;
        this.numeroVaga = numeroVaga;
        this.dataInicio = dataInicio;
    }

    public void encerrarUso(Date dataFim, double valorPago) {
        this.dataFim = dataFim;
        this.valorPago = valorPago;
    }

    public String getIdentificadorCliente() {
        return identificadorCliente;
    }

    public int getNumeroVaga() {
        return numeroVaga;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public double getValorPago() {
        return valorPago;
    }
}
		
	}

	public boolean estacionar() {
		
	}

	public boolean sair() {
		
	}

	public boolean disponivel() {
		
	}

}
