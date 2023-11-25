package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Estacionamento{

	private Integer id;
	private String nome;
	private Map<Integer, Cliente> clientes = new HashMap<Integer, Cliente>(200);
	private Map<Integer, Vaga> vagas = new HashMap<Integer, Vaga>(200);;
	private int quantFileiras;
	private int vagasPorFileira;
    private int quantVagas;

	public Estacionamento(Integer id, String nome, int fileiras, int vagasPorFila) {
		this.id = id;
		this.nome=nome;
		this.quantFileiras=fileiras;
		this.vagasPorFileira=vagasPorFila;
		this.quantVagas = quantFileiras*vagasPorFileira;	
		gerarVagas();
	}

	public void addCliente(Cliente cliente) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("estacionamentoClientes.txt", true));
		
		try {
			if(clientes.get(cliente.getId()) == null){
				clientes.put(cliente.getId(), cliente);
				bw.newLine();
				bw.write(0+";"+cliente.getId());
			}
        } catch (Exception e) {
            throw(e);
        }
	}

	public Cliente getById(Integer id){
		Cliente c = null;
		try{
			c = clientes.get(id);
		}catch(NullPointerException e){
			throw new RuntimeException();
		}
		return c;
	}

	private void gerarVagas() {
		for(int i=0; i < quantVagas; i++){
			Random ale= new Random();
			int numeroVaga=ale.nextInt(quantVagas);
			char letraVaga=(char)('a'+ale.nextInt(26));
			Vaga nova= new Vaga(letraVaga,numeroVaga, this.nome);
			vagas.put(hashCode(), nova);
		}
	}

	public Vaga getVagaAleatoria(){
		Vaga valueVagaDisp = null;
		for (var entryVagas : vagas.entrySet()) {
			if(entryVagas.getValue().disponivel()){
				valueVagaDisp = entryVagas.getValue();
			}
		}
		return valueVagaDisp;
	}

	public void estacionar(String placa) {                                        
		for(var entryClientes : clientes.entrySet()){
            Cliente valueCliente = entryClientes.getValue(); // Atribui o value do getValue a uma variável para ficar mais facil de trabalhar
			if(valueCliente.possuiVeiculo(placa)){
				for (var entryVagas : vagas.entrySet()) {
                    if(entryVagas.getValue().disponivel()){
                        Vaga valueVagaDisp = entryVagas.getValue();
                        valueCliente.getVeiculo(placa).estacionar(valueVagaDisp);
                        break;
                    }
                }
			}
		}
	}

	public void estacionar(String placa, Servicos serv) {
		for(var entryClientes : clientes.entrySet()){
            Cliente valueCliente = entryClientes.getValue(); // Atribui o value do getValue a uma variável para ficar mais facil de trabalhar
			if(valueCliente.possuiVeiculo(placa)){
				for (var entryVagas : vagas.entrySet()) {
                    if(entryVagas.getValue().disponivel()){
                        Vaga valueVagaDisp = entryVagas.getValue();
                        valueCliente.getVeiculo(placa).estacionar(valueVagaDisp, serv);
                        break;
                    }
                }
			}
		}
	}

	public List<Cliente> getClientes(){
        List<Cliente> listaCliente = new ArrayList<Cliente>(this.clientes.values());
		return listaCliente;
	}


	public double sair(String placa) {
		double total = 0d;
		for(var entryClientes : clientes.entrySet()){
			if(entryClientes.getValue().possuiVeiculo(placa)){
                Cliente value = entryClientes.getValue();
				for(UsoDeVaga uv : value.getVeiculo(placa).getUsos()){
					if(!uv.getStatus()){ 
						total = value.calcularPagamento();
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
        double totalCliente = 0;

        for(var entryClientes : clientes.entrySet()){
            totalCliente += entryClientes.getValue().arrecadadoTotal();
        }
        
        return totalCliente;
        
	}

	//public double arrecadacaoNoMes(int mes) {}

	public double valorMedioPorUso() {
        double mediaPorUso = 0;
        try{
            mediaPorUso = totalArrecadado()/clientes.size();
        }catch(Exception e){
            mediaPorUso = 0;
        }
		return mediaPorUso;
	}

	//public String top5Clientes(int mes) {}

	@Override
	public String toString(){
		return this.nome;
	}

    public String toStringClientes(){
        StringBuilder b = new StringBuilder();

        for(var entryClientes : clientes.entrySet()){
            b.append(entryClientes.getValue().toString());
        }
        return b.toString();
    }

	public Map<Integer, Vaga> getVagas(){
		return this.vagas;
	}

	public double valorMedio(){
		double total = 0d;
		int horistas = 0;
		for(Cliente c: this.getClientes()){
			if(c.getCategoria().getClass().getName().contains("main.Horista")){
				total += c.arrecadadoNoMes(LocalDate.now().getMonthValue());
				horistas+=1;
			}
			
		}
		return total/horistas;
	}
}