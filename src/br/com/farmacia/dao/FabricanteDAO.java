package br.com.farmacia.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import br.com.farmacia.domain.Fabricante;
import br.com.farmacia.factory.ConexaoFactory;

public class FabricanteDAO {

	public void salvar(Fabricante f) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fabricante ");
		sql.append("(descricao) ");
		sql.append("VALUES (?);");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		command.setString(1, f.getDescricao());
		
		command.executeUpdate();

	}

	public void excluir(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fabricante ");
		sql.append("WHERE id_fabricante = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		command.setLong(1, f.getId());
		command.executeUpdate();
	}
	
	public void editar(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fabricante ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE id_fabricante = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		command.setString(1, f.getDescricao());
		command.setLong(2, f.getId());
		command.executeUpdate();
	}
	
	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_fabricante, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE id_fabricante = ? ");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		command.setLong(1, f.getId());
		ResultSet result = command.executeQuery();
		
		Fabricante retorno = null;
		if(result.next()) {
			retorno = new Fabricante();
			retorno.setId(result.getLong("id_fabricante"));
			retorno.setDescricao(result.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fabricante> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_fabricante, descricao ");
		sql.append("FROM fabricante ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		ResultSet result = command.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while(result.next()) {
			Fabricante f = new Fabricante();
			f.setId(result.getLong("id_fabricante"));
			f.setDescricao(result.getString("descricao"));
			
			lista.add(f);
		}
		
		return lista;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT id_fabricante, descricao ");
		sql.append("FROM fabricante ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC");
		
		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement command = (PreparedStatement) conexao.prepareStatement(sql.toString());
		command.setString(1, "%" + f.getDescricao() + "%");
		ResultSet result = command.executeQuery();
		
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		while(result.next()) {
			Fabricante item = new Fabricante();
			item.setId(result.getLong("id_fabricante"));
			item.setDescricao(result.getString("descricao"));
			
			lista.add(item);
		}
		
		return lista;
	}
	
	public static void main(String[] args) {
		/*
		 Fabricante f1 = new Fabricante(); f1.setDescricao("DESCRICAO 1");
		 
		 Fabricante f2 = new Fabricante(); f2.setDescricao("DESCRICAO 2");
		 
		 FabricanteDAO fdao = new FabricanteDAO();
		 
		 try { fdao.salvar(f1); fdao.salvar(f2);
		 System.out.println("Os fabricantes foram salvos com sucesso!"); } catch
		 (SQLException ex) { ex.printStackTrace();
		 System.out.println("Ocorreu um erro ao tentar salvar um dos fabricantes"); }
		*/ 

		/*Fabricante f1 = new Fabricante();
		f1.setId(2L);
		
		Fabricante f2 = new Fabricante();
		f1.setId(6L);

		FabricanteDAO fdao = new FabricanteDAO();

		try {
			fdao.excluir(f1);
			fdao.excluir(f2);
			System.out.println("Os fabricantes foram removidos com sucesso");
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Não foi possível excluir no banco de dados");
			ex.printStackTrace();
		}*/
		
		/*
		Fabricante f1 = new Fabricante();
		f1.setId(8L);
		f1.setDescricao("DESCRICAO 3");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			fdao.editar(f1);
			System.out.println("Os fabricantes foram editados com sucesso");
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Não foi possível editar o fabricante");
			ex.printStackTrace();
		}
		*/
		
		/*
		Fabricante f1 = new Fabricante();
		f1.setId(5L);
		f1.setDescricao("DESCRICAO 5");
		
		Fabricante f2 = new Fabricante();
		f2.setId(8L);
		f2.setDescricao("DESCRICAO 3");
		
		Fabricante f3 = new Fabricante();
		Fabricante f4 = new Fabricante();
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			f3 = fdao.buscarPorCodigo(f1);
			f4 = fdao.buscarPorCodigo(f2);
			
			System.out.println("Resultado 1 "+f3);
			System.out.println("Resultado 2 "+f4);
			
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Não foi possível encontrar o fabricante");
			ex.printStackTrace();
		}
		*/
		
		/*
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = fdao.listar();
			if (lista.size()==0) {
				System.out.println("Não foi possível encontrar nenhum fabricante");
			}
			
			for(Fabricante f : lista) {
				System.out.println("Resultado "+f);
			}
			
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Ocorreu um erro na listagem dos fabricantes");
			ex.printStackTrace();
		}
		*/
		
		Fabricante f1 = new Fabricante();
		f1.setDescricao("2");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista = fdao.buscarPorDescricao(f1);
			if (lista.size()==0) {
				System.out.println("Não foi possível encontrar nenhum fabricante");
			}
			
			for(Fabricante f : lista) {
				System.out.println("Resultado " + f);
			}
			
		} catch (SQLException ex) {
			// TODO: handle exception
			System.out.println("Ocorreu ao procurar os fabricantes");
			ex.printStackTrace();
		}
	}
}
