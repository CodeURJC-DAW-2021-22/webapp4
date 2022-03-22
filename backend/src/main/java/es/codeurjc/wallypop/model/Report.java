package es.codeurjc.wallypop.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "REPORT")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id_report")
public class Report {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_REPORT")
	private long ID_REPORT;

	@ManyToOne
	@NonNull
	@JoinColumn(name = "ARTICLE")
	private Article ARTICLE;

	@NonNull
	@Column(name = "EMAIL")
	private String EMAIL;

	@NonNull
	@Column(name = "DESCRIPTION", columnDefinition = "text")
	private String DESCRIPTION;

	@Column(name = "PROOF")
	private Blob PROOF = null;

	public Report() {

	}

	public Report(Article aRTICLE, String eMAIL, Blob pROOF, String dESCRIPTION) {
		super();
		ARTICLE = aRTICLE;
		EMAIL = eMAIL;
		PROOF = pROOF;
		DESCRIPTION = dESCRIPTION;
	}

	public Report(Article aRTICLE, String eMAIL, String dESCRIPTION) {
		super();
		ARTICLE = aRTICLE;
		EMAIL = eMAIL;
		DESCRIPTION = dESCRIPTION;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public long getID_REPORT() {
		return ID_REPORT;
	}

	@JsonIgnore
	public Blob getPROOF() {
		return PROOF;
	}

	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public void setPROOF(Blob pROOF) {
		PROOF = pROOF;
	}

	public void setID_REPORT(long ID_REPORT) {
		this.ID_REPORT = ID_REPORT;
	}
}
