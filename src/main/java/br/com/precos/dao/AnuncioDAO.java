package br.com.precos.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.precos.domain.Anuncio;

@Component
@SuppressWarnings("unchecked")
public class AnuncioDAO extends HibernateDAO<Anuncio> {
	
	public AnuncioDAO() {
		// Informa o tipo da entidade para o Hibernate
		super(Anuncio.class);
	}

	// Consulta um anuncio pelo id
	public Anuncio getAnuncioById(Long id) {
		// O Hibernate consulta automaticamente pelo id
		return super.get(id);
	}

	// Busca uma lista de anúncios pelo nome
	public List<Anuncio> findAnunciosByNameProduto(String nome) {
		Query q = getSession().createQuery("from Anuncio as a where lower(a.produto.nome) like lower(?)");
		q.setString(0, "%" + nome +"%");
		return q.list();
	}

	// Busca uma lista de anúncios pelo segmento comercial
	public List<Anuncio> findAnunciosBySegmentoComercial(String segmentoComercial) {
		Query q = getSession().createQuery("from Anuncio as a where lower(a.anunciante.segmentoComercial.nome) = lower(?)");
		q.setString(0, segmentoComercial);
		List<Anuncio> anuncios = q.list();
		return anuncios;
	}

	// Consulta todos os anúncios
	public List<Anuncio> getAnuncios() {
		Query q = getSession().createQuery("from Anuncio");
		List<Anuncio> anuncios = q.list();
		return anuncios;
	}

	// Insere ou atualiza um anúncio
	public void salvar(Anuncio a) {
		super.save(a);
	}

	// Deleta o anúncio pelo id
	public boolean delete(Long id) {
		Anuncio a = get(id);
		delete(a);
		return true;
	}
	
}
