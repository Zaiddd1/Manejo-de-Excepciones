package numero;

public class Main {

    public static void main(String[] args) {

        Numero numero = new Numero();

        try {

            numero.setValor(25.5);

            System.out.println("Valor: "
                    + numero.getValor());

            // Este segundo setValor lanza la excepcion; el mensaje del
            // primer println de arriba ya se imprimio antes de llegar aqui.
            numero.setValor(-10);

        } catch (IllegalArgumentException e) {

            System.out.println("Error: "
                    + e.getMessage());
        }
    }
}
