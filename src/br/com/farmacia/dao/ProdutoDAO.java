package br.com.farmacia.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import br.com.farmacia.domain.Fabricante;
import br.com.farmacia.domain.Produto;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {
	
	public void salvar(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, quantidade, preco, fabricante_id_fabricante) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
	
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setLong(4, p.getFabricante().getId());
		
		comando.executeUpdate();
	}
	
	public ArrayList<Produto> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.id_produto, p.descricao, p.quantidade, p.preco, "
				+ "f.id_fabricante, f.descricao ");
		sql.append("FROM produto p ");
		sql.append("INNER JOIN fabricante f ON p.fabricante_id_fabricante = f.id_fabricante");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> itens = new ArrayList<Produto>();
		while(resultado.next()) {
			Fabricante f = new Fabricante();
			f.setId(resultado.getLong("f.id_fabricante"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produto p = new Produto();
			p.setId(resultado.getInt("p.id_produto"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setFabricante(f);
			
			itens.add(p);
		}
		
		return itens;
	}
	
	public void excluir(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE id_produto = ? ");
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		comando.setInt(1, p.getId());
		comando.executeUpdate();
		
	}
	
	
	public void editar(Produto p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET descricao = ?, quantidade = ?, preco = ?, fabricante_id_fabricante = ? ");
		sql.append("WHERE id_produto = ? ");
		
		
		Connection conexao = (Connection) ConexaoFactory.conectar();
		PreparedStatement comando = (PreparedStatement) conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setInt(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setLong(4, p.getFabricante().getId());
		comando.setLong(5, p.getId());

		comando.executeUpdate();
	}
	
}
