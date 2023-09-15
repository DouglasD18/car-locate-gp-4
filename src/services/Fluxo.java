package services;

import models.Cliente;
import models.PessoaFisica;
import models.TipoVeiculo;

import java.time.LocalDateTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Fluxo {

    private final Menu menu = new Menu();
    private final Scanner scanner = new Scanner(System.in);

    private int solicitaTrecho(String trecho) {
        try {
            System.out.printf("Digite o %s da devolução:\n", trecho);
            int ano = scanner.nextInt();
            scanner.nextLine();

            return ano;
        } catch (InputMismatchException e) {
            System.out.printf("O %s deve ser um número inteiro!\n", trecho);
            return solicitaTrecho(trecho);
        }
    }

    private LocalDateTime solicitaData(){

        int ano = solicitaTrecho("ano");
        int mes = solicitaTrecho("mês");
        int dia = solicitaTrecho("dia");
        int hora = solicitaTrecho("hora");
        int minuto = solicitaTrecho("minuto");

        return LocalDateTime.of(ano, mes, dia, hora, minuto, 0);
    }

    private String getPlaca() {
        try {
            System.out.println("Digite a placa do Veiculo.");
            String placa = scanner.nextLine();
            placa = placa.trim();

            if (placa.length() != 7) {
                throw new IllegalArgumentException("A placa não pode estar vazia");
            }

            return placa;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getPlaca();
        }

    }

    private TipoVeiculo getTipoVeiculo() {
        try {
            System.out.println("Digite qual o tipo do Veiculo.");
            String tipoVeiculo = scanner.nextLine();
            tipoVeiculo = tipoVeiculo.trim();

            if (tipoVeiculo.equals("SUV")) {
                return TipoVeiculo.SUV;
            }

            if (tipoVeiculo.equals("Pequeno")) {
                return TipoVeiculo.PEQUENO;
            }

            if (tipoVeiculo.equals("Médio")) {
                return TipoVeiculo.MEDIO;
            }

            throw new IllegalArgumentException("O tipo de Veiculo deve ser Pequeno, Médio ou SUV");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getTipoVeiculo();
        }
    }

    private void cadatrarVeiculo() {
        String placa = getPlaca();
        TipoVeiculo tipoVeiculo = getTipoVeiculo();

        menu.adicionarVeiculo(placa, tipoVeiculo);
    }

    private void listarVeiculos() {
        menu.listarVeiculos();
    }

    private String getNome() {
        System.out.println("Digite o nome do Cliente");

        try {
            String nome = scanner.nextLine();
            nome = nome.trim();

            if (nome.length() < 7) {
                throw new IllegalArgumentException("O nome deve ter ao menos 7 caracteres.");
            }

            return nome;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getNome();
        }

    }

    private String getContato() {

        System.out.println("Digite o contato do Cliente");

        try {
            String contato = scanner.nextLine();
            contato = contato.trim();

            if (contato.length() != 11) {
                throw new IllegalArgumentException("O contato deve ter 11 caracteres.");
            }

            return contato;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getContato();
        }

    }

    private String getEndereco() {

        System.out.println("Digite o endereço do Cliente");

        try {
            String endereco = scanner.nextLine();
            endereco = endereco.trim();

            if (endereco.length() < 12) {
                throw new IllegalArgumentException("O contato deve ter ao menos 12 caracteres.");
            }

            return endereco;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getEndereco();
        }

    }

    private String getIdentificador() {

        System.out.println("Digite o identificador único do Cliente");

        try {
            String identificador = scanner.nextLine();
            identificador = identificador.trim();

            if (identificador.length() != 11 && identificador.length() != 14) {
                throw new IllegalArgumentException("O contato deve ter 11 ou 14 caracteres.");
            }

            return identificador;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            return getIdentificador();
        }

    }

    private void cadastrarCliente() {
        String nome = getNome();
        String contato = getContato();
        String endereco = getEndereco();
        String identificador = getIdentificador();

        menu.adicionarCliente(nome, contato, endereco, identificador);
    }

    private void listarClientes() {
        menu.listarClientes();
    }

    private  void cadastrarLocacao() {
        String placa = getPlaca();
        String identificador = getIdentificador();

        try {
            menu.criarLocacao(placa, identificador);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            cadastrarLocacao();
        }
    }

    private  void listarLocacoes() {
        menu.listarLocacoes();
    }

    private void listarLocacaoPorCliente() {
        String identificador = getIdentificador();

        try {
            System.out.println("\n--- Lista de Locações por Cliente ---");

            menu.listarLocacoesPorCliente(identificador);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.out.println("\n------\n");
            cadastrarLocacao();
        }

    }

    private void pagarLocacao() {
        String placa = getPlaca();
        LocalDateTime data = solicitaData();

        menu.pagarLocacao(placa, data);
    }

    public boolean fluxoPrincipal() {

        System.out.println("\n----------------------------------");
        System.out.println("1 - Cadastrar Veiculo");
        System.out.println("2 - Listar Veiculos");
        System.out.println("3 - Cadastrar Cliente");
        System.out.println("4 - Listar Clientes");
        System.out.println("5 - Criar Locação");
        System.out.println("6 - Listar Locações");
        System.out.println("7 - Listar Locações por Cliente");
        System.out.println("8 - Pagar Locação");
        System.out.println("9 - Sair");
        System.out.println("----------------------------------\n");

        System.out.println("Digite o numero da opção desejada: ");

        try {
            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    cadatrarVeiculo();
                    return true;
                }
                case 2 -> {
                    System.out.println("\n--- Lista de Veiculos ---");
                    listarVeiculos();
                    return true;
                }
                case 3 -> {
                    cadastrarCliente();
                    return true;
                }
                case 4 -> {
                    System.out.println("\n--- Lista de Clientes ---");
                    listarClientes();
                    return true;
                }
                case 5 -> {
                    cadastrarLocacao();
                    return true;
                }
                case 6 -> {
                    System.out.println("\n--- Lista de Locações ---");
                    listarLocacoes();
                    return true;
                }
                case 7 -> {
                    listarLocacaoPorCliente();
                    return true;
                }
                case 8 -> {
                    pagarLocacao();
                    return true;
                }
                case 9 -> {
                    return false;
                }
                default -> {
                    System.out.println("Opção não encontrada, tente novamente!");
                    return true;
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Precisa ser um número de 1 a 9.");
            return true;
        }

    }

    public void closeScanner() {
        scanner.close();
    }

}
