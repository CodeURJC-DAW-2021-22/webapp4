package es.codeurjc.wallypop.model;

import javax.persistence.*;

@Entity
@Table(name = "Favorites")
public class Favorites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_FAVORITE")
	private long ID_FAVORITE;
	
	@ManyToOne
	@JoinColumn(name = "ID_USER")
	private User USER;
	
	@OneToMany
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

	public void setID_FAVORITE(long iD_FAVORITE) {
		ID_FAVORITE = iD_FAVORITE;
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
