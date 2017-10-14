package br.com.precos.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.com.precos.dao.AnuncioDAO;
import br.com.precos.domain.Anuncio;
import br.com.precos.domain.Produto;

import org.springframework.stereotype.Component;

@Component
public class AnuncioService {
	
	@Autowired
	private AnuncioDAO db;

	// Lista todos os anuncios do banco de dados
	public List<Anuncio> getAnuncios() {
		List<Anuncio> anuncios = db.getAnuncios();
		return anuncios;
	}

	// Busca um anuncio pelo id
	public Anuncio getAnuncio(Long id) {
		return db.getAnuncioById(id);
	}

	// Deleta o anuncio pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id) {
		return db.delete(id);
	}

	// Salva ou atualiza o anuncio
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Anuncio anuncio) {
		db.saveOrUpdate(anuncio);
		return true;
	}

	// Busca o anuncio pelo nome
	public List<Anuncio> findAnunciosByNameProduto(String name) {
		return db.findAnunciosByNameProduto(name);
	}

	public List<Anuncio> findAnunciosBySegmentoComercial(String segmentoComercial) {
		return db.findAnunciosBySegmentoComercial(segmentoComercial);
	}
	
}
