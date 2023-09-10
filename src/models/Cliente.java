package models;

public abstract class Cliente {

    String nome, contato, endereco, valorIdentificador;
    IdentificadorUnicoCliente identificadorUnico;

    public abstract String toString();
    abstract String getNome();
    abstract String getContato();
    abstract String getEndereco();
    abstract String getValorIdentificador();
    abstract void setContato();
    abstract void setEndereco();
}
