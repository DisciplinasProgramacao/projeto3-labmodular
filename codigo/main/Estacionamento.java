package main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
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

	/**
	 * Adiciona um cliente no estacionamento.
	 * @param cliente
	 * @throws IOException
	 */
	public void addCliente(Cliente cliente) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter("estacionamentoClientes.txt", true));
		
		try {
			if(clientes.get(cliente.getId()) == null){
				clientes.put(cliente.getId(), cliente);
				bw.newLine();
				bw.write(this.id+";"+cliente.getId());
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

	/**
	 * Gera de maneira aleatória as vagas do estacionamento.
	 */
	private void gerarVagas() {
		for(int i=0; i < quantVagas; i++){
			Random ale= new Random();
			int numeroVaga=ale.nextInt(quantVagas);
			char letraVaga=(char)('a'+ale.nextInt(26));
			Vaga nova= new Vaga(letraVaga,numeroVaga, this.nome);
			vagas.put(hashCode(), nova);
		}
	}

	/**
	 * Recupera uma vaga de forma aleatória.
	 */
	public Vaga getVagaAleatoria(){
		Vaga valueVagaDisp = null;
		for (var entryVagas : vagas.entrySet()) {
			if(entryVagas.getValue().disponivel()){
				valueVagaDisp = entryVagas.getValue();
			}
		}
		return valueVagaDisp;
	}

	/**
	 * Adiciona um veículo em uma vaga do estacionamento.
	 * @param placa
	 */
	public void estacionar(String placa) {   
		
		try{
			Cliente cliente = clientes.entrySet().stream().filter(x -> x.getValue().possuiVeiculo(placa)).findFirst().get().getValue(); 
			Vaga vaga = vagas.entrySet().stream().filter(v -> v.getValue().disponivel()).findFirst().get().getValue();
			cliente.getVeiculo(placa).estacionar(vaga);	
		}catch(NullPointerException npe){
			npe.notify();
		}

		// for(var entryClientes : clientes.entrySet()){
        //     Cliente valueCliente = entryClientes.getValue(); // Atribui o value do getValue a uma variável para ficar mais facil de trabalhar
		// 	if(valueCliente.possuiVeiculo(placa)){
		// 		for (var entryVagas : vagas.entrySet()) {
        //             if(entryVagas.getValue().disponivel()){
        //                 Vaga valueVagaDisp = entryVagas.getValue();
        //                 valueCliente.getVeiculo(placa).estacionar(valueVagaDisp);
        //                 break;
        //             }
        //         }
		// 	}
		// }
	}

	/**
	 * Adiciona um veículo em uma vaga do estacionamento utilizando um dos serviços disponíveis.
	 * @param placa
	 * @param serv
	 */
	public void estacionar(String placa, Servicos serv) {
		
		try{
			Cliente cliente = clientes.entrySet().stream().filter(x -> x.getValue().possuiVeiculo(placa)).findFirst().get().getValue(); 
			Vaga vaga = vagas.entrySet().stream().filter(v -> v.getValue().disponivel()).findFirst().get().getValue();
			cliente.getVeiculo(placa).estacionar(vaga, serv);	
		}catch(NullPointerException npe){
			npe.notify();
		}
		

		// for(var entryClientes : clientes.entrySet()){
        //     Cliente valueCliente = entryClientes.getValue(); // Atribui o value do getValue a uma variável para ficar mais facil de trabalhar
		// 	if(valueCliente.possuiVeiculo(placa)){
		// 		for (var entryVagas : vagas.entrySet()) {
        //             if(entryVagas.getValue().disponivel()){
        //                 Vaga valueVagaDisp = entryVagas.getValue();
        //                 valueCliente.getVeiculo(placa).estacionar(valueVagaDisp, serv);
        //                 break;
        //             }
        //         }
		// 	}
		// }
	}

	public List<Cliente> getClientes(){
        List<Cliente> listaCliente = new ArrayList<Cliente>(this.clientes.values());
		return listaCliente;
	}

	/**
	 * Remove o veículo de uma vaga do estacionamento.
	 * @param placa
	 */
	public void sair(String placa) {
		double total = 0d;

		ArrayList<UsoDeVaga> usos = clientes.entrySet().stream().filter(x -> x.getValue().possuiVeiculo(placa)).findFirst().get().getValue().getVeiculo(placa).getUsos(); 
		Optional<UsoDeVaga> usosOpt = usos.stream().filter(u -> !u.getStatus()).findAny();

		if(usosOpt.isPresent()){
			UsoDeVaga usoReal = (UsoDeVaga)usosOpt.get();
			usoReal.sair();
		}
		// for(var entryClientes : clientes.entrySet()){
		// 	if(entryClientes.getValue().possuiVeiculo(placa)){
        //         Cliente value = entryClientes.getValue();
		// 		for(UsoDeVaga uv : value.getVeiculo(placa).getUsos()){
		// 			if(!uv.getStatus()){ 
		// 				if(total > 0){
		// 					uv.sair();
		// 					uv.getVaga().sair();
		// 				}
		// 			}
		// 		}
		// 	}
		// }
	}

	/**
	 * Retorna o total arrecadado pelo estacionamento.
	 */
	public double totalArrecadado() {
        double totalCliente = 0;

        for(var entryClientes : clientes.entrySet()){
            totalCliente += entryClientes.getValue().arrecadadoTotal();
        }
        
        return totalCliente;
        
	}

	//public double arrecadacaoNoMes(int mes) {}

	/**
	 * Retorna o valor médio pelo uso de vaga do estacionamento.
	 */
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