package br.com.precos.rest;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.precos.domain.Anuncio;
import br.com.precos.service.AnuncioService;
import br.com.precos.service.UploadService;
import br.com.precos.util.Response;
import br.com.precos.util.ResponseWithURL;

@Path("/anuncios")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class AnunciosResource {
	
	@Context
	HttpServletRequest request;
	
	@Autowired
	private AnuncioService anuncioService;
	
	@Autowired
	private UploadService uploadService;


	@GET
	public List<Anuncio> get() {
		List<Anuncio> anuncios = anuncioService.getAnuncios();
		return anuncios;
	}

	@GET
	@Path("{id}")
	public Anuncio get(@PathParam("id") long id) {
		Anuncio a = anuncioService.getAnuncio(id);
		return a;
	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		anuncioService.delete(id);
		return Response.Ok("Anúncio deletado com sucesso");
	}

	@POST
	public Response post(Anuncio a) {
		anuncioService.save(a);
		return Response.Ok("Anúncio salvo com sucesso");
	}

	@PUT
	public Response put(Anuncio a) {
		anuncioService.save(a);
		return Response.Ok("Anúncio atualizado com sucesso");
	}
	
	@GET
	@Path("/segmentoComercial/{segmentoComercial}")
	public List<Anuncio> getAnunciosBySegmentoComercial(@PathParam("segmentoComercial") String segmentoComercial) {
		List<Anuncio> anuncios = anuncioService.findAnunciosBySegmentoComercial(segmentoComercial);
		return anuncios;
	}

	@GET
	@Path("/nomeProduto/{nome}")
	public List<Anuncio> getAnunciosByNomeProduto(@PathParam("nome") String nome) {
		List<Anuncio> anuncios = anuncioService.findAnunciosByNameProduto(nome);
		return anuncios;
	}
	
	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response postFoto(final FormDataMultiPart multiPart) {
		if(multiPart != null && multiPart.getFields() != null) {
			Set<String> keys = multiPart.getFields().keySet();
			for (String key : keys) {
				// Obtem a InputStream para ler o arquivo
				FormDataBodyPart field = multiPart.getField(key);
				InputStream in = field.getValueAs(InputStream.class);
				try {
					// Salva o arquivo
					String fileName = field.getFormDataContentDisposition().getFileName();
					String path = uploadService.upload(fileName, in);
					System.out.println("Arquivo: " + path);
					return Response.Ok("Arquivo recebido com sucesso");
				} catch (IOException e) {
					e.printStackTrace();
					return Response.Error("Erro ao enviar o arquivo.");
				}
			}
		}
		return Response.Ok("Requisição inválida.");
	}

	// ***** m�todo que converte um arquivo para Base64
	@POST
	@Path("/toBase64")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public String toBase64(final FormDataMultiPart multiPart) {
		if(multiPart != null) {
			Set<String> keys = multiPart.getFields().keySet();
			for (String key : keys) {
				try {
					// Obtem a InputStream para ler o arquivo
					FormDataBodyPart field = multiPart.getField(key);
					InputStream in = field.getValueAs(InputStream.class);
					byte[] bytes = IOUtils.toByteArray(in);
					String base64 = Base64.getEncoder().encodeToString(bytes);
					return base64;
				} catch (Exception e) {
					e.printStackTrace();
					return "Erro: " + e.getMessage();
				}
			}
		}
		return "Requisição inválida.";
	}

//	@POST
//	@Path("/postFotoBase64")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	public Response postFotoBase64(@FormParam("fileName") String fileName, @FormParam("base64") String base64) {
//		if (fileName != null && base64 != null) {
//			try {
//				// Decode: Converte o Base64 para array de bytes
//				byte[] bytes = Base64.getDecoder().decode(base64);
//				InputStream in = new ByteArrayInputStream(bytes);
//				// Faz o upload (salva o arquivo em uma pasta)
//				String path = uploadService.upload(fileName, in);
//				System.out.println("Arquivo: " + path);
//				// OK
//				return Response.Ok("Arquivo recebido com sucesso");
//			} catch (Exception e) {
//				e.printStackTrace();
//				return Response.Error("Erro ao enviar o arquivo.");
//			}
//		}
//		return Response.Error("Requisição inválida.");
//	}
	
	
	@POST
	@Path("/postFotoBase64")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public ResponseWithURL postFotoBase64(@FormParam("fileName") String fileName, @FormParam("base64") String base64) {
		if (fileName != null && base64 != null) {
			try {
				// Decode: Converte o Base64 para array de bytes
				byte[] bytes = Base64.getDecoder().decode(base64);
				InputStream in = new ByteArrayInputStream(bytes);
				// Faz o upload (salva o arquivo em uma pasta)
				String url = uploadService.upload(fileName, in);
				// OK
				return ResponseWithURL.Ok("Arquivo recebido com sucesso",url);
			} catch (Exception e) {
				e.printStackTrace();
				return ResponseWithURL.Error("Erro ao enviar o arquivo.");
			}
		}
		return ResponseWithURL.Error("Requisição inválida.");
	}
	

}
