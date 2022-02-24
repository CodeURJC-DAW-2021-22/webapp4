package es.codeurjc.wallypop.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "ARTICLE")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
 	@NonNull
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;

 	@ManyToOne
 	@NonNull
 	@JoinColumn(name = "ID_USER")
 	private User USER;
 	
 	@NonNull
 	@Column(name = "TITLE")
 	private String TITLE;
 	
 	@Column(name = "DESCRIPTION")
 	private String DESCRIPTION;
 	
 	@NonNull
 	@Column(name = "PRICE")
 	private float PRICE;
 	
 	@NonNull
 	@Column(name = "DATE")
 	private Date DATE = new java.sql.Date(System.currentTimeMillis());
 	
 	@NonNull
 	@Column(name = "RESERVED")
 	private boolean RESERVED = false;
 	
 	@NonNull
 	@Column(name = "SOLD")
 	private boolean SOLD = false;
 	
 	@Column(name = "PHOTO_BLOB")
 	private Blob PHOTO; 
 	
 	@NonNull
 	@Column(name = "N_VISITS")
 	private int N_VISITS = 0;
 	
 	@ManyToMany
 	@NonNull
 	@JoinColumn(name = "CATEGORY")
	private List<Category> CATEGORY;

	public Article(User uSER, String tITLE, String dESCRIPTION, float pRICE, Blob pHOTO, List<Category> cATEGORY) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		PHOTO = pHOTO;
		CATEGORY = cATEGORY;
	}
	public Article(User uSER, String tITLE, String dESCRIPTION, float pRICE, List<Category> cATEGORY) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		CATEGORY = cATEGORY;
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

	public float getPRICE() {
		return PRICE;
	}

	public void setPRICE(float pRICE) {
		PRICE = pRICE;
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
		return CATEGORY;
	}


	public void setCATEGORY(List<Category> cATEGORY) {
		CATEGORY = cATEGORY;
	}
 	
}
