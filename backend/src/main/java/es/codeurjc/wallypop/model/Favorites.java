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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.lang.NonNull;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "FAVORITES")
@JsonIdentityInfo(generator= ObjectIdGenerators.IntSequenceGenerator.class, property="@id_favorite")
public class Favorites {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NonNull
	@Column(name = "ID_FAVORITE")
	private long ID_FAVORITE;

	@ManyToOne
	@NonNull
	@JoinColumn(name = "USERS")
	private User USERS;

	@OneToOne
	@NonNull
	@JoinColumn(name = "ID_ARTICLE")
	private Article ARTICLE;

	public Favorites() {

	}

	public Favorites(User uSER, Article aRTICLE) {
		super();
		USERS = uSER;
		ARTICLE = aRTICLE;
	}

	public Article getARTICLE() {
		return ARTICLE;
	}

	public long getID_FAVORITE() {
		return ID_FAVORITE;
	}

	public User getUSER() {
		return USERS;
	}

	public void setARTICLE(Article aRTICLE) {
		ARTICLE = aRTICLE;
	}

	public void setUSER(User uSER) {
		USERS = uSER;
	}

	public void setID_FAVORITE(long ID_FAVORITE) {
		this.ID_FAVORITE = ID_FAVORITE;
	}

	@NonNull
	public User getUSERS() {
		return USERS;
	}

	public void setUSERS(@NonNull User USERS) {
		this.USERS = USERS;
	}
}
