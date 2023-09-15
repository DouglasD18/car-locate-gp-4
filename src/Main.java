import services.Fluxo;

public class Main {
    public static void main(String[] args) {

        Fluxo fluxo = new Fluxo();
        boolean controle = true;

        while(controle) {
            controle = fluxo.fluxoPrincipal();
        }

        fluxo.closeScanner();

      }
}