@Entity
@Table(name = "favorites")
public class Favorites {
	@Id
 	@GenerationType = Identity
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotEmpty
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_FAVORITE")
	private int ID_FAVORITE;
	
	@ManyToOne
	@NotEmpty
	@Column(name = "ID_USER")
	private int ID_USER;
	
	@OneToOne(mappedBy = "ID_ARTICLE")
	@NotEmpty
	@Column(name = "ID_ARTICLE")
	private int ID_ARTICLE;

	public Favorites(int ID_USER, int ID_ARTICLE) {
		super();
		this.ID_USER = ID_USER;
		this.ID_ARTICLE = ID_ARTICLE;
	}

	public int getID_FAVORITE() {
		return ID_FAVORITE;
	}

	public void setID_FAVORITE(int iD_FAVORITE) {
		this.ID_FAVORITE = iD_FAVORITE;
	}

	public int getID_USER() {
		return this.ID_USER
	}

	public void getID_USER(int ID_USER) {
		this.ID_USER = ID_USER;
	}

	public int getID_ARTICLE() {
		return this.ID_ARTICLE;
	}

	public void setARTICLE(int ID_ARTICLE) {
		this.ID_ARTICLE = ID_ARTICLE;
	}
}
