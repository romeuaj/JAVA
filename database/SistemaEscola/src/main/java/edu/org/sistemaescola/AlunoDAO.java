/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.org.sistemaescola;

/**
 *
 * @author okubo
 */
import java.util.List;
import java.util.ArrayList;
import java.sql.*;

public class AlunoDAO extends GenericDAO<Aluno, String> {
    @Override
    public List<Aluno> obterTodos(){
        List<Aluno> lista = new ArrayList<>();
        try{
        ResultSet r1 = getStatement().executeQuery("SELECT * FROM ALUNO");
        while(r1.next())
            lista.add(new Aluno(r1.getString("matricula"), r1.getString("NOME"), r1.getInt("ENTRADA")));
        
        closeStatement(r1.getStatement());
        }catch(Exception e){e.printStackTrace();}
        
        return lista;
    }
    
    @Override
    public Aluno obter(String chave){
        Aluno aluno = null;
        try{
            PreparedStatement ps = getConnection().prepareStatement("SELECT * FROM ALUNO WHERE MATRICULA = ?");
            ps.setString(1, chave);
            ResultSet rs = ps.executeQuery();
            if(rs.next()) aluno = new Aluno(rs.getString("matricula"), rs.getString("nome"), rs.getInt("entrada"));
            closeStatement(ps);
        }catch(Exception e){}
        return aluno;
    }
    
    @Override
    public void alterar(Aluno entidade){
        try {
		PreparedStatement ps = getConnection().prepareStatement(
				   "UPDATE ALUNO SET NOME = ?, ENTRADA = ? "+
				                                  	" WHERE MATRICULA = ?");
		ps.setString(1, entidade.getNome());
		ps.setInt(2, entidade.getEntrada());
		ps.setString(3, entidade.getMatricula());
		ps.executeUpdate();
		closeStatement(ps);
	    } catch (Exception e) { }
    }
    
    @Override
    public void excluir(String chave){
        try {
		PreparedStatement ps = getConnection().prepareStatement(
				                    "DELETE FROM ALUNO WHERE MATRICULA = ?");
		ps.setString(1, chave);
		ps.executeUpdate();
		closeStatement(ps);
	    } catch (Exception e) { }
    }
    
    @Override
    public void incluir(Aluno entidade){
        try {
                PreparedStatement ps = getConnection().prepareStatement(
				                                     "INSERT INTO ALUNO VALUES (?, ?, ?)");
		ps.setString(1, entidade.getMatricula());
		ps.setString(2, entidade.getNome());
		ps.setInt(3, entidade.getEntrada());
		ps.executeUpdate();
		closeStatement(ps);
	    } catch (Exception e) { }
    }
}