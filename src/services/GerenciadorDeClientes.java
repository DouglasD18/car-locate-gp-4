package services;

import models.Cliente;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class GerenciadorDeClientes implements Gerenciador<Cliente> {

    ArrayList<Cliente> clientes = new ArrayList<>();

    @Override
    public void cadastrar(Cliente cliente) {
        if (buscar(cliente.getValorIdentificador()) == null) {
            clientes.add(cliente);
        } else {
            System.out.println("Cliente já está cadastrado!");
        }
    }

    @Override
    public void alterar(Cliente cliente) {
        this.clientes = (ArrayList<Cliente>) this.clientes.stream().map(elemente -> {
            if (elemente.getValorIdentificador().equals(cliente.getValorIdentificador())) {
                return cliente;
            } else {
                return elemente;
            }

        }).collect(Collectors.toList());
    }

    @Override
    public void excluir(Cliente cliente) {
        clientes.remove(cliente);
    }

    @Override
    public Cliente buscar(String documento) {
        return this.clientes.stream().filter(cliente -> cliente.getValorIdentificador().equals(documento)).findFirst().orElse(null);
    }

    public ArrayList<Cliente> listar() {
        return this.clientes;
    }

}
