/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.org.sistemaescola;

/**
 *
 * @author okubo
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

/**
 *
 * @author okubo
 */
  public abstract class GenericDAO<E,K> {
   protected Connection getConnection() throws Exception{
    //Class.forName("org.apache.derby.jdbc.ClientDriver");
    return DriverManager.getConnection(
        "jdbc:derby://localhost:1527/ESCOLA","escola","esc");
  }
  protected Statement getStatement() throws Exception{
   	return getConnection().createStatement();
  }
  protected void closeStatement(Statement st) throws Exception{
   	st.getConnection().close();
  }
  public abstract void incluir(E entidade);
  public abstract void excluir(K chave);
  public abstract void alterar(E entidade);
  public abstract E obter(K chave);
  public abstract List<E> obterTodos();
    
}

