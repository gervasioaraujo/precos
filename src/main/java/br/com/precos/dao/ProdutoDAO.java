package br.com.precos.dao;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import br.com.precos.domain.Produto;

@Component
@SuppressWarnings("unchecked")
public class ProdutoDAO extends HibernateDAO<Produto>{

	public ProdutoDAO() {
		// Informa o tipo da entidade para o Hibernate
		super(Produto.class);
	}

	// Consulta um anuncio pelo id
	public Produto getProdutoById(Long id) {
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
	public List<Produto> getProdutos() {
		Query q = getSession().createQuery("from Produto");
		List<Produto> produtos = q.list();
		return produtos;
	}

	// Insere ou atualiza um anúncio
	public void salvar(Produto p) {
		super.save(p);
	}

	// Deleta o anúncio pelo id
	public boolean delete(Long id) {
		Produto p = get(id);
		delete(p);
		return true;
	}
	
}
