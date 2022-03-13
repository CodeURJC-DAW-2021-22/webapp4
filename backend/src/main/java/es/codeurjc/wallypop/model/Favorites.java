package es.codeurjc.wallypop.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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

	@OneToOne
	@NonNull
	@JoinColumn(name = "ID_ARTICLE")
	private Article ARTICLE;

	public Favorites() {

	}

	public Favorites(User uSER, Article aRTICLE) {
		super();
		USER = uSER;
		ARTICLE = aRTICLE;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public long getID_FAVORITE() {
		return ID_FAVORITE;
	}

	public User getUSER() {
		return USER;
	}

	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}

	public void setUSER(User uSER) {
		USER = uSER;
	}

}
