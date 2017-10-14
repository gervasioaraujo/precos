package test;

import java.math.BigDecimal;
import java.util.List;

import junit.framework.TestCase;
import br.com.precos.domain.Produto;
import br.com.precos.service.AnuncioService;

public class ProdutoTest extends TestCase {
	
	private AnuncioService anuncioService;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
		// Cria o "bean" pelo Spring.
		anuncioService = (AnuncioService) SpringUtil.getInstance().getBean(AnuncioService.class);
	}


	public void testListaProdutos() {
		
		List<Produto> produtos = produtoService.getAnuncios();
		
		assertNotNull(produtos);
		
		// Valida se encontrou algo
		assertTrue(produtos.size() > 0);
		
		// Valida se encontrou o Arroz 1kg
		Produto arroz1kg = produtoService.findByName("Arroz 1kg").get(0);
		assertEquals("Arroz 1kg", arroz1kg.getNome());

		// Valida se encontrou a Arroz 5kg
		Produto arroz5kg = produtoService.findByName("Arroz 5kg").get(0);
		assertEquals("Arroz 5kg", arroz5kg.getNome());
		
		// Valida se encontrou o Feij�o 1kg
		Produto feijao1kg = produtoService.findByName("Feij�o 1kg").get(0);
		assertEquals("Feij�o 1kg", feijao1kg.getNome());
		
	}

	public void testSalvarDeletarProduto() {
		
		Produto p = new Produto();
		p.setNome("Teste");
		p.setPreco(BigDecimal.valueOf(2.5));
		p.setCategoria("Categoria Teste");;
		p.setUrlFoto("url foto aqui");
		p.setAnunciante("Anunciante teste");
		produtoService.save(p);
		
		// id do produto salvo
		Long id = p.getId();
		assertNotNull(id);
		
		// Busca no banco de dados para confirmar que o produto foi salvo
		p = produtoService.getAnuncio(id);
		assertEquals("Teste", p.getNome());
		assertEquals(BigDecimal.valueOf(2.5), p.getPreco());
		assertEquals("Categoria Teste", p.getCategoria());
		assertEquals("url foto aqui", p.getUrlFoto());
		assertEquals("Anunciante teste", p.getAnunciante());
		
		// Atualiza o produto
		p.setNome("Teste UPDATE");
		produtoService.save(p);
		
		// Busca o produto novamente (vai estar atualizado)
		p = produtoService.getAnuncio(id);
		assertEquals("Teste UPDATE", p.getNome());
		
		// Deleta o carro
		produtoService.delete(id);
		
		// Busca o produto novamente
		p = produtoService.getAnuncio(id);
		
		// Desta vez o carro n�o existe mais.
		assertNull(p);
		
	}

}
