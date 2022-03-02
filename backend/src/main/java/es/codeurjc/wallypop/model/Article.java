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
 	
 	@Column(name = "PRICE_1")
 	private float PRICE_1;
 	
 	@NotNull(message = "Debes especificar el precio del producto que estas anunciando")
 	@Column(name = "PRICE")
 	private String PRICE;
 	
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
 	
 	// Un artículo tiene que estar asociada con una única categoría. 
 	// Una categoria puede estar asociada con varios artículos
 	@JoinColumn(name = "ID_CATEGORY")
	private String  ID_CATEGORY;
 	
 	// Un artículo tiene que estar asociada con una única usuario. 
 	// Un usuario puede estar asociada con varios artículos
 	@JoinColumn(name = "ID_USER")
	private String  ID_USER;
 	
 	public Article() {
 		
 	}

	public Article(User uSER, String tITLE, String dESCRIPTION, float pRICE, Blob pHOTO, List<Category> lISTcATEGORYS) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE_1 = pRICE;
		PHOTO = pHOTO;
		CATEGORYS = lISTcATEGORYS;
	}
	public Article(User uSER,String cITY, String tITLE, String dESCRIPTION, float pRICE, List<Category> lISTcATEGORYS) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE_1 = pRICE;
		CATEGORYS = lISTcATEGORYS;
	}
	
	public Article(String cITY, String tITLE, String dESCRIPTION, float pRICE) {
		super();
		CITY = cITY;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE_1 = pRICE;
	}

	public Article(String cITY, String tITLE, String dESCRIPTION, float pRICE, List<Category> lISTcATEGORYS) {
		super();
		CITY = cITY;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE_1 = pRICE;
	}
	
	public Article( String cITY, String tITLE, String dESCRIPTION, String pRICE) {
		super();
		CITY = cITY;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;		
	}
	
	public int getID_ARTICLE() {
		return ID_ARTICLE;
	}

	public User getUSER() {
		return USER;
	}

	public void setUSER(User uSER) {
		USER = uSER;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public String getPRICE() {
		return PRICE;
	}

	public void setPRICE(float pRICE) {
		PRICE_1 = pRICE;
	}

	public Date getDATE() {
		return DATE;
	}

	public void setDATE(Date dATE) {
		DATE = dATE;
	}

	public boolean isRESERVED() {
		return RESERVED;
	}

	public void setRESERVED(boolean rESERVED) {
		RESERVED = rESERVED;
	}

	public boolean isSOLD() {
		return SOLD;
	}

	public void setSOLD(boolean sOLD) {
		SOLD = sOLD;
	}

	public Blob getPHOTO() {
		return PHOTO;
	}
	
	

	public float getPRICE_1() {
		return PRICE_1;
	}

	public void setPRICE_1(float pRICE_1) {
		PRICE_1 = pRICE_1;
	}

	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}

	public int getN_VISITS() {
		return N_VISITS;
	}

	public void setN_VISITS(int n_VISITS) {
		N_VISITS = n_VISITS;
	}


	public List<Category> getCATEGORY() {
		return CATEGORYS;
	}


	public void setCATEGORY(List<Category> cATEGORY) {
		CATEGORYS = cATEGORY;
	}
	
	
	public String getCITY() {
		return CITY;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public List<Category> getCATEGORYS() {
		return CATEGORYS;
	}

	public void setCATEGORYS(List<Category> cATEGORYS) {
		CATEGORYS = cATEGORYS;
	}

	public String getID_CATEGORY() {
		return ID_CATEGORY;
	}

	public void setID_CATEGORY(String iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
	}

	public String getID_USER() {
		return ID_USER;
	}

	public void setID_USER(String iD_USER) {
		ID_USER = iD_USER;
	}

	public void setID_ARTICLE(int iD_ARTICLE) {
		ID_ARTICLE = iD_ARTICLE;
	}
	
	

	public void setPRICE(String pRICE) {
		PRICE = pRICE;
	}

	@Override
	public String toString() {
		return "Article [ID_ARTICLE=" + ID_ARTICLE + ", USER=" + USER + ", CITY=" + CITY + ", TITLE=" + TITLE
				+ ", DESCRIPTION=" + DESCRIPTION + ", PRICE=" + PRICE + "]";
	}
	
	
 	
}
