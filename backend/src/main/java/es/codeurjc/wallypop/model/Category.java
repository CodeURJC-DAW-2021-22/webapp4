package es.codeurjc.wallypop.model;

import java.sql.Blob;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CATEGORY")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id_category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@JoinColumn(name = "ID_CATEGORY")
	private long ID_CATEGORY;

	@NonNull
	@Column(name = "TITLE")
	private String TITLE;

	@Column(name = "DESCRIPTION", columnDefinition = "text")
	private String DESCRIPTION = null;

	@Column(name = "PHOTO")
	private Blob PHOTO = null;

	@Column(name = "ICON")
	private String ICON = null;

	@ManyToMany(mappedBy = "CATEGORYS")
	private List<Article> ARTICLES = new LinkedList<>();
	
	@Column(name="SIZE")
	private int size = 0;

	public Category() {
		// DEFAULT CONSTRUCTOR FOR JPA
	}

	public Category(String tITLE, String dESCRIPTION, Blob pHOTO, String iCON) {
		super();
		TITLE = tITLE;
		DESCRIPTION = dESCRIPTION;
		PHOTO = pHOTO;
		ICON = iCON;
	}

	public List<Article> getARTICLES() {
		return ARTICLES;
	}

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public String getICON() {
		return ICON;
	}

	public Long getID_CATEGORY() {
		return ID_CATEGORY;
	}

	@JsonIgnore
	public Blob getPHOTO() {
		return PHOTO;
	}

	public String getTITLE() {
		return TITLE;
	}

	public void setARTICLES(List<Article> aRTICLES) {
		ARTICLES = aRTICLES;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

	public void setICON(String iCON) {
		ICON = iCON;
	}

	public void setID_CATEGORY(Long iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
	}

	public void setPHOTO(Blob pHOTO) {
		PHOTO = pHOTO;
	}

	public void setTITLE(String tITLE) {
		TITLE = tITLE;
	}
	
	public int size() {
		return ARTICLES.size();
	}
	
	public void addSize() {
		size++;
	}
	
	public void setSize(int sIze) {
		size = sIze;
	}

	public int getSize() {
		return size;
	}

	public void setID_CATEGORY(long iD_CATEGORY) {
		ID_CATEGORY = iD_CATEGORY;
	}

	@Override
	public String toString() {
		return "Category [ID_CATEGORY=" + ID_CATEGORY + ", TITLE=" + TITLE + ", DESCRIPTION=" + DESCRIPTION + ", PHOTO="
				+ PHOTO + ", ICON=" + ICON + ", ARTICLES=" + ARTICLES + ", size=" + size + "]";
	}
	
}
