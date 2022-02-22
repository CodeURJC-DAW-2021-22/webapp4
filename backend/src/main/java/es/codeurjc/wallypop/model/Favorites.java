package es.codeurjc.wallypop.model;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "FAVORITES")
public class Favorites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_FAVORITE")
	private long ID_FAVORITE;
	
	@ManyToOne
	@NonNull
	@JoinColumn(name = "ID_USER")
	private User USER;
	
	@OneToOne(mappedBy = "ID_ARTICLE")
	@NonNull
	@JoinColumn(name = "ID_ARTICLE")
	private Article ARTICLE;

	public Favorites(User uSER, Article aRTICLE) {
		super();
		USER = uSER;
		ARTICLE = aRTICLE;
	}

	public long getID_FAVORITE() {
		return ID_FAVORITE;
	}

	public User getUSER() {
		return USER;
	}

	public void setUSER(User uSER) {
		USER = uSER;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}
	
	
	
}
