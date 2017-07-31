package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.dao.FabricanteDAO;
import br.com.farmacia.domain.Fabricante;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private ArrayList<Fabricante> itens;
	private Fabricante fabricante;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public  ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}
	
	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			itens = fdao.listar();
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro na pesquisa: " + e.getMessage());
		}
	}

	public void prepararNovo() {
		fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO fdao = new FabricanteDAO();
			fdao.salvar(fabricante);

			itens = fdao.listar();
			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso.");
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();

			dao.excluir(fabricante);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso.");
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();

			dao.editar(fabricante);
			itens = dao.listar();
			
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso.");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}
}
