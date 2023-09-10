package models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.UUID;

public class Locacao {

    private UUID id;
    private Cliente locatario;
    private Veiculo veiculo;

    private LocalDateTime dataDeRetirada;
    private LocalDateTime dataDeDevolucao;

    private double valorBase;

    public Locacao(Cliente locatario, Veiculo veiculo) {
        this.id = UUID.randomUUID();
        this.locatario = locatario;
        this.veiculo = veiculo;
        this.dataDeRetirada = LocalDateTime.now();
        if (locatario.getIdentificadorUnico().toString().equals("CPF")) {
            this.dataDeDevolucao = dataDeRetirada.plusDays(5);
        } else {
            this.dataDeDevolucao = dataDeRetirada.plusDays(3);
        }
        if (veiculo.getTipoVeiculo().toString().equals("Pequeno")) {
            this.valorBase = 100.00;
        } else if (veiculo.getTipoVeiculo().toString().equals("Médio")) {
            this.valorBase = 150.00;
        } else {
            this.valorBase = 200;
        }

    }

    public double devolucao() {
        //this.dataDeDevolucao = LocalDateTime.now();
        LocalDate retirada = dataDeRetirada.toLocalDate();
        LocalDate devolucao = dataDeDevolucao.toLocalDate();
        Period valor = Period.between(retirada, devolucao);
        Duration diferenca = Duration.between(retirada, devolucao);


        long minutos = diferenca.toMinutesPart();
        int dias = valor.getDays();

        double valorFinal = 0;

        if (locatario.getIdentificadorUnico().toString().equals("CPF")) {
            if (dias >= 5 && minutos > 0) {
                double valorSemDesconto = valorBase * (dias + 1);
                valorFinal = valorSemDesconto * 0.95;
            } else {
                valorFinal = valorBase * dias;
            }
        } else if (locatario.getIdentificadorUnico().toString().equals("CNPJ")) {
            if (dias >= 3 && minutos > 0) {
                double valorSemDesconto = valorBase * (dias + 1);
                valorFinal = valorSemDesconto * 0.90;
            } else {
                valorFinal = valorBase * dias;

            }
        }

        return valorFinal;
    }
}
