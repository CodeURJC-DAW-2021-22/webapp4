package es.codeurjc.wallypop.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "USERS")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NonNull
	@Column(name = "ID_USER")
	private Long ID_USER;

	@NonNull
	@Column(name = "NAME")
	private String FULL_NAME;

	@NonNull
	@Column(name = "PASSWORD")
	private String PASSWORD;

	@NonNull
	@Column(name = "EMAIL")
	private String NAME;

	@NonNull
	@Column(name = "TEL")
	private String TEL;

	@NonNull
	@Column(name = "N_SOLD")
	private int N_SOLD = 0;

	@NonNull
	@Column(name = "N_SELL")
	private int N_SELL = 0;

	@NonNull
	@Column(name = "IS_ADMIN")
	private boolean IS_ADMIN = false;
	
	@OneToMany(cascade = CascadeType.ALL , mappedBy = "USERS")
	private List<Article> ARTICLES = new LinkedList<>();

	public User() {
		// DEFAULT CONSTRUCTOR FOR JPA
	}

	public User(String nAME, String pASSWORD, String eMAIL, String tEL, boolean iS_ADMIN) {
		super();
		FULL_NAME = nAME;
		PASSWORD = pASSWORD;
		NAME = eMAIL;
		TEL = tEL;
		IS_ADMIN = iS_ADMIN;
	}

	public User(String nAME, String pASSWORD, String eMAIL, String tEL) {
		super();
		FULL_NAME = nAME;
		PASSWORD = pASSWORD;
		NAME = eMAIL;
		TEL = tEL;
	}

	public Long getID_USER() {
		return ID_USER;
	}

	public String getFULL_NAME() {
		return FULL_NAME;
	}

	public void setFULL_NAME(String fULL_NAME) {
		FULL_NAME = fULL_NAME;
	}

	public String getPASSWORD() {
		return PASSWORD;
	}

	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	// EMAIL
	public String getNAME() {
		return NAME;
	}

	// EMAIL
	public void setNAME(String eMAIL) {
		NAME = eMAIL;
	}

	public String getTEL() {
		return TEL;
	}

	public void setTEL(String tEL) {
		TEL = tEL;
	}

	public int getN_SOLD() {
		return N_SOLD;
	}

	public void setN_SOLD(int n_SOLD) {
		N_SOLD = n_SOLD;
	}

	public int getN_SELL() {
		return N_SELL;
	}

	public void setN_SELL(int n_SELL) {
		N_SELL = n_SELL;
	}

	public boolean isIS_ADMIN() {
		return IS_ADMIN;
	}

	public void newArticle() {
		N_SELL += 1;
	}
	
	public void deleteArticle() {
		N_SELL -= 1;
	}

	public List<Article> getARTICLES() {
		return ARTICLES;
	}

	public void setARTICLES(List<Article> aRTICLES) {
		ARTICLES = aRTICLES;
	}
	
	public List<Article> getARTICLESSold() {
		List<Article> lResult = new LinkedList<>();
		for (Article a : ARTICLES) {
			if (a.isSOLD()) {
				lResult.add(a);
			}
		}
		return lResult;
	}

	public void updateN_Sell() {
		N_SELL = ARTICLES.size() - N_SOLD;
	}
	
	public void sell(Boolean bool) {
		if (bool) {
			N_SOLD += 1;
		} else {
			N_SOLD -= 1;
		}
	}

}
