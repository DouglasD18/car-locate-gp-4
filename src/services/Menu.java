package services;

import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Menu {

    private GerenciamentoLocacao gerenciamentoLocacao = new GerenciamentoLocacao();
    private GerenciadorDeVeiculos gerenciadorDeVeiculos = new GerenciadorDeVeiculos();
    private GerenciadorDeClientes gerenciadorDeClientes = new GerenciadorDeClientes();

    public void listarLocacoes() {
        ArrayList<Locacao> locacoes = gerenciamentoLocacao.listarLocacoes();

        if (locacoes.isEmpty()) {
            System.out.println("Ainda não existem locações!");
        } else {
            locacoes.forEach(locacao -> System.out.println(locacao.toString()));
        }
    }

    public boolean clienteExiste(String identificador) {
        if (identificador.length() != 11 && identificador.length() != 14) {
            throw new IllegalArgumentException("O identificador do cliente deve ter 11 ou 14 caracteres!");
        }

        Cliente cliente = gerenciadorDeClientes.buscar(identificador);

        return cliente != null;
    }

    public boolean veiculoExiste(String placa) {
        if (placa.length() != 7) {
            throw new IllegalArgumentException("A placa deve ter 7 caracteres.");
        }

        Veiculo veiculo = gerenciadorDeVeiculos.buscar(placa);

        return veiculo != null;
    }

    public void listarLocacoesPorCliente(String identificador) {
        if (!clienteExiste(identificador)) {
            System.out.println("O cliente não existe!");
        }

        ArrayList<Locacao> locacoes = gerenciamentoLocacao.listarPorLocatario(identificador);

        if (locacoes.isEmpty()) {
            System.out.println("O cliente ainda tem locações.");
        }

        locacoes.forEach(locacao -> System.out.println(locacao.toString()));
    }

    public void criarLocacao(String placa, String identificador) {
        if (!veiculoExiste(placa)) {
            throw new IllegalArgumentException("O veiculo não existe!");
        }

        if (!clienteExiste(identificador)) {
            throw new IllegalArgumentException("O cliente não existe!");
        }

        gerenciamentoLocacao.criarLocacao(placa, identificador);
        gerenciadorDeVeiculos.setarDisponibilidade(placa, false);
    }

    public void listarClientes() {
        ArrayList<Cliente> clientes = gerenciadorDeClientes.listar();

        if (clientes == null) {
            System.out.println("Ainda não há clientes cadastrados.");
        } else {
            clientes.forEach(cliente -> System.out.println(cliente.toString()));
        }
    }

    public void listarVeiculos() {
        ArrayList<Veiculo> veiculos = gerenciadorDeVeiculos.listar();

        if (veiculos == null) {
            System.out.println("Ainda não há clientes cadastrados.");
        } else {
            veiculos.forEach(veiculo -> System.out.println(veiculo.toString()));
        }
    }

    public void adicionarCliente(String nome, String contato, String endereco, String valorIdentificador) {
        if (valorIdentificador.length() == 1) {
            Cliente cliente = new PessoaFisica(nome, contato, endereco, valorIdentificador);
            this.gerenciadorDeClientes.cadastrar(cliente);
        } else {
            Cliente cliente = new PessoaJuridica(nome, contato, endereco, valorIdentificador);
            this.gerenciadorDeClientes.cadastrar(cliente);
        }
    }

    public void adicionarVeiculo(String placa, TipoVeiculo tipoVeiculo) {
        Veiculo veiculo = new Veiculo(placa, tipoVeiculo);
        this.gerenciadorDeVeiculos.cadastrar(veiculo);
    }

    public void pagarLocacao(String placa, LocalDateTime dataDeEntregaReal) {
        Locacao locacao = gerenciamentoLocacao.listarPorVeiculo(placa);

        if (locacao == null) {
            System.out.println("O veículo não está locado.");
        } else {
            System.out.println("Valor pago: " + locacao.devolucao(dataDeEntregaReal));
            gerenciadorDeVeiculos.setarDisponibilidade(placa, true);
        }

    }

}
