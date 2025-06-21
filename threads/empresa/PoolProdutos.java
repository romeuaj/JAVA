package Estácio.semestre4.java.threads.empresa;

public final class PoolProdutos extends ContadorSinc {
    public PoolProdutos(int qtdade_produtos){
        super(qtdade_produtos);
        if(qtdade_produtos < 1)
            throw new IllegalArgumentException("Argumentos ilegais usados no construtor de PoolProdutos.");
    }

    public synchronized int retirarProdutos(int nr_produtos){
        int aux = getContador();
        if((aux - nr_produtos) >= 0){
            decrementar(nr_produtos);
            return nr_produtos;
        }
        else{
            zerarContador();
            return aux;
        }
    }
}
