package br.com.precos.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.precos.domain.Anunciante;
import br.com.precos.domain.Anuncio;

@Component
@SuppressWarnings("unchecked")
public class AnuncianteDAO extends HibernateDAO<Anunciante> {

	public AnuncianteDAO() {
		// Informa o tipo da entidade para o Hibernate
		super(Anunciante.class);
	}

	// Consulta um anuncio pelo id
	public Anunciante getAnuncianteById(Long id) {
		// O Hibernate consulta automaticamente pelo id
		return super.get(id);
	}

	// Busca uma lista de anunciantes pelo nome
	public List<Anunciante> findByName(String nome) {
		Query q = getSession().createQuery("from Anunciante where lower(nome)  like lower(?)");
		q.setString(0, "%" + nome +"%");
		return q.list();
	}

	// Consulta todos os anúncios
	public List<Anunciante> getAnunciantes() {
		Query q = getSession().createQuery("from Anunciante");
		List<Anunciante> anunciantes = q.list();
		return anunciantes;
	}

	// Insere ou atualiza um anúncio
	public void salvar(Anunciante a) {
		super.save(a);
	}

	// Deleta o anúncio pelo id
	public boolean delete(Long id) {
		Anunciante a = get(id);
		delete(a);
		return true;
	}
	
}
