package Estácio.semestre4.java.threads;
import java.util.concurrent.Semaphore;
public class Contador {
    private volatile int contador = 0;
    private final Semaphore semaforo = new Semaphore(1);

    public void incrementar(){
        try{
            semaforo.acquire();
            contador++;
        } catch(InterruptedException e){
            Thread.currentThread().interrupt();
            throw new RuntimeException("Thread interrompida", e);
        } finally {
            semaforo.release();
        }
    }

    public int getContador(){
        return contador;
    }
}
