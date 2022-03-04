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
 	private long ID_ARTICLE;

 	@ManyToOne
 	@JoinColumn(name = "USER")
 	private User USER;
 	
 	@NotNull
 	@Column(name = "CITY")
 	private String CITY;
 	
 	@NotNull
 	@Column(name = "TITLE")
 	private String TITLE;
 
 	@NotNull
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


	public String getCITY() {
		return CITY;
	}


	public void setCITY(String cITY) {
		CITY = cITY;
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


	public float getPRICE() {
		return PRICE;
	}


	public void setPRICE(float pRICE) {
		PRICE = pRICE;
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


	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}


	public List<Category> getCATEGORYS() {
		return CATEGORYS;
	}


	public void setCATEGORYS(List<Category> cATEGORYS) {
		CATEGORYS = cATEGORYS;
	}


	public long getID_ARTICLE() {
		return ID_ARTICLE;
	}


	public User getUSER() {
		return USER;
	}


	public Date getDATE() {
		return DATE;
	}


	public int getN_VISITS() {
		return N_VISITS;
	}


	public String getID_USER() {
		return ID_USER;
	}
	
	public String getPRICE_s() {
		return String.valueOf(getPRICE()); 
	}
 	
}
