package es.codeurjc.wallypop.model;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "USER")
public class User {

	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_USER")
 	private Long ID_USER;

	@NonNull
	@Column(name = "FULL_NAME")
 	private String FULL_NAME;
	
	@NonNull
	@Column(name = "PASSWORD")
 	private String PASSWORD;
 	
	@NonNull
	@Column(name = "EMAIL")
 	private String EMAIL;
 	
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

	public User(String fULL_NAME, String pASSWORD, String eMAIL, String tEL,
			boolean iS_ADMIN) {
		super();
		FULL_NAME = fULL_NAME;
		PASSWORD = pASSWORD;
		EMAIL = eMAIL;
		TEL = tEL;
		IS_ADMIN = iS_ADMIN;
	}

	public User(String fULL_NAME, String pASSWORD, String eMAIL, String tEL) {
		super();
		FULL_NAME = fULL_NAME;
		PASSWORD = pASSWORD;
		EMAIL = eMAIL;
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

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
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
	
}
