package models;

public abstract class Cliente {

    private String nome, contato, endereco, valorIdentificador;

    private IdentificadorUnicoCliente identificadorUnico;

    public Cliente(String nome, String contato, String endereco, String valorIdentificador, IdentificadorUnicoCliente identificadorUnico) {
        this.nome = nome;
        this.contato = contato;
        this.endereco = endereco;
        this.valorIdentificador = valorIdentificador;
        this.identificadorUnico = identificadorUnico;
    }

    public String getNome() {
        return nome;
    }
    public IdentificadorUnicoCliente getIdentificadorUnico() {
        return identificadorUnico;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getValorIdentificador() {
        return identificadorUnico.toString() + "=" + valorIdentificador;
    }

    public abstract String toString();

}
