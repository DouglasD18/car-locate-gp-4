package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public class Locacao {

    private UUID id;
    private String identificadorLocatario;
    private String placa;

    private LocalDateTime dataDeRetirada;

    private double valorBase;

    public Locacao(String identificadorLocatario, String placa) {
        this.id = UUID.randomUUID();
        this.identificadorLocatario = identificadorLocatario;
        this.placa = placa;
        this.dataDeRetirada = LocalDateTime.now();

        if (placa.equals("Pequeno")) {
            this.valorBase = 100.00;
        } else if (placa.equals("Médio")) {
            this.valorBase = 150.00;
        } else {
            this.valorBase = 200;
        }

    }


    public double devolucao(LocalDateTime dataDeEntregaReal) {

        Duration duracaoLocacao = Duration.between(dataDeRetirada,dataDeEntregaReal);
        long diasDeLocacao = duracaoLocacao.toDays()+1;

        double valorFinal = 0;

        if (identificadorLocatario.equals("CPF")) {
            if (diasDeLocacao >= 5) {
                double valorSemDesconto = valorBase * (diasDeLocacao + 1);
                valorFinal = valorSemDesconto * 0.95;
            } else {
                valorFinal = valorBase * diasDeLocacao;
            }
        } else if (identificadorLocatario.equals("CNPJ")) {
            if (diasDeLocacao >= 3 ) {
                double valorSemDesconto = valorBase * (diasDeLocacao + 1);
                valorFinal = valorSemDesconto * 0.90;
            } else {
                valorFinal = valorBase * diasDeLocacao;

            }
        }

        return valorFinal;
    }

    public String getIdentificadorLocatario() {
        return identificadorLocatario;
    }

    public String getPlaca() {
        return  this.placa;
    }

    @Override
    public String toString() {
        return "Locacao{" +
                "identificador do locatario ='" + identificadorLocatario + '\'' +
                ", placa do veiculo ='" + placa + '\'' +
                ", data de locação =" + dataDeRetirada +
                '}';
    }
}
