package es.codeurjc.wallypop.model;

import java.sql.Blob;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "ARTICLE")
public class Article {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ARTICLE")
	private long ID_ARTICLE;

	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User USER;

	@NonNull
	@Column(name = "CITY")
	private String CITY;
	
	@NonNull
	@Column(name = "POSTAL_CODE")
	private String POSTAL_CODE;

	@NonNull
	@Column(name = "TITLE")
	private String TITLE;

	@NonNull
	@Column(name = "DESCRIPTION")
	private String DESCRIPTION;

	@NonNull
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
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "ARTICLE")
	private List<Report> REPORTS = new LinkedList<>();

	public Article() {

	}

	public Article(User uSER, String tITLE, String dESCRIPTION, String cITY, String pOSTAL_CODE, float pRICE, Blob pHOTO,
			List<Category> lISTcATEGORYS) {
		super();
		USER = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		PHOTO = pHOTO;
		CATEGORYS = lISTcATEGORYS;
		CITY = cITY;
		POSTAL_CODE = pOSTAL_CODE;
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
	
	public void setUSER(User uSER) {
		USER = uSER;
	}

	public Date getDATE() {
		return DATE;
	}

	public int getN_VISITS() {
		return N_VISITS;
	}
	
	public void visit() {
		N_VISITS += 1;
	}

	public String getPRICE_s() {
		return String.valueOf(getPRICE());
	}
	
	public Long getUserID() {
		return USER.getID_USER();
	}

	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}
	
	public String getUserEmail() {
		return USER.getNAME();
	}

}
