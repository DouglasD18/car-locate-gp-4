package models;

public class PessoaFisica extends Cliente {
    String cpf;
    public PessoaFisica(String nome, String contato, String endereco, String valorIdentificador) {
        super(nome, contato, endereco, valorIdentificador, IdentificadorUnicoCliente.CPF);

        if (valorIdentificador.length() != 11) {
            throw new IllegalArgumentException("O CPF deve ter 11 números!");
        }
    }

    @Override
    public String toString() {
        return "Pessoa Física {" +
                "nome='" + nome + '\'' +
                ", contato=" + contato +
                ", endereco=" + endereco +
                ", " + identificadorUnico.toString() + "=" + valorIdentificador +
                '}';
    }

    public String getCpf() {
        return cpf;
    }
}
