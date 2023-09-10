package models;

public class PessoaJuridica extends Cliente {

    public PessoaJuridica(String nome, String contato, String endereco, String valorIdentificador) {
        super(nome, contato, endereco, valorIdentificador, IdentificadorUnicoCliente.CNPJ);

        if (valorIdentificador.length() != 11) {
            throw new IllegalArgumentException("O CPF deve ter 14 números!");
        }
    }

    @Override
    public String toString() {
        return "Pessoa Jurídica {" +
                "nome='" + nome + '\'' +
                ", contato=" + contato +
                ", endereco=" + endereco +
                ", " + identificadorUnico.toString() + "=" + valorIdentificador +
                '}';
    }

}
