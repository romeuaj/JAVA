/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.org.sistemaescola;

/**
 *
 * @author okubo
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;

public class SistemaEscola {

    private final AlunoDAO dao = new AlunoDAO();
    public static final BufferedReader entrada = new BufferedReader( new InputStreamReader(System.in));
    
    private void exibir(Aluno aluno){
        System.out.println("Aluno: " + aluno.getNome()+ 
                            "\nMatricula: " + aluno.getMatricula() + 
                            "\nEntrada: " + aluno.getEntrada() + 
                            "\n=============================="); 
        }    
    
    
    public void exibirTodos(){
        dao.obterTodos().forEach(aluno->exibir(aluno));
    }
                                     
    public void inserirAluno() throws IOException {
        Aluno aluno = new Aluno();
        
        System.out.println("Nome: ");
        try{
        aluno.setNome(entrada.readLine());
        }catch(NameLengthException e){}
        System.out.println("Matrícula: ");
        aluno.setMatricula(entrada.readLine());
        System.out.println("Entrada: ");
        aluno.setEntrada(Integer.parseInt(entrada.readLine()));
        dao.incluir(aluno);
    }
    
    public void excluirAluno()throws IOException{
        System.out.println("Matrícula: ");
        dao.excluir(entrada.readLine());        
    }
    
    public static void main(String[] args) throws IOException {
        SistemaEscola sistema = new SistemaEscola();
        
        while(true){
            System.out.println("1 - Listar \n2 - Inserir \n3 - Excluir \n0 - Sair \nEscolha uma opção: ");
            int opcao = Integer.parseInt(entrada.readLine());
            if (opcao == 0) break;
            switch(opcao){
                case 1:
                    sistema.exibirTodos();
                    break;
                case 2:
                    sistema.inserirAluno();
                    break;
                case 3:
                    sistema.excluirAluno();
            
            }
        }
    }
}

