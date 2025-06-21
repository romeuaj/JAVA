package Estácio.semestre4.java.threads.empresa;

public class Principal {
    private static Empresa ACME;

    public static void main(String args[]) throws InterruptedException{
        ACME = new Empresa(20, 25, 4, 200);
    }
}
