package models;

public class PessoaJuridica extends Cliente {

    public PessoaJuridica(String nome, String contato, String endereco, String valorIdentificador) {
        super(nome, contato, endereco, valorIdentificador, IdentificadorUnicoCliente.CNPJ);

        if (valorIdentificador.length() != 11) {
            throw new IllegalArgumentException("O CNPJ deve ter 14 números!");
        }
    }

    @Override
    public String toString() {
        return "Pessoa Jurídica {" +
                "nome='" + getNome() + '\'' +
                ", contato=" + getContato() +
                ", endereco=" + getEndereco() +
                ", " + getIdentificadorUnico().toString() + "=" + getValorIdentificador() +
                '}';
    }

}
