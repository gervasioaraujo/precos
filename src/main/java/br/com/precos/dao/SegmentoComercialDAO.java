package br.com.precos.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.precos.domain.SegmentoComercial;

@Component
@SuppressWarnings("unchecked")
public class SegmentoComercialDAO extends HibernateDAO<SegmentoComercial> {

	public SegmentoComercialDAO() {
		// Informa o tipo da entidade para o Hibernate
		super(SegmentoComercial.class);
	}

	// Consulta um segmento comercial pelo id
	public SegmentoComercial getSegmentoComercialById(Long id) {
		// O Hibernate consulta automaticamente pelo id
		return super.get(id);
	}

	// Busca um anuncio pelo nome
//	public List<Produto> findByName(String nome) {
//		Query q = getSession().createQuery("from Produto where lower(nome)  like lower(?)");
//		q.setString(0, "%" + nome +"%");
//		return q.list();
//	}

	// Busca um anuncio pelo tipo
//	public List<Produto> findByCategoria(String categoria) {
//		Query q = getSession().createQuery("from Produto where categoria=?");
//		q.setString(0, categoria);
//		List<Produto> produtos = q.list();
//		return produtos;
//	}

	// Consulta todos os anúncios
	public List<SegmentoComercial> getSegmentosComerciais() {
		Query q = getSession().createQuery("from SegmentoComercial");
		List<SegmentoComercial> segmentosComerciais = q.list();
		return segmentosComerciais;
	}

	// Insere ou atualiza um anúncio
	public void salvar(SegmentoComercial s) {
		super.save(s);
	}

	// Deleta o anúncio pelo id
	public boolean delete(Long id) {
		SegmentoComercial s = get(id);
		delete(s);
		return true;
	}
	
}
