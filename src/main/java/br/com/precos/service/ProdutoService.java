package br.com.precos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.precos.dao.AnuncianteDAO;
import br.com.precos.dao.ProdutoDAO;
import br.com.precos.domain.Anunciante;
import br.com.precos.domain.Produto;

@Component
public class ProdutoService {

	@Autowired
	private ProdutoDAO db;

	// Lista todos os anuncios do banco de dados
	public List<Produto> getProdutos() {
		List<Produto> produtos = db.getProdutos();
		return produtos;
	}

	// Busca um anuncio pelo id
	public Produto getProduto(Long id) {
		return db.getProdutoById(id);
	}

	// Deleta o anuncio pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id) {
		return db.delete(id);
	}

	// Salva ou atualiza o anuncio
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Produto produto) {
		db.saveOrUpdate(produto);
		return true;
	}

	// Busca o anuncio pelo nome
//	public List<Produto> findByName(String name) {
//		return db.findByName(name);
//	}

//	public List<Produto> findByCategoria(String categoria) {
//		return db.findByCategoria(categoria);
//	}

	
}
