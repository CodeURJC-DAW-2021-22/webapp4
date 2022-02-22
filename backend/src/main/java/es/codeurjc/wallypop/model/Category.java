package es.codeurjc.wallypop.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "CATEGORY")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@JoinColumn(name = "ID_CATEGORY")
	private long ID_CATEGORY;
	
	@NonNull
	@Column(name = "TITLE")
	private String TITLE;
	
	@Column(name = "DESCRIPTION")
	private String DESCRIPTION;
	
	@Column(name = "PHOTO")
	private Blob PHOTO;
	
	public Category(String TITLE, String DESCRIPTION) {
		this.TITLE = TITLE;
		this.DESCRIPTION = DESCRIPTION;
	}

	public Long getID_CATEGORY() {
		return ID_CATEGORY;
	}

	public void setID_CATEGORY(Long iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
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

	public Blob getPHOTO() {
		return PHOTO;
	}

	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}
}
