package main;

import java.time.Duration;
import java.time.LocalTime;
import interfaces.ICategoriaCliente;

public class DeTurno implements ICategoriaCliente{
    public String turno;
    public Cliente cli;
    private LocalTime inicioTurno;
    private LocalTime fimTurno;

    public DeTurno(Cliente cliente, String turno) {
        this.cli = cliente;
        this.turno = turno;

        switch (turno) {
            case "manha":
                inicioTurno = LocalTime.of(8, 0);
                fimTurno = LocalTime.of(12, 0);
                break;
            case "tarde":
                inicioTurno = LocalTime.of(12, 1);
                fimTurno = LocalTime.of(18, 0);
                break;
            case "noite":
                inicioTurno = LocalTime.of(18, 1);
                fimTurno = LocalTime.of(23, 59);
                break;
            default:
                throw new IllegalArgumentException("Turno desconhecido: " + turno);
        }
    }

    @Override
    public double calcularPagamento(){
        double valor=200.00;
        for(Veiculo v: cli.getVeiculos()){
            for(UsoDeVaga x:v.getUsos()){
                if(!estaDentroDoTurno((x.getHoraEntrada()),x.getHoraSaida())){
                    valor+=calcularValorAdicional(x.getHoraEntrada(),x.getHoraSaida());
                }
            }
        }
        return valor;
    }
   
    private boolean estaDentroDoTurno(LocalTime horaEntrada, LocalTime horaSaida) {
        return (horaEntrada.compareTo(inicioTurno) >= 0) && (horaSaida.compareTo(fimTurno) <= 0);
    }

    private double calcularValorAdicional(LocalTime horaEntrada, LocalTime horaSaida) {
        double taxaHoraria = 10.0; 
        long minutosForaDoTurno = 0;
    
        if (horaEntrada.isBefore(inicioTurno)) {
            minutosForaDoTurno += Duration.between(horaEntrada, inicioTurno).toMinutes();
        }
    
        if (horaSaida.isAfter(fimTurno)) {
            minutosForaDoTurno += Duration.between(fimTurno, horaSaida).toMinutes();
        }
    
        return (minutosForaDoTurno / 60.0) * taxaHoraria;
    }
}