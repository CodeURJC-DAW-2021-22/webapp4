package es.codeurjc.wallypop.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "ARTICLE")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;

 	@ManyToOne
 	@JoinColumn(name = "USER")
 	private User USER;
 	
 	@NotNull(message = "Debes especificar la ciudad")
 	@Column(name = "CITY")
 	private String CITY;
 	
 	@NotNull(message = "Debes especificar el título del anuncio.")
 	@Column(name = "TITLE")
 	private String TITLE;
 
 	@NotNull(message = "Debes especificar una pequeña descripción para el anuncio. ytuliza tu imaginación.")
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	
 	@NotNull
 	@Column(name = "PRICE")
 	private float PRICE;
 	
    @DateTimeFormat(pattern = "dd/MM/yyyy")
 	@Column(name = "DATE")
 	private Date DATE = new java.sql.Date(System.currentTimeMillis());
 	
 	@Column(name = "RESERVED")
 	private boolean RESERVED = false;
 	
 	@Column(name = "SOLD")
 	private boolean SOLD = false;
 	
 	@Column(name = "PHOTO_BLOB")
 	private Blob PHOTO; 
 	
 	@Column(name = "N_VISITS")
 	private int N_VISITS = 0;
 	
 	@ManyToMany
 	@JoinColumn(name = "CATEGORYS")
	private List<Category> CATEGORYS;
 	
 	// Un artículo tiene que estar asociada con una única usuario. 
 	// Un usuario puede estar asociada con varios artículos
 	@JoinColumn(name = "ID_USER")
	private String  ID_USER;
 	
 	public Article() {
 		
 	}


	public Article(User uSER, String tITLE, String dESCRIPTION, String cITY, float pRICE, Blob pHOTO, List<Category> lISTcATEGORYS) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		PHOTO = pHOTO;
		CATEGORYS = lISTcATEGORYS;
		CITY = cITY;
	}
	
 	
}
