package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class app {

    static ArrayList<Estacionamento> estacionamentos = new ArrayList<Estacionamento>();
    static ArrayList<Cliente> clientes = new ArrayList<Cliente>();
    static ArrayList<Veiculo> veiculos = new ArrayList<Veiculo>();

    public static void main(String args[]) throws IOException{
        carregarDados();
        menu();
    }

    public static void menu(){
        Scanner s = new Scanner(System.in);


        System.out.print("\nO que deseja fazer?\n");
        System.out.print("  1-Listagens\n");
        System.out.print("  2-Relatorios\n");
        System.out.print("  3-Clientes\n");
        System.out.print("  4-Estacionamento\n");

        System.out.println("Selecione uma opção\n");
        int opcao = s.nextInt();

        switch(opcao){
            case 1:
                Listagens();
                break;
             case 2:
                Relatorios();
                break;
             case 3:
                Clientes();
                break;
            case 4:
                Estacionamento();
                break;
        }
    }

    public static void Listagens(){
        System.out.print("Listagens\n\n");
        System.out.print("  1-Listar Clientes\n");
        System.out.print("  2-Listar Veiculos\n");
        System.out.print("  3-Listar Estacionamentos\n\n");
        

        Scanner s1 = new Scanner(System.in);

        System.out.print("\nO que deseja?\n");

        int opcao = s1.nextInt();

        switch(opcao){
            case 1:
                for(Cliente c : clientes){
                    System.out.println(c.toString());
                }
                menu();
                break;
             case 2:
                for(Veiculo v: veiculos){
                    System.out.println(v.toString());
                }
                menu();
                break;
             case 3:
                for(Estacionamento e : estacionamentos){
                    System.out.println(e.toString());
                }
                menu();
                break;
        }
    }

    public static void Relatorios(){
        System.out.print("Relatorios\n\n");
        System.out.print("  1-Valor arrecadado estacionamento\n");
        System.out.print("  2-Valor arrecadado mês\n");
        System.out.print("  3-Valor médio de utilização\n");
        System.out.print("  4-Ranking dos clientes\n\n");

        System.out.print("O que deseja?");


        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();

        double total = 0d;
        System.out.println("Selecione");
        for(int i = 0; i< estacionamentos.size(); i++){
            System.out.println(i + "- " + estacionamentos.get(i).toString());
        }
        int selecao = s.nextInt();
        Estacionamento est = estacionamentos.get(selecao);

        switch(opcao){
            case 1:
                total = est.totalArrecadado();
                System.out.print(total);
                menu();
                break;
             case 2:
                System.out.println("Selecione(1-janeiro e assim por diante)");
                int mes = s.nextInt();
                total = est.arrecadacaoNoMes(mes);
                System.out.println(total);
                menu();
                break;
             case 3:
                total = est.valorMedioPorUso();
                System.out.println(total);
                menu();
                break;
            case 4:
                System.out.println("Selecione(1-janeiro e assim por diante)");
                int mes1 = s.nextInt();
                System.out.println(est.top5Clientes(mes1));
                menu();
                break;
        }
    }

    public static void Clientes(){
        System.out.print("Clientes\n\n");
        System.out.print("  1-Cadastrar cliente\n");
        System.out.print("  2-Adicionar veiculo\n");
        System.out.print("  3-Estacionar veiculo\n");
        System.out.print("  4-Retirar veiculo\n");
        System.out.print("  5-Usos de estacionamentos\n");
        System.out.print("  6-Arrecadado no mês\n\n");

        System.out.print("O que deseja?");


        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();

        switch(opcao){
            case 1:
                try {
                    cadastrarCliente();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                menu();
                break;
            case 2:
                System.out.print("Qual veiculo deseja adicionar?\n\n");

                for(int i = 0; i< veiculos.size(); i++){
                    if(!veiculos.get(i).getTemDono()){ System.out.println(i + "- " + veiculos.get(i).getPlaca()); }
                }

                int selecao = s.nextInt();
                Veiculo v = veiculos.get(selecao);

                System.out.print("Para qual cliente?\n\n");
                for(int i = 0; i< clientes.size(); i++){
                    System.out.println(i + "- " + clientes.get(i).getNome());
                }

                int selecao1 = s.nextInt();
                Cliente c = clientes.get(selecao1);
                c.addVeiculo(v);

                menu();
                break;
            case 3:
                System.out.println("Qual estacionamento?\n\n");
                for(int i = 0; i< estacionamentos.size(); i++){
                    System.out.println(i+"- "+ estacionamentos.get(i).toString());
                }

                int selecao4 = s.nextInt();
                Estacionamento e1 = estacionamentos.get(selecao4);

                if(e1.getClientes().size() <= 0){ menu(); }
                System.out.print("Qual cliente?\n\n");

                for(int i = 0; i< e1.getClientes().size(); i++){
                    System.out.println(i + "- " + e1.getClientes().get(i).getNome());
                }

                int selecao2 = s.nextInt();
                Cliente c1 = e1.getClientes().get(selecao2);


                if(c1.getVeiculosValidosCount() <= 0){ menu(); }
                ArrayList<Veiculo> veiculosTemp = null;
                System.out.print("Qual veiculo?\n\n");
                for(int i = 0; i< c1.getVeiculos().size(); i++){
                    veiculosTemp = c1.getVeiculos();
                    for(int j = 0; j < veiculosTemp.size(); j++){
                        if(!veiculosTemp.get(j).getStatus()){
                            System.out.println(i + "- " + veiculosTemp.get(j).getPlaca());
                        }
                    }
                }

                int selecao3 = s.nextInt();
                Veiculo v1 = veiculosTemp.get(selecao3);

                System.out.print("Quer algum serviço?\n\n");
                System.out.print("  1-Manobrista?\n");
                System.out.print("  2-Lavagem?\n");
                System.out.print("  3-Polimento?\n");
                System.out.print("  4-Nenhum?\n");

                int selecao10 = s.nextInt();
                switch(selecao10){
                    case 1:
                        Servicos serv = new Servicos("MANOBRISTA");
                        e1.estacionar(v1.getPlaca(), serv);
                        break;
                    case 2:
                        Servicos serv1 = new Servicos("LAVAGEM");
                        e1.estacionar(v1.getPlaca(), serv1);
                        break;
                    case 3:
                        Servicos serv3 = new Servicos("POLIMENTO");
                        e1.estacionar(v1.getPlaca(), serv3);
                        break;
                    case 0:
                        e1.estacionar(v1.getPlaca());
                        break;
                        
                }
                menu();
                break;
            case 4:
                System.out.println("Qual estacionamento?\n\n");
                for(int i = 0; i< estacionamentos.size(); i++){
                    System.out.println(i+"- "+ estacionamentos.get(i).toString());
                }

                int selecao7 = s.nextInt();
                Estacionamento e2 = estacionamentos.get(selecao7);

                if(e2.getClientes().size() <= 0){ menu(); }
                System.out.print("Qual cliente?\n\n");

                for(int i = 0; i< e2.getClientes().size(); i++){
                    if(e2.getClientes().get(i).getVeiculosCount() > 0){
                        System.out.println(i + "- " + e2.getClientes().get(i).getNome());
                    }
                }

                int selecao5 = s.nextInt();
                Cliente c2 = e2.getClientes().get(selecao5);

                if(c2.getVeiculosCount() <= 0){ menu(); }
                ArrayList<Veiculo> veiculosTemp1 = null;
                System.out.print("Qual veiculo?\n\n");
                for(int i = 0; i< c2.getVeiculos().size(); i++){
                    veiculosTemp1 = c2.getVeiculos();
                    for(int j = 0; j < veiculosTemp1.size(); j++){
                        if(veiculosTemp1.get(j).getStatus()){
                            System.out.println(i + "- " + veiculosTemp1.get(j).getPlaca());
                        }
                    }
                }

                int selecao6 = s.nextInt();
                Veiculo v2 = veiculosTemp1.get(selecao6);
                System.out.println("Total pago pelo cliente: " + e2.sair(v2.getPlaca()) + " ");
                v2.changeFalse();
                menu();
                break;
            case 5:
                System.out.println("Qual estacionamento?\n\n");
                for(int i = 0; i< estacionamentos.size(); i++){
                    System.out.println(i+"- "+ estacionamentos.get(i).toString());
                }

                int selecao8 = s.nextInt();
                Estacionamento e3 = estacionamentos.get(selecao8);

                System.out.print("Qual cliente?\n\n");

                for(int i = 0; i< e3.getClientes().size(); i++){
                    if(e3.getClientes().get(i).getVeiculosCount() > 0){
                        System.out.println(i + "- " + e3.getClientes().get(i).getNome());
                    }
                }

                int selecao9 = s.nextInt();
                Cliente c3 = e3.getClientes().get(selecao9);

                System.out.println(c3.usoDeEstacionamento(e3.toString()));

                menu();
                break;
            case 6:
                System.out.println("Qual estacionamento?\n\n");
                for(int i = 0; i< estacionamentos.size(); i++){
                    System.out.println(i+"- "+ estacionamentos.get(i).toString());
                }

                int selecao11 = s.nextInt();
                Estacionamento est = estacionamentos.get(selecao11);

                if(est.getClientes().size() <= 0){ menu(); }
                System.out.print("Qual cliente?\n\n");

                for(int i = 0; i< est.getClientes().size(); i++){
                    if(est.getClientes().get(i).getVeiculosCount() > 0){
                        System.out.println(i + "- " + est.getClientes().get(i).getNome());
                    }
                }

                int selecao12 = s.nextInt();
                Cliente c4 = est.getClientes().get(selecao12);

                System.out.println("Selecione(1-janeiro e assim por diante)");
                int mes = s.nextInt();
                double total = c4.arrecadadoNoMes(mes);
                System.out.println(total);
                menu();
                break;
            }
    }

    public static void Estacionamento(){
        System.out.print("Estacionamento\n\n");
        System.out.println("    1-Adicionar cliente\n");

        System.out.print("O que deseja?");


        Scanner s = new Scanner(System.in);
        int opcao = s.nextInt();

        switch (opcao) {
            case 1:
                System.out.println("Qual estacionamento?\n\n");
                for(int i = 0; i< estacionamentos.size(); i++){
                    System.out.println(i+"- "+ estacionamentos.get(i).toString());
                }

                int selecao1 = s.nextInt();
                Estacionamento e1 = estacionamentos.get(selecao1);

                System.out.println("Qual cliente?\n\n");

                for(int i = 0; i< clientes.size(); i++){
                    System.out.println(i + "- " + clientes.get(i).getNome());
                }

                int selecao2 = s.nextInt();
                Cliente c = clientes.get(selecao2);
                e1.addCliente(c);

                menu();
                break;
        
            default:
                break;
        }
    }

    public static void carregarDados() throws IOException{
        BufferedReader bre = new BufferedReader(new FileReader("estacionamentos.txt"));
        String linhae = "";
        
        while((linhae = bre.readLine()) != null){
            String[] linhas = linhae.split(";", 0);
            Estacionamento e = new Estacionamento(linhas[0], Integer.parseInt(linhas[1]), Integer.parseInt(linhas[2]));
            estacionamentos.add(e);	
        }

        BufferedReader brv = new BufferedReader(new FileReader("veiculos.txt"));
        String linhav = "";
        
        while((linhav = brv.readLine()) != null){
            String[] linhas = linhav.split(";", 0);
            Veiculo v = new Veiculo(linhas[0]);
            veiculos.add(v);
        }

        BufferedReader brc = new BufferedReader(new FileReader("clientes.txt"));
        String linhac = "";
        
        while((linhac = brc.readLine()) != null){
            String[] linhas = linhac.split(";", 0);
            Cliente c = new Cliente(linhas[0], linhas[1]);
            clientes.add(c);
        }
    }

    public static void cadastrarCliente() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("clientes.txt", true));

        Scanner s = new Scanner(System.in);

        System.out.println("Digite o seu nome");
        String nome = s.nextLine();

        System.out.println("Digite o seu id");
        String id = s.nextLine();

        Cliente c = new Cliente(nome, id);

        bw.newLine();
        bw.write(c.getNome()+";"+c.getId());
        bw.close();
        clientes.add(c);

    }

    public static void cadastrarVeiculo() throws IOException{
        BufferedWriter bw = new BufferedWriter(new FileWriter("veiculos.txt", true));

        Scanner s = new Scanner(System.in);

        System.out.println("Digite a placa");
        String placa = s.nextLine();

        Veiculo v = new Veiculo(placa);

        bw.newLine();
        bw.write(v.getPlaca());
        bw.close();
        veiculos.add(v);
    }
}
