/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.romeu.derbydao;

/**
 *
 * @author okubo
 */
public class DerbyDAO {

    public static void main(String[] args) {
        AlunoDAO dao = new AlunoDAO();
        dao.obterTodos().forEach(aluno ->System.out.println(aluno.getNome()));
        System.out.println("Deveria aparecer algo aqui!!!");
    }
}
