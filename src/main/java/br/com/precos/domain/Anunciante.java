package br.com.precos.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Anunciante implements Serializable {

	private static final long serialVersionUID = 8541479471624753619L;

	@Id
	@Column(name="id", unique=true, nullable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false, length=100)
	private String nome;
	
	@Column(length=150)
	private String descricao;
	
	@Column(nullable=false, length=100)
	private String urlFoto;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_id")
	private Endereco endereco;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contato_id")
	private Contato contato;

	@ManyToOne
	@JoinColumn(name = "segmento_comercial_id")
	private SegmentoComercial segmentoComercial;
	
	
	// *************** Getters and Setters *****************
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUrlFoto() {
		return urlFoto;
	}

	public void setUrlFoto(String urlFoto) {
		this.urlFoto = urlFoto;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public SegmentoComercial getSegmentoComercial() {
		return segmentoComercial;
	}

	public void setSegmentoComercial(SegmentoComercial segmentoComercial) {
		this.segmentoComercial = segmentoComercial;
	}
	
}
