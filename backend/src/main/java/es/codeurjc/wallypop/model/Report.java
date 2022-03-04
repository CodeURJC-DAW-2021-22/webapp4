package es.codeurjc.wallypop.model;

import java.sql.Blob;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "REPORT")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_REPORT")
	private long ID_REPORT;
	
	
	@ManyToOne
	@JoinColumn(name = "ID_ARTICLE")
	private Article ARTICLE;
	
	@NonNull
	@Column(name = "EMAIL")
	private String EMAIL;
	
	@Column(name = "DESCRIPTION")
	private String DESCRIPTION;
	
	@Lob
	@JsonIgnore
	@Column(name = "PROOF")
	private Blob PROOF;
	
	public Report() {
		
	}
	
	public Report(Article aRTICLE, String eMAIL, Blob pROOF,String dESCRIPTION) {
		super();
		ARTICLE = aRTICLE;
		EMAIL = eMAIL;
		PROOF = pROOF;
		DESCRIPTION = dESCRIPTION;
	}	
	
	public long getID_REPORT() {
		return ID_REPORT;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public Blob getPROOF() {
		return PROOF;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
}
