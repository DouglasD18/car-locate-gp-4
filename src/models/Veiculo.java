package models;

public class Veiculo {

    private String placa;
    private boolean estaDisponivel;
    private TipoVeiculo tipoVeiculo;

    public Veiculo(String placa, TipoVeiculo tipoVeiculo) {
        if (placa.length() != 7) {
            throw new IllegalArgumentException("A placa não pode estar vazia");
        }

        this.placa = placa;
        this.estaDisponivel = true;
        this.tipoVeiculo = tipoVeiculo;
    }

    public String getPlaca() {
        return placa;
    }

    public boolean isEstaDisponivel() {
        return estaDisponivel;
    }

    public TipoVeiculo getTipoVeiculo() {
        return tipoVeiculo;
    }

    public void setEstaDisponivel(boolean estaDisponivel) {
        this.estaDisponivel = estaDisponivel;
    }

    @Override
    public String toString() {
        return "Veiculo{" +
                "placa='" + placa + '\'' +
                ", estaDisponivel=" + estaDisponivel +
                ", tipoVeiculo=" + tipoVeiculo.toString() +
                '}';
    }
}
