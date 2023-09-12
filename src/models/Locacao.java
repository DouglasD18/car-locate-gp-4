package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Locacao {

    private UUID id;
    private Cliente locatario;
    private Veiculo veiculo;

    private LocalDateTime dataDeRetirada;

    private double valorBase;

    public Locacao(Cliente locatario, Veiculo veiculo) {
        this.id = UUID.randomUUID();
        this.locatario = locatario;
        this.veiculo = veiculo;
        this.dataDeRetirada = LocalDateTime.now();

        if (veiculo.getTipoVeiculo().toString().equals("Pequeno")) {
            this.valorBase = 100.00;
        } else if (veiculo.getTipoVeiculo().toString().equals("Médio")) {
            this.valorBase = 150.00;
        } else {
            this.valorBase = 200;
        }

    }


    public double devolucao(LocalDateTime dataDeEntregaReal) {

        Duration duracaoLocacao = Duration.between(dataDeRetirada,dataDeEntregaReal);
        long diasDeLocacao = duracaoLocacao.toDays()+1;

        double valorFinal = 0;

        if (locatario.getIdentificadorUnico().toString().equals("CPF")) {
            if (diasDeLocacao >= 5) {
                double valorSemDesconto = valorBase * (diasDeLocacao + 1);
                valorFinal = valorSemDesconto * 0.95;
            } else {
                valorFinal = valorBase * diasDeLocacao;
            }
        } else if (locatario.getIdentificadorUnico().toString().equals("CNPJ")) {
            if (diasDeLocacao >= 3 ) {
                double valorSemDesconto = valorBase * (diasDeLocacao + 1);
                valorFinal = valorSemDesconto * 0.90;
            } else {
                valorFinal = valorBase * diasDeLocacao;

            }
        }

        return valorFinal;
    }

}
