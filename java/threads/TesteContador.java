package Estácio.semestre4.java.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TesteContador {
    public static void main(String args[]){
        Contador contador = new Contador();

        int numThreads = 10;
        int numIncrementos = 1000;

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for(int i = 0; i < numThreads; i++){
            executor.submit(()->{
                for(int j = 0; j < numIncrementos; j++){
                    contador.incrementar();
                }
            });
        }

        executor.shutdown();

        while(!executor.isTerminated()){

        }

        System.out.println("Valor final do contador: " + contador.getContador());

    }
}
