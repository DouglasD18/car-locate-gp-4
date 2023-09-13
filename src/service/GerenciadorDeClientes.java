package service;

import models.Cliente;

import java.util.ArrayList;
import java.util.List;


public class GerenciadorDeClientes implements Gerenciador<Cliente> {

    List<Cliente> clientes = new ArrayList<>();


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

    }

    @Override
    public void excluir(Cliente cliente) {
        clientes.remove(cliente);

    }

    @Override
    public Cliente buscar(String documento) {
        for (Cliente cliente : clientes) {
            if (cliente.getValorIdentificador().equals(documento)) {
                return cliente;
            }
        }
        return null;
    }
}
