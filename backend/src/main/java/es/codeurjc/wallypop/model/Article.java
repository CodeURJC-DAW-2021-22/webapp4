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
	@NonNull
	@JoinColumn(name = "USERS")
	private User USERS;

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
	@Column(name = "DESCRIPTION", columnDefinition = "text")
	private String DESCRIPTION = null;

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

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "ARTICLE")
	private List<Report> REPORTS = new LinkedList<>();

	public Article() {

	}

	public Article(User uSER, String tITLE, String dESCRIPTION, String cITY, String pOSTAL_CODE, float pRICE,
			Blob pHOTO, List<Category> lISTcATEGORYS) {
		super();
		USERS = uSER;
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PRICE = pRICE;
		PHOTO = pHOTO;
		CATEGORYS = lISTcATEGORYS;
		CITY = cITY;
		POSTAL_CODE = pOSTAL_CODE;
	}

	public List<Category> getCATEGORYS() {
		return CATEGORYS;
	}

	public String getCITY() {
		return CITY;
	}

	public Date getDATE() {
		return DATE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public long getID_ARTICLE() {
		return ID_ARTICLE;
	}

	public int getN_VISITS() {
		return N_VISITS;
	}

	public Blob getPHOTO() {
		return PHOTO;
	}

	public String getPOSTAL_CODE() {
		return POSTAL_CODE;
	}

	public float getPRICE() {
		return PRICE;
	}

	public String getPRICE_s() {
		return String.valueOf(getPRICE());
	}

	public String getTITLE() {
		return TITLE;
	}

	public User getUSER() {
		return USERS;
	}

	public String getUserEmail() {
		return USERS.getNAME();
	}

	public Long getUserID() {
		return USERS.getID_USER();
	}

	public boolean isRESERVED() {
		return RESERVED;
	}

	public boolean isSOLD() {
		return SOLD;
	}

	public void setCATEGORYS(List<Category> cATEGORYS) {
		CATEGORYS = cATEGORYS;
	}

	public void setCITY(String cITY) {
		CITY = cITY;
	}

	public User getUSER() {
		return USERS;
	}
	
	public void setUSER(User uSER) {
		USERS = uSER;
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}

	public void setPOSTAL_CODE(String pOSTAL_CODE) {
		POSTAL_CODE = pOSTAL_CODE;
	}

	public void setPRICE(float pRICE) {
		PRICE = pRICE;
	}

	public void setRESERVED(boolean rESERVED) {
		RESERVED = rESERVED;
	}

	public void setSOLD(boolean sOLD) {
		SOLD = sOLD;
	}
	
	public Long getUserID() {
		return USERS.getID_USER();
}
	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}

	public void setUSER(User uSER) {
		USERS = uSER;
	}

	public void visit() {
		N_VISITS += 1;
	}

}
