package Estácio.semestre4.java.threads.empresa;

public class ContadorSinc {
    private int contador;
    private final int inicio;

    public ContadorSinc(int inicio){
        this.inicio = inicio;
        this.contador = inicio;
    }

    public synchronized void decrementar(){
        this.contador--;
    }

    public synchronized void decrementar(int n){
        this.contador = this.contador - n;
    }

    public synchronized void incrementar(){
        this.contador++;
    }

    public synchronized void incrementar(int n){
        this.contador = this.contador + n;
    }

    public synchronized void resetContador(){
        this.contador = this,inicio;
    }

    public synchronized void zerarContador(){
        this.contador = 0;
    }

    public synchronized int getContador(){
        return this.contador;
    }
}
