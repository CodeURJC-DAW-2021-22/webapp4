package es.codeurjc.wallypop.model;

import java.sql.Blob;

import javax.persistence.*;

import org.springframework.lang.NonNull;

public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_REPORT")
	private long ID_REPORT;
	
	@ManyToOne
	@NonNull
	@Column(name = "ID_ARTICLE")
	private Article ARTICLE;
	
	@NonNull
	@Column(name = "EMAIL")
	private String EMAIL;
	
	@Column(name = "PROOF")
	private Blob PROOF;

	public Report(Article aRTICLE, String eMAIL, Blob pROOF) {
		super();
		ARTICLE = aRTICLE;
		EMAIL = eMAIL;
		PROOF = pROOF;
	}

	public Report(Article aRTICLE, String eMAIL) {
		super();
		ARTICLE = aRTICLE;
		EMAIL = eMAIL;
	}

	public long getID_REPORT() {
		return ID_REPORT;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public Blob getPROOF() {
		return PROOF;
	}

	public void setPROOF(Blob pROOF) {
		PROOF = pROOF;
	}
	
	
	
}
