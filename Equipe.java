package Estácio.semestre4.java.threads.empresa;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Equipe  extends Thread{
    private final int nr_integrantes;
    private CountDownLatch latch;
    private final Semaphore pool_fita;
    private final PoolProdutos pool_produtos;
    private final ArrayList <Empacotador> empacotadores;
    private final ContadorSinc prod_empacotados_eqp;

    public Equipe(String nome, int nr_integrantes, Semaphore pool_fita, PoolProdutos pool_produtos){
        this.setName(nome);
        this.nr_integrantes = nr_integrantes;
        this.pool_fita = pool_fita;
        this.pool_produtos = pool_produtos;
        this.empacotadores = new ArrayList<Empacotador>();
        prod_empacotados_eqp = new ContadorSinc(0);
        prepararEmpacotadores();
    }
    
    public synchronized void liberarFita(){
        latch.countDown();
        prod_empacotados_eqp.incrementar();
        pool_fita.release();
    }

    public synchronized void libararFitasDesnecessarias(int nr_travas_liberadas){
        pool_fita.release(nr_travas_liberadas);
        while(nr_travas_liberadas > 0){
        latch.countDown();
        nr_travas_liberadas--;
       }
    }

    public synchronized int getEmpacotamentos(){
        return prod_empacotados_eqp.getContador();
    }

    public synchronized int getNrIntegrantes(){
        return nr_integrantes;
    }


    public synchronized void prepararEmpacotadores(){
        for(int i = 1; i <= nr_integrantes; i++ ){
            Empacotador emp = new Empacotador(i, this);
            empacotadores.add(emp);
        }
    }

    private boolean empacotar(int nr_produtos){
        Thread thd;
        int thd_criadas = pool_produtos.retirarProdutos(nr_produtos);
        boolean controle;
        if(thd_criadas == 0){
            libararFitasDesnecessarias(nr_produtos);
            return false;
        } 
        else{
            for(int i = 1; i <= thd_criadas; i++){
                thd = new Thread(empacotadores.get(i-1));
                thd.setPriority(Thread.currentThread().getPriority() + 2);
                thd.start();
            }
            libararFitasDesnecessarias(nr_produtos - thd_criadas);
            return bloquear();
        }       
    }

    private boolean bloquear(){
        try{
            latch.wait();
            return true;
        } catch(InterruptedException e){
            e.printStackTrace();
            return false;
        }
    }

    private synchronized void relatar(){
        System.out.println("\n/----------------------------------------\\");
        System.out.println(getName() + " (thread: " + Thread.currentThread().getId() + ") FINALIZOU");
        System.out.println(" |- Nr Integrantes: " + this.nr_integrantes);
        System.out.println(" |- Empacotamentos da Equipe: " + this.prod_empacotados_eqp.getContador());
        System.out.println(" |- Nr Integrantes: " + this.nr_integrantes);
        System.out.println(" |- Empacotamentos por integrante: ");
        empacotadores.forEach((emp) -> emp.listarEmpacotamentos());
        System.out.println(" |- Threads por objeto empacotador: ");
        empacotadores.forEach((emp) -> emp.listarIdThreads());
        System.out.println("\\----------------------------------------/\n");
    }
    
    @Override
    public void run(){
        try{
            boolean controle;
            System.out.println(getName() + " PRONTA");
            do{
                pool_fita.acquire(nr_integrantes);
                this.latch = new CountDownLatch(nr_integrantes);
                controle = empacotar(nr_integrantes);
            }while(controle);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            relatar();
    }
}
