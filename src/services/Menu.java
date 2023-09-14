package services;

import models.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu<ContaDevedora> {

    private GerenciamentoLocacao gerenciamentoLocacao = new GerenciamentoLocacao();
    private GerenciadorDeVeiculos gerenciadorDeVeiculos = new GerenciadorDeVeiculos();
    private GerenciadorDeClientes gerenciadorDeClientes = new GerenciadorDeClientes();

    public int solicitaTrecho(Scanner input, String trecho) {
        try {
            System.out.printf("Digite o %s da devolução:\n", trecho);
            int ano = input.nextInt();
            input.nextLine();

            return ano;
        } catch (InputMismatchException e) {
            System.out.printf("O %s deve ser um número inteiro!\n", trecho);
            return solicitaTrecho(input, trecho);
        }
    }

    public LocalDateTime solicitaData(){
        Scanner input = new Scanner(System.in);

        int ano = solicitaTrecho(input, "ano");
        int mes = solicitaTrecho(input, "mês");
        int dia = solicitaTrecho(input, "dia");
        int hora = solicitaTrecho(input, "hora");
        int minuto = solicitaTrecho(input, "minuto");

        input.close();

        return LocalDateTime.of(ano, mes, dia, hora, minuto, 0);
    }

    public void listarLocações() {
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
            throw new IllegalArgumentException("O cliente não existe!");
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

    // public void adicionarVeiculo() {}
    // public void pagarLocacao() {}

}
