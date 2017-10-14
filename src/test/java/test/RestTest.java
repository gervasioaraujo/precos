package test;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Date;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import javax.ws.rs.client.WebTarget;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import junit.framework.TestCase;

import org.glassfish.jersey.client.ClientConfig;

import br.com.precos.dao.AnuncianteDAO;
import br.com.precos.dao.ProdutoDAO;
import br.com.precos.domain.Anunciante;
import br.com.precos.domain.Anuncio;
import br.com.precos.domain.Produto;
import br.com.precos.util.ResponseWithURL;
import br.com.precos.rest.GsonMessageBodyHandler;
import br.com.precos.service.AnuncioService;

public class RestTest extends TestCase {

	
//	public void testGetProdutoId() {
//		// Cria o cliente da API
//		ClientConfig clientConfig = new ClientConfig();
//		Client client = ClientBuilder.newClient(clientConfig);
//		// Registra o parser com o Google-GSON
//		client.register(GsonMessageBodyHandler.class);
//		// URL do web service
//		String URL = "http://localhost:8080/precos/rest/";
//		// Cria a requisi��o com o "caminho"
//		WebTarget target = client.target(URL).path("/produtos/5");
//		// Faz a requisi��o do tipo GET solicitando um JSON como resposta.
//		Response response = target.request(MediaType.APPLICATION_JSON).get();
//		// Status HTTP de retorno
//		int status = response.getStatus();
//		// L� um Produto (converte diretamente da string do JSON)
//		Produto p = response.readEntity(Produto.class);
//		assertEquals(200, status);
//		assertEquals("Macarr�o 1 kg", p.getNome());
//	}
//	
//	public void testDeleteProdutoId() {
//		// Cria o cliente da API
//		ClientConfig clientConfig = new ClientConfig();
//		Client client = ClientBuilder.newClient(clientConfig);
//		// Registra o parser com o Google-GSON
//		client.register(GsonMessageBodyHandler.class);
//		// URL do web service
//		String URL = "http://localhost:8080/produtos/rest/";
//		// Cria a requisi��o com o "caminho"
//		WebTarget target = client .target(URL).path("/produtos/6");
//				
//		// O teste de deletar s� funciona se o produto existir, ent�o vamos verificar antes.
//		Response responseGet =  
//				target.request(MediaType.APPLICATION_JSON)
//				.get();
//		if(responseGet.getStatus() != 200) {
//			// N�o deixa prosseguir no teste se o produto n�o existe
//			System.err.println("Produto para deletar n�o existe, abortando teste.");
//			return;
//		}
//		
//		// Faz a requisi��o do tipo GET solicitando um JSON como resposta.
//		Response response =  
//				target.request(MediaType.APPLICATION_JSON)
//				.delete();
//		// Valida se a requisi��o foi OK
//		assertEquals(200, response.getStatus());
//		// L� a response do pacote domain
//		br.com.precos.util.Response s = response.readEntity(br.com.precos.util.Response.class);
//		assertEquals("OK", s.getStatus());
//		assertEquals("Produto deletado com sucesso", s.getMsg());
//	}
//	
//	public void testPostFormParams() {
//		ClientConfig clientConfig = new ClientConfig();
//		Client client = ClientBuilder.newClient(clientConfig);
//		client.register(GsonMessageBodyHandler.class);
//		// Cria os par�metros do formul�rio
//		String base64 = Base64.getEncoder().encodeToString("Gerv�sio Ara�jo".getBytes());
//		Form form = new Form();
//		form.param("fileName", "nome2.xt");
//		form.param("base64", base64);
//		String URL = "http://localhost:8080/precos/";
//		// Faz a requisi��o do tipo POST com x-www-form-urlencoded
//		WebTarget target = client.target(URL).path("/rest/produtos/postFotoBase64");
//		Entity<Form> entity = Entity.entity(form,MediaType.APPLICATION_FORM_URLENCODED_TYPE);
//		Response response = target.request(MediaType.APPLICATION_JSON).post(entity);
//		// OK
//		assertEquals(200, response.getStatus());
//		// Converte para ResponseWithURL
//		ResponseWithURL r = response.readEntity(ResponseWithURL.class);
//		assertEquals("OK", r.getStatus());
//		assertEquals("Arquivo recebido com sucesso", r.getMsg());
//	}

	public void testSalvarNovoAnuncio() {
		
		ClientConfig clientConfig = new ClientConfig();
		Client client = ClientBuilder.newClient(clientConfig);
		client.register(GsonMessageBodyHandler.class);
		
		// Cria o objeto normalmente.
		Produto p = new Produto();
		p.setNome("Arroz 1kg");
		p.setPreco(BigDecimal.valueOf(2.3));
		p.setUrlFoto("http://public.com.br");
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
		
		Anunciante an = new Anunciante();
		an.setNome("Comercial Don do Coco");
		an.setUrlFoto("http://...");
		an.setDescricao("Atendemos todos os dias");
		AnuncianteDAO adao = new AnuncianteDAO();
		adao.salvar(an);
		
		Anuncio a = new Anuncio();
		a.setDataCriacao(new Date());
		a.setDataUltimaAtualizacao(new Date());
		a.setProduto(p);
		a.setAnunciante(an);
		
		String URL = "http://localhost:8080/precos/";
		WebTarget target = client.target(URL).path("/rest/anuncios");
		
		// Envia o objeto como JSON no corpo da requisi��o
		Entity<Anuncio> entity = Entity.entity(a, MediaType.APPLICATION_JSON);
		Response response = target.request(MediaType.APPLICATION_JSON).post(entity, Response.class);
		
		// Valida se a requisi��o foi OK
		assertEquals(200, response.getStatus());
		
		// L� a response do pacote util
		br.com.precos.util.Response s = response.readEntity(br.com.precos.util.Response.class);
		assertEquals("OK", s.getStatus());
		assertEquals("Anuncio salvo com sucesso", s.getMsg());
		
		// Depois de salvar o produto, vou busc�-lo pelo nome para excluir.
		
//		target = client.target(URL).path("/rest/produtos/nome/Novo Produto 1");
//		response = target.request(MediaType.APPLICATION_JSON).get();
		
		/*
		assertEquals(200, response.getStatus());
		p = response.readEntity(Produto.class);
		assertEquals("Novo Produto 1", p.getNome());
		Long id = p.getId();

		// Deleta o produto que foi salvo no teste, para n�o deixar sujeira a base.
		target = client .target(URL).path("/rest/produtos/"+id);
		response =  target.request(MediaType.APPLICATION_JSON).delete();
		assertEquals(200, response.getStatus());
		*/
	}


}
