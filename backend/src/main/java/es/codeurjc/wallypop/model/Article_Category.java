package es.codeurjc.wallypop.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.lang.NonNull;


@Entity
@Table(name = "ARTICLE_CATEGORY")
public class Article_Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_ARTICLE_CATEGORY")
	private long ID_ARTICLE_CATEGORY;
	
	@ManyToMany
	@NonNull
	@Column(name = "ID_ARTICLE")
	private List<Article> ARTICLES;
	
	@ManyToMany
	@NonNull
	@Column(name = "ID_CATEGORY")
	private List<Category> CATEGORIES;

	public Article_Category(List<Article> aRTICLES, List<Category> cATEGORIES) {
		super();
		ARTICLES = aRTICLES;
		CATEGORIES = cATEGORIES;
	}

	public long getID_ARTICLE_CATEGORY() {
		return ID_ARTICLE_CATEGORY;
	}

	public List<Article> getARTICLES() {
		return ARTICLES;
	}

	public void setARTICLES(List<Article> aRTICLES) {
		ARTICLES = aRTICLES;
	}

	public List<Category> getCATEGORIES() {
		return CATEGORIES;
	}

	public void setCATEGORIES(List<Category> cATEGORIES) {
		CATEGORIES = cATEGORIES;
	}
	
}
