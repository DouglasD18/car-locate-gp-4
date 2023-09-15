package models;

public class PessoaFisica extends Cliente {
    String cpf;
    public PessoaFisica(String nome, String contato, String endereco, String valorIdentificador) {
        super(nome, contato, endereco, valorIdentificador, IdentificadorUnicoCliente.CPF);
    }

    @Override
    public String toString() {
        return "Pessoa Física {" +
                "nome='" + getNome() + '\'' +
                ", contato=" + getContato() +
                ", endereco=" + getEndereco() +
                ", " + getIdentificadorUnico().toString() + "=" + getValorIdentificador() +
                '}';
    }

    public String getCpf() {
        return cpf;
    }
}
