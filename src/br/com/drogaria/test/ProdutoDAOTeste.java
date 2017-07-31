package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.farmacia.dao.ProdutoDAO;
import br.com.farmacia.domain.Fabricante;
import br.com.farmacia.domain.Produto;
import jdk.nashorn.internal.ir.annotations.Ignore;

public class ProdutoDAOTeste {
	
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("Qualquer um com 3 coisas");
		p.setPreco(1.20);
		p.setQuantidade(28);
		
		Fabricante f = new Fabricante();
		f.setId(20L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
	}
	
	@Test
	@Ignore
	public void listar() throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for (Produto p : lista) {
			System.out.println("Código do produto: "+ p.getId());
			System.out.println("Descrição: "+p.getDescricao());
			System.out.println("Preço: "+p.getPreco());
			System.out.println("Quantidade: "+p.getQuantidade());
			System.out.println("Código do Fabricante: "+p.getFabricante().getId());
			System.out.println("Descrição do Fabricante: "+p.getFabricante().getDescricao());
			System.out.println("");
		}
	}
	
	@Test
	@Ignore
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setId(2);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	
	@Test
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setId(6);
		p.setDescricao("ME AJUDA TOMCAT, 28, 1.20, 20");
		p.setPreco(15.30);
		p.setQuantidade(99);
		
		Fabricante f = new Fabricante();
		f.setId(20L);
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);		
	}
}
