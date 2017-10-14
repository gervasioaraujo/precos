package br.com.precos.rest;

import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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

import br.com.precos.domain.SegmentoComercial;
import br.com.precos.service.SegmentoComercialService;
import br.com.precos.util.Response;

@Path("/segmentosComerciais")
@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
@Component
public class SegmentoComercialResource {
	
	@Context
	HttpServletRequest request;
	
	@Autowired
	private SegmentoComercialService segmentoComercialService;
	
//	@Autowired
//	private UploadService uploadService;


	@GET
	public List<SegmentoComercial> get() {
		List<SegmentoComercial> segmentosComerciais = segmentoComercialService.getSegmentosComerciais();
		return segmentosComerciais;
	}

	@GET
	@Path("{id}")
	public SegmentoComercial get(@PathParam("id") long id) {
		SegmentoComercial s = segmentoComercialService.getSegmentoComercial(id);
		return s;
	}

//	@GET
//	@Path("/categoria/{categoria}")
//	public List<Produto> getByCategoria(@PathParam("categoria") String categoria) {
//		List<Produto> produtos = anuncioService.findByCategoria(categoria);
//		return produtos;
//	}

//	@GET
//	@Path("/nome/{nome}")
//	public List<Produto> getByNome(@PathParam("nome") String nome) {
//		List<Produto> produtos = anuncioService.findByName(nome);
//		return produtos;
//	}

	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id) {
		segmentoComercialService.delete(id);
		return Response.Ok("Segmento Comercial deletado com sucesso");
	}

	@POST
	public Response post(SegmentoComercial s) {
		segmentoComercialService.save(s);
		return Response.Ok("Segmento Comercial salvo com sucesso");
	}

	@PUT
	public Response put(SegmentoComercial s) {
		segmentoComercialService.save(s);
		return Response.Ok("Segmento Comercial atualizado com sucesso");
	}
	
//	@POST
//	@Consumes(MediaType.MULTIPART_FORM_DATA)
//	public Response postFoto(final FormDataMultiPart multiPart) {
//		if(multiPart != null && multiPart.getFields() != null) {
//			Set<String> keys = multiPart.getFields().keySet();
//			for (String key : keys) {
//				// Obtem a InputStream para ler o arquivo
//				FormDataBodyPart field = multiPart.getField(key);
//				InputStream in = field.getValueAs(InputStream.class);
//				try {
//					// Salva o arquivo
//					String fileName = field.getFormDataContentDisposition().getFileName();
//					String path = uploadService.upload(fileName, in);
//					System.out.println("Arquivo: " + path);
//					return Response.Ok("Arquivo recebido com sucesso");
//				} catch (IOException e) {
//					e.printStackTrace();
//					return Response.Error("Erro ao enviar o arquivo.");
//				}
//			}
//		}
//		return Response.Ok("Requisição inválida.");
//	}

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

}
