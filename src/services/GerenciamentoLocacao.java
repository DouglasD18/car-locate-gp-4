package services;

import models.Locacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class GerenciamentoLocacao {

    ArrayList<Locacao> locacoes = new ArrayList<>();

    public boolean criarLocacao(String identificadorLocatario, String placa) {
        Locacao locacao = new Locacao(identificadorLocatario, placa);

        return this.locacoes.add(locacao);
    }

    public ArrayList<Locacao> listarLocacoes() {
        return this.locacoes;
    }

    public ArrayList<Locacao> listarPorLocatario(String identificador) {
        return (ArrayList<Locacao>) this.locacoes.stream().filter(locacao -> locacao.getIdentificadorLocatario().equals(identificador)).collect(Collectors.toList());
    }

    public Locacao listarPorVeiculo(String placa) {
        return this.locacoes.stream().filter(locacao -> locacao.getPlaca().equals(placa)).findFirst().orElse(null);
    }

    public boolean remove(Locacao locacao) {
        return this.locacoes.remove(locacao);
    }

    public double pagarLocacaoPorVeiculo(String placa, LocalDateTime dataDeEntregaReal) {
        Locacao locacao = this.locacoes.stream().filter(loc -> loc.getPlaca().equals(placa)).findFirst().orElse(null);

        if (locacao == null) {
            throw new IllegalArgumentException("Esse veículo não está locado!");
        }

        return locacao.devolucao(dataDeEntregaReal);
    }

}
