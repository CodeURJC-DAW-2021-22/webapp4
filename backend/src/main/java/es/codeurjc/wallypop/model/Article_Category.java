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
	
	@ManyToMany(mappedBy = "ID_ARTICLE")
	@NonNull
	@Column(name = "ID_ARTICLE")
	private List<Article> ARTICLES;
	
	@ManyToMany(mappedBy = "ID_CATEGORY")
	@NonNull
	@Column(name = "ID_CATEGORY")
	private List<Category> CATEGORIES;
	
	
	public Article_Category(Article aRTICLE, Category cATEGORY) {
		super();
		ARTICLE = aRTICLE;
		CATEGORY = cATEGORY;
	}
	
	public long getID_ARTICLE_CATEGORY() {
		return ID_ARTICLE_CATEGORY;
	}
	public void setID_ARTICLE_CATEGORY(long iD_ARTICLE_CATEGORY) {
		ID_ARTICLE_CATEGORY = iD_ARTICLE_CATEGORY;
	}
	public Article getARTICLE() {
		return ARTICLE;
	}
	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}
	public Category getCATEGORY() {
		return CATEGORY;
	}
	public void setCATEGORY(Category cATEGORY) {
		CATEGORY = cATEGORY;
	}
	
	
	
	
}