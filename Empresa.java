package Estácio.semestre4.java.threads.empresa;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Empresa {
    private final Semaphore pool_fita;
    private final PoolProdutos pool_produtos;
    private  ArrayList <Equipe> turno;
    private final int max_prod_empacotar;
    private final int pool_empacotadores;
    private final int nr_max_equipes;
    private int prod_empacotados;

    public Empresa(int nr_fitas, int pool_empacotadores, int nr_max_equipes, int max_prod_empacotar) throws InterruptedException{
        if((nr_fitas <1) || (pool_empacotadores < 2) || (nr_max_equipes < 2) || (max_prod_empacotar < 1))
            throw new IllegalArgumentException("Argumentos ilegais utilizados no construtor de Empresa.");
        else{
            this.pool_fita = new Semaphore(nr_fitas);
            this.pool_empacotadores = pool_empacotadores;
            this.nr_max_equipes = nr_max_equipes;
            this.max_prod_empacotar = max_prod_empacotar;
            this.pool_produtos = new PoolProdutos(max_prod_empacotar);
            this.turno = new ArrayList<Equipe>();
            this.prod_empacotados = 0;
            criarEquipes(nr_fitas);
            turno.forEach((eqp) -> eqp.start());
            for(Equipe eqp: turno)
                try{
                    eqp.join();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            for(Equipe eqp: turno)
                prod_empacotados = prod_empacotados + eqp.getEmpacotamentos();
            System.out.println("TOTAL DE EMPACOTAMENTOS:" +  prod_empacotados);
        }
    }

    private void criarEquipes(int nr_fitas){
        Equipe eqp;
        int nr_emp_eqp;
        int empacotadores_disponiveis = pool_empacotadores;
        int i = 1;
        do{
            nr_emp_eqp = comporEquipe(empacotadores_disponiveis);
            if(nr_emp_eqp > nr_fitas)
                nr_emp_eqp = nr_fitas;
            eqp = new Equipe("Eqp[" + String.valueOf(i) + "]", nr_emp_eqp, pool_fita, pool_produtos);
            turno.add(eqp);
            empacotadores_disponiveis = empacotadores_disponiveis - nr_emp_eqp;
            i++;
        }while((i < nr_max_equipes) && (empacotadores_disponiveis >= 2));
        if(empacotadores_disponiveis > 0){
            if(nr_fitas > empacotadores_disponiveis)
                eqp = new Equipe("Eqp[" + String.valueOf(i) + "]", empacotadores_disponiveis, pool_fita, pool_produtos);
            else
                eqp = new Equipe("Eqp[" + String.valueOf(i) + "]", nr_fitas, pool_fita, pool_produtos);     
            turno.add(eqp);       
        }
    }

    private final int comporEquipe(int empacotadores_disponiveis){
        Random rnd = new Random();
        if(empacotadores_disponiveis > 2)
            return rnd.nextInt(pool_empacotadores / nr_max_equipes) + 2;
        else
            return empacotadores_disponiveis;
    }
}
