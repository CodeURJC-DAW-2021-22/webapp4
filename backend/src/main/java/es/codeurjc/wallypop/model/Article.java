package es.codeurjc.wallypop.model;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "ARTICLE")
public class Article {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	@NonNull
 	@Column(name = "ID_ARTICLE")
 	private int ID_ARTICLE;

 	@ManyToOne
 	@NonNull
 	@Column(name = "ID_USER")
 	private int ID_USER;
 	
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

	public Article(int iD_USER, String tITLE, String dESCRIPTION, float pRICE, Blob pHOTO) {
		super();
		ID_USER = iD_USER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		PHOTO = pHOTO;
	}

	public int getID_ARTICLE() {
		return ID_ARTICLE;
	}

	public int getID_USER() {
		return ID_USER;
	}

	public void setID_USER(int iD_USER) {
		ID_USER = iD_USER;
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
 	
 	
}
