package br.com.precos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.precos.dao.AnuncianteDAO;
import br.com.precos.domain.Anunciante;

@Component
public class AnuncianteService {

	@Autowired
	private AnuncianteDAO db;

	// Lista todos os anuncios do banco de dados
	public List<Anunciante> getAnunciantes() {
		List<Anunciante> anunciantes = db.getAnunciantes();
		return anunciantes;
	}

	// Busca um anuncio pelo id
	public Anunciante getAnunciante(Long id) {
		return db.getAnuncianteById(id);
	}

	// Deleta o anuncio pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id) {
		return db.delete(id);
	}

	// Salva ou atualiza o anuncio
	@Transactional(rollbackFor = Exception.class)
	public boolean save(Anunciante anunciante) {
		db.saveOrUpdate(anunciante);
		return true;
	}

	// Busca uma lista de anunciantes pelo nome
	public List<Anunciante> findByName(String name) {
		return db.findByName(name);
	}

//	public List<Produto> findByCategoria(String categoria) {
//		return db.findByCategoria(categoria);
//	}

	
}
