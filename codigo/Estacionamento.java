public class Estacionamento {
    private String nome;
    private Cliente[] clientes;
    private Vaga[] vagas;
    private int quantFileiras;
    private int vagasPorFileira;
    private int numClientes;
    private int numVagas;

    public Estacionamento(String nome, int fileiras, int vagasPorFila) {
        this.nome = nome;
        this.quantFileiras = fileiras;
        this.vagasPorFileira = vagasPorFila;
        this.numClientes = 0;
        this.numVagas = 0;

        this.clientes = new Cliente[100]; 
        this.vagas = new Vaga[fileiras * vagasPorFila];

        gerarVagas();
    }

    public void addVeiculo(Veiculo veiculo, String idCli) {
        Cliente cliente = null;
        for (int i = 0; i < numClientes; i++) {
            if (clientes[i].getIdentificador().equals(idCli)) {
                cliente = clientes[i];
                break;
            }
        }

        if (cliente != null) {
            cliente.adicionarVeiculo(veiculo);
        }
    }

    public void addCliente(Cliente cliente) {
        if (numClientes < clientes.length) {
            clientes[numClientes] = cliente;
            numClientes++;
        }
    }

    private void gerarVagas() {
        for (int fileira = 1; fileira <= quantFileiras; fileira++) {
            for (int vagaNum = 1; vagaNum <= vagasPorFileira; vagaNum++) {
                Vaga vaga = new Vaga("F" + fileira + "V" + vagaNum);
                vagas[numVagas] = vaga;
                numVagas++;
            }
        }
    }

    public void estacionar(String placa) {
        for (int i = 0; i < numVagas; i++) {
            if (!vagas[i].isOcupada()) {
                vagas[i].estacionarVeiculo(placa);
                break;
            }
        }
    }

    public double sair(String placa) {
        for (int i = 0; i < numVagas; i++) {
            if (vagas[i].getPlaca().equals(placa)) {
                double valorCobrado = vagas[i].sair();
                return valorCobrado;
            }
        }
        return 0.0; 
    }

    public double totalArrecadado() {
        double total = 0.0;
        for (int i = 0; i < numVagas; i++) {
            total += vagas[i].getValorArrecadado();
        }
        return total;
    }

    public double arrecadacaoNoMes(int mes) {
        double totalMes = 0.0;
        for (int i = 0; i < numVagas; i++) {
            if (vagas[i].getMes() == mes) {
                totalMes += vagas[i].getValorArrecadado();
            }
        }
        return totalMes;
    }

    public double valorMedioPorUso() {
        if (numVagas > 0) {
            return totalArrecadado() / numVagas;
        }
        return 0.0;
    }

    public String top5Clientes(int mes) {
        Map<String, Double> arrecadacaoClientes = new HashMap<>();

        for (int i = 0; i < numVagas; i++) {
            Vaga vaga = vagas[i];
            if (vaga.getMes() == mes) {
                String identificadorCliente = vaga.getIdCliente();
                double valorArrecadado = vaga.getValorArrecadado();

                if (arrecadacaoClientes.containsKey(identificadorCliente)) {
                    valorArrecadacao += arrecadacaoClientes.get(identificadorCliente);
                }

                arrecadacaoClientes.put(identificadorCliente, valorArrecadacao);
            }
        }

        List<Map.Entry<String, Double>> topClientes = arrecadacaoClientes
                .entrySet()
                .stream()
                .sorted((a, b) -> b.getValue().compareTo(a.getValue()))
                .limit(5)
                .collect(Collectors.toList());

        StringBuilder top5 = new StringBuilder();
        for (Map.Entry<String, Double> entry : topClientes) {
            String idCliente = entry.getKey();
            double valorArrecadado = entry.getValue();
            top5.append("Cliente: ").append(idCliente).append(", Arrecadação: R$").append(valorArrecadado).append("\n");
        }

        return top5.toString();
    }
}

}
