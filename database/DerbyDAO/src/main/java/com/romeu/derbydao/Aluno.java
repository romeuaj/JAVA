/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romeu.derbydao;

/**
 *
 * @author okubo
 */
public class Aluno {
    private String nome;
    private String matricula;
    private int ano;

    public Aluno(){}
    
    public Aluno(String matricula, String nome, int entrada){
        this.nome = nome;
        this.matricula = matricula;
        this.ano = entrada;
    }
    
    public void setNome(String nome) throws NameLengthException {
        if(nome.length() < 40)
            this.nome = nome;
        else
            throw new NameLengthException("O nome informado excedeu o lçimite de 40 caracteres!");
    }
    
    public void setMatricula(String mat){
        this.matricula = mat;
    }
    
    public void setEntrada(int ent){
        this.ano = ent;
    }
    
    public String getMatricula(){
        return this.matricula;
    }
    
    public String getNome(){
        return this.nome;
    }
    
    public int getEntrada(){
        return this.ano;
    }
}
