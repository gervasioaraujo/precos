package br.com.precos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.precos.dao.SegmentoComercialDAO;
import br.com.precos.domain.SegmentoComercial;

@Component
public class SegmentoComercialService {

	@Autowired
	private SegmentoComercialDAO db;

	// Lista todos os anuncios do banco de dados
	public List<SegmentoComercial> getSegmentosComerciais() {
		List<SegmentoComercial> segmentosComerciais = db.getSegmentosComerciais();
		return segmentosComerciais;
	}

	// Busca um anuncio pelo id
	public SegmentoComercial getSegmentoComercial(Long id) {
		return db.getSegmentoComercialById(id);
	}

	// Deleta o anuncio pelo id
	@Transactional(rollbackFor = Exception.class)
	public boolean delete(Long id) {
		return db.delete(id);
	}

	// Salva ou atualiza o anuncio
	@Transactional(rollbackFor = Exception.class)
	public boolean save(SegmentoComercial s) {
		db.saveOrUpdate(s);
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
